package lession4;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestCount")
public class TestCount {
    private static int counter = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        log.info("start...");
        Thread add = new Thread(() -> {
            for (int i = 0;i < 5000;i++) {
                synchronized (lock) {
                    counter--;
                }
            }
        }, "add");

        Thread sub = new Thread(() -> {
            for (int i = 0;i < 5000;i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        }, "sub");
        sub.start();
        add.start();

        add.join();
        sub.join();

        log.info("counter = {}", counter);
    }


}
