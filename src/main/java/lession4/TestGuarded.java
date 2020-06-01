package lession4;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestGuarded")
public class TestGuarded {

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        // 线程1等待线程2的下载结果
        new Thread(() -> {
            try {
                log.info("等待结果");
//                Object o = guardedObject.get();
                Object o = guardedObject.get(4000);
                log.info("下载结果为:{}", o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "下载完成后").start();

        // 线程2表示下载完成
        new Thread(() -> {
            try {
                log.info("开始下载...");
                Thread.sleep(2000);
                guardedObject.complete("这是一个视频...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "下载任务").start();
    }

}

class GuardedObject {
    // 结果
    private Object response;

    // 给获取结果的线程使用
    public Object get() throws InterruptedException {
        synchronized (this) {
            // 为空则等待
            while (response == null) {
                this.wait();
            }
            return response;
        }
    }

    public Object get(long timeout) throws InterruptedException {
        synchronized (this) {
            // 开始时间
            long begin = System.currentTimeMillis();
            // 经历的时间
            long passed = 0;
            // 为空则等待
            while (response == null) {
                long wait = timeout - passed;
                if (wait <= 0) {
                    break;
                }
                // 可能会有虚假唤醒问题，所以不能直接使用timeout
                this.wait(wait);
                passed = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    // 给结果完成的线程使用
    public void complete (Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
