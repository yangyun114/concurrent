package lession3_10;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "TestJoin")
public class TestJoin {
    private static int i = 0;
    private static int j = 0;

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = 10;
        };
        Thread t = new Thread(runnable, "t");
        t.start();
        // join方法等待t线程运行结束
        t.join();

        Runnable runnable1 = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j = 10;
        };
        Thread t1 = new Thread(runnable1, "t1");
        t1.start();
        t1.join();
        log.info("i = {}, j = {}", i, j);
    }
}
