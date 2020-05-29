package lession3_10;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "TestInterrupt")
public class TestInterrupt {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            log.info("sleeping...");
            Thread.sleep(2000);
            return 1;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread t = new Thread(futureTask, "t");
        t.start();
//        log.info("interrupt");
//        t.interrupt();
//        log.info("{}", futureTask.get());
//        log.info("打断标记：{}", t.isInterrupted());

        Thread t1 = new Thread(() -> {
            log.info("sleeping...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        log.debug("interrupt");
        t1.interrupt();
        log.debug("打断标记：{}", t1.isInterrupted());
    }
}
