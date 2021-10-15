package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TwoThreadPrint")
public class TwoThreadPrint {
    private static Object lock = new Object();
    private static volatile int count = 0, flag = 0;
    public static void main(String[] args) {
//        Thread t1 = new Thread(() -> {
//            while (count < 100) {
//                synchronized (lock) {
//                    // 拿到锁之后，打印
//                    log.info("{}", ++count);
//                    // 打印完成之后，唤醒其它等待的线程
//                    lock.notifyAll();
//                    try {
//                        // 其它线程唤醒完成，本线程等待
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "t1");
//
//        Thread t2 = new Thread(() -> {
//            while (count < 100) {
//                synchronized (lock) {
//                    // 拿到锁之后，打印
//                    log.info("{}", ++count);
//                    // 打印完成之后，唤醒其它等待的线程
//                    lock.notifyAll();
//                    try {
//                        // 其它线程唤醒完成，本线程等待
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "t2");
//
//        t1.start();
//        t2.start();



        Thread t1 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if (flag == 0) {
                        log.info("{}", ++count);
                        flag = 1;
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if (flag == 1) {
                        log.info("{}", ++count);
                        flag = 2;
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if (flag == 2) {
                        log.info("{}", ++count);
                        flag = 0;
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

    }
}
