package lession3_10;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestSleepAndYield")
public class TestSleep {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread("t") {
            @Override
            public void run() {
                try {
                    log.info("enter sleep...");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.info("wake up...");
                    e.printStackTrace();
                }
            }
        };
        t.start();

        Thread.sleep(1000);
        log.info("interrupt...");
        t.interrupt();
    }


}
