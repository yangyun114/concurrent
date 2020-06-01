package lession3_10;

import lombok.extern.slf4j.Slf4j;
import utils.Sleeper;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "TestTea")
public class TestTea {

    public static void main(String[] args) {
        test1();
        test2();
        Sleeper.sleep(20000);
    }

    private static void test2() {
        Runnable runnable = () -> {
            try {
                log.info("洗水壶...");
                TimeUnit.SECONDS.sleep(2);

                log.info("烧水...");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable, "工具人1");
        thread.start();
    }

    private static void test1() {
        Runnable runnable = () -> {
            try {
                log.info("洗茶壶...");
                TimeUnit.SECONDS.sleep(2);

                log.info("洗茶杯...");
                TimeUnit.SECONDS.sleep(2);

                log.info("拿茶叶...");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable, "工具人2");
        thread.start();
    }


}
