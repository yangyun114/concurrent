package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "TestReentrantLockTwo")
public class TestReentrantLockTwo {
    static ReentrantLock lock = new ReentrantLock();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;
    public static void main(String[] args) throws InterruptedException {
        Condition cigarette = lock.newCondition();
        Condition takeout = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                log.info("有烟没:[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.info("没烟，等会...");
                    cigarette.await();
                }
                log.info("有烟，干活...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.info("外卖到了没:[{}]", hasTakeout);
                while (!hasTakeout) {
                    log.info("没到，等会...");
                    takeout.await();
                }
                log.info("到了，干活...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "小女").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                hasCigarette = true;
                cigarette.signal();
                log.info("烟到了...");
            } finally {
                lock.unlock();
            }
        }, "送烟的").start();

        new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                hasTakeout = true;
                takeout.signal();
                log.info("外卖到了...");
            } finally {
                lock.unlock();
            }
        }, "送外卖的").start();
    }
}
