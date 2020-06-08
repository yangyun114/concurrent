package lession4;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestHappensBefore")
public class TestHappensBefore {
    static int i;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (i == 0) {

            }
        }, "t1").start();

        Thread.sleep(1000);
        log.info("停止...");
        new Thread(() -> {
            i = 1;
        }, "t2").start();
    }

}
