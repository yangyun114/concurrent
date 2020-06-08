package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "TestPark")
public class TestPark {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                log.info("开始...");
                Thread.sleep(2000);
                log.info("park...");
                LockSupport.park();

                LockSupport.park();
                Thread.sleep(2000);
                log.info("park结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();

        Thread.sleep(1000);
        log.info("unpark...");
        LockSupport.unpark(t1);
    }
}
