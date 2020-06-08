package lession4;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestSolveDeadLock")
public class TestSolveDeadLock {
    public static void main(String[] args) {
        Object A = new Object();
        Object B = new Object();

        new Thread(() -> {
            log.info("获取锁A...");
            synchronized (A) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("获取锁B...");
                synchronized (B) {
                    log.info("获取成功...");
                }
            }
        }, "t1").start();

        new Thread(() -> {
            log.info("获取锁B...");
            synchronized (B) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("获取锁A...");
                synchronized (A) {
                    log.info("获取成功...");
                }
            }
        }, "t2").start();
    }
}
