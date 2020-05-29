package lession3_10;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestInterruptTwo")
public class TestInterruptTwo {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.info("被打断，推出");
                    break;
                }
            }
        }, "t");
        t.start();
        log.info("interrupt...");
        t.interrupt();
    }
}
