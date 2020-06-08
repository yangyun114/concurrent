package lession7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

@Slf4j(topic = "TestExecutorsCache")
public class TestExecutorsCache {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        pool.execute(() -> {
            log.info("1");
        });

        pool.execute(() -> {
            log.info("2");
        });

        pool.execute(() -> {
            log.info("3");
        });


    }
}
