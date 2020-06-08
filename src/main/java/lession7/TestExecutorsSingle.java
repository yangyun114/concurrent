package lession7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j(topic = "TestExecutorsSingle")
public class TestExecutorsSingle {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        pool.execute(() -> {
            log.info("1");
            int i = 1 / 0;
        });

        pool.execute(() -> {
            log.info("2");
        });

        pool.execute(() -> {
            log.info("3");
        });
    }
}
