package lession3_10;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestState")
public class TestState {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            // new
            @Override
            public void run() {
                log.debug("running...");
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) {
                    // runnable
                }
            }
        };
        t2.start();

        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                log.debug("running...");
            }
        };
        t3.start();

        Thread t4 = new Thread("t4") {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (TestState.class) {
                    Thread.sleep(1000000000);// timed_waiting
                }
            }
        };
        t4.start();

        Thread t5 = new Thread("t5") {
            @SneakyThrows
            @Override
            public void run() {
                t2.join();// waiting
            }
        };
        t5.start();

        Thread t6 = new Thread("t6") {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (TestState.class) {
                    Thread.sleep(1000000000);// blocked
                }
            }
        };
        t6.start();

        log.info("t1 state: {}", t1.getState());
        log.info("t2 state: {}", t2.getState());
        log.info("t3 state: {}", t3.getState());
        log.info("t4 state: {}", t4.getState());
        log.info("t5 state: {}", t5.getState());
        log.info("t6 state: {}", t6.getState());



    }
}
