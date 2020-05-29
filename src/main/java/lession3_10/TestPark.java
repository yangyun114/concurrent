package lession3_10;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "TestPark")
public class TestPark {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            log.info("park...");
            LockSupport.park();

            log.info("unpark...");
            Thread currentThread = Thread.currentThread();
            log.info("打断标记:{}", Thread.interrupted());

            log.info("park...");
            LockSupport.park();
            log.info("unpark...");

        }, "t");
        t.start();
        Thread.sleep(2000);
        t.interrupt();

        Thread.sleep(2000);
        t.interrupt();
    }

}
