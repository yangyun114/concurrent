package lession4;

import lombok.extern.slf4j.Slf4j;

// a线程输出5次a，b线程输出五次b，c线程输出五次c，要求输出abcabcabcabcabc
@Slf4j(topic = "TestOrderControlTwo")
public class TestOrderControlTwo {
    static final Object lock = new Object();
    static int flag = 0;
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (flag != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("a");
                    flag = 1;
                    lock.notifyAll();
                }
            }
        }, "a");

        Thread b = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (flag != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("b");
                    flag = 2;
                    lock.notifyAll();
                }
            }
        }, "b");

        Thread c = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (flag != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("c");
                    flag = 0;
                    lock.notifyAll();
                }
            }
        }, "c");

        a.start();
        b.start();
        c.start();

    }
}
