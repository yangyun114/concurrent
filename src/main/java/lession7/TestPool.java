package lession7;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "TestPool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPoll tp = new ThreadPoll(1, 1000, TimeUnit.MILLISECONDS, 10, (queue, task) -> {
            // 1、死等
//            queue.put(task);
            // 2、超时
            queue.offer(task, 500, TimeUnit.MILLISECONDS);
        });
        for (int i = 0; i < 3; i++) {
            int j = i;
            tp.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}", j);
            });
        }
    }
}

// 拒绝策略
@FunctionalInterface
interface RejectPolicy<T> {
    // 1、死等
    // 2、带超时等待
    // 3、让调用者放弃任务执行
    // 4、让调用者抛出异常
    // 5、让调用者自己执行任务
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j(topic = "ThreadPoll")
class ThreadPoll {
    // 任务队列
    private BlockingQueue<Runnable> taskQueue;
    // 线程集合
    private HashSet<Worker> workers = new HashSet<>();
    // 线程数
    private int coreSize;
    // 获取任务的超时时间
    private long timeout;
    // 超时时间单位
    private TimeUnit unit;
    // 拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPoll(int coreSize, long timeout, TimeUnit unit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        this.rejectPolicy = rejectPolicy;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 执行任务
            // 1、task不为空，执行task
            // 2、当task执行完毕，从任务队列获取任务并执行
            while (task != null || (task = taskQueue.take()) != null) {
//            while (task != null || (task = taskQueue.poll(timeout, unit)) != null) {
                try {
                    log.info("正在执行...{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 执行完毕
                    task = null;
                }
            }
            // 一旦退出循环，从workers中移除
            synchronized (workers) {
                log.info("worker 被移除:{}", this);
                workers.remove(this);
            }
        }
    }

    // 执行任务
    public void execute(Runnable task) {
        // 当任务数没有超过核心数coreSize，直接交给Worker对象执行
        // 如果超过了，就加入任务队列暂存
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.info("新增 worker:{}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
//                taskQueue.put(task);
                // 1、死等
                // 2、带超时等待
                // 3、让调用者放弃任务执行
                // 4、让调用者抛出异常
                // 5、让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }
}

// 阻塞队列
@Slf4j(topic = "BlockingQueue")
class BlockingQueue<T> {
    // 1、任务队列
    private Deque<T> queue = new ArrayDeque<>();

    // 2、锁来保护队头和队尾的元素
    private ReentrantLock lock = new ReentrantLock();

    // 3、容量上限，达到上限时生产者阻塞，容量空消费者阻塞
    private int capacity;

    // 4、、生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    // 5、消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    // 容量构造方法
    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // 带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            // 将超时时间统一转换为纳秒
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    // 返回剩余的等待时间
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    // 阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    // 阻塞添加
    public void put(T task) {
        lock.lock();
        try {
            // 队列满，阻塞
            while (queue.size() == capacity) {
                try {
                    log.info("等待加入任务队列:{}...", task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("加入任务队列:{}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    // 带超时时间的阻塞添加
    public boolean offer(T task, long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            // 队列满，阻塞
            while (queue.size() == capacity) {
                try {
                    log.info("等待加入任务队列:{}...", task);
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("加入任务队列:{}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    // 获取大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            // 队列是否已满
            if (queue.size() == capacity) {
                // 把操作权利下放到reject policy
                rejectPolicy.reject(this, task);
            } else {
                // 有空闲
                log.info("加入任务队列:{}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
