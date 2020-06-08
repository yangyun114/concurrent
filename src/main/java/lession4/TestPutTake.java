package lession4;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.LinkedList;

@Slf4j(topic = "TestPutTake")
public class TestPutTake {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                // 线程内不能直接使用i，lambda表达式只能使用final变量
                queue.put(new Message(id, "产品" + id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();
    }

}

// 消息队列，Java线程间通信
@Slf4j(topic = "MessageQueue")
class MessageQueue {
    // 队列集合
    private LinkedList<Message> list = new LinkedList<>();
    // 容量
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    // 消费者获取消息
    public Message take() {
        // 加锁对象是任意的，this、list都可以
        synchronized (list) {
            // 检查是否为空
            while (list.isEmpty()) {
                try {
                    log.info("队列空，消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从队列的头部获取消息返回
            Message message = list.removeFirst();
            log.info("消费了一个消息:{}", message);
            list.notifyAll();
            return message;
        }
    }

    // 生产者产生消息
    public void put(Message message) {
        synchronized (list) {
            while (list.size() == capacity) {
                // 队列已满
                try {
                    log.info("队列满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 加到尾部
            list.addLast(message);
            log.info("生产了一个消息:{}", message);
            list.notifyAll();
        }
    }
}

// 消息类
final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
