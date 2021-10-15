package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j(topic = "ConsumerAndContainer")
public class ConsumerAndProducer {
    private static Object lock = new Object();
    private static Queue<Integer> queue = new LinkedList();
    private static volatile int count = 0;

    public static void main(String[] args) {
        Thread consumer = new Thread(() -> {
            while (!queue.isEmpty()) {
                synchronized (lock) {
                    Integer peek = queue.peek();
                    log.info("{}", peek);

                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "consumer");
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    queue.offer(++count);

                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "producer");

        consumer.start();
        producer.start();
    }
}
