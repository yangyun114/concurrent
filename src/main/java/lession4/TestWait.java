package lession4;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestWait")
public class TestWait {
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                log.info("执行...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("其他代码...");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                log.info("执行...");
                try {
                    lock.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("其他代码...");
            }
        }, "t2").start();

        Thread.sleep(2000);
        log.info("唤醒...");
        synchronized (lock) {
            lock.notify();
        }
    }
}
