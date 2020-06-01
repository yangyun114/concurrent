package lession4;

import lombok.extern.slf4j.Slf4j;
import org.omg.PortableServer.THREAD_POLICY_ID;

@Slf4j(topic = "TestPosture")
public class TestPosture {
    static final Object lock = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                log.info("是否有烟:[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.info("没烟，先歇会...");
                    try {
//                        Thread.sleep(5000);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("是否有烟:[{}]", hasCigarette);
                if (hasCigarette) {
                    log.info("干活...");
                } else {
                    log.info("干不成活...");
                }
            }
        }, "小南").start();

        new Thread(() -> {
            synchronized (lock) {
                log.info("外卖到了吗:[{}]", hasTakeout);
                while (!hasTakeout) {
                    log.info("没到，先等会...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("外卖到了吗:[{}]", hasTakeout);
                if (hasTakeout) {
                    log.info("干活...");
                } else {
                    log.info("干不成活...");
                }
            }
        }, "小女").start();

//        for (int i = 0;i < 5;i++) {
//            new Thread(() -> {
//                synchronized (lock) {
//                    log.info("干活...");
//                }
//            }, "其他人").start();
//        }

        Thread.sleep(1000);

        new Thread(() -> {
            synchronized (lock) {
                hasTakeout = true;
                log.info("外卖送到了...");
                lock.notifyAll();
            }
        }, "送外卖的").start();

        Thread.sleep(1000);

        new Thread(() -> {
            synchronized (lock) {
                hasCigarette = true;
                log.info("烟送到了...");
                lock.notifyAll();
            }
        }, "送烟的").start();
    }
}
