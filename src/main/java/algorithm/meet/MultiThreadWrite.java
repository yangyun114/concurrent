package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

///*
// 问题：多线程写文件
//
//有四个线程1、2、3、4。线程1的功能就是输出A，线程2的功能就是输出B，以此类推.........
//现在有四个文件file1,file2,file3, file4。初始都为空。
//现要让四个文件呈如下格式：
//file1：A B C D A B....
//file2：B C D A B C....
//file3：C D A B C D....
//file4：D A B C D A....
//*/
@Slf4j
public class MultiThreadWrite {
    static final Object lock = new Object();
    volatile static int flag = 0;

    void print() {
        File file1, file2, file3, file4;
        Thread a = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (flag != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("A");
                    flag = 1;
                    lock.notifyAll();
                }
            }
        }, "A");

        Thread b = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (flag != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("B");
                    flag = 2;
                    lock.notifyAll();
                }
            }
        }, "B");

        Thread c = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (flag != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("C");
                    flag = 3;
                    lock.notifyAll();
                }
            }
        }, "C");

        Thread d = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (flag != 3) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("D");
                    flag = 0;
                    lock.notifyAll();
                }
            }
        }, "D");

        a.start();
        b.start();
        c.start();
        d.start();
    }

    public static void main(String[] args) {

    }


}
