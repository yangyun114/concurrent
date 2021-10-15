package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j(topic = "TestReentrantLock")
public class TestReentrantLock {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock();

        Thread t1 = new Thread(() -> {
            try {
                log.info("尝试获取锁...");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("没有获得锁，返回...");
                return;
            }

            try {
                log.info("获取到锁...");
            } finally {
                log.info("释放锁...");
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        t1.start();
        t1.interrupt();
    }

}
