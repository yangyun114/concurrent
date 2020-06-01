package lession4;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestWaitTwo")
public class TestWaitTwo {
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                log.info("获得锁...");
                try {
                    // sleep不释放锁
//                    Thread.sleep(20000);
                    // wait释放锁
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        Thread.sleep(1000);
        synchronized (lock) {
            log.info("获得锁...");
        }
    }
}
