package lession7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "TestExecutorsScheduled")
public class TestExecutorsScheduled {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.schedule(() -> {
            log.info("1");
        }, 1, TimeUnit.SECONDS);

        // 第二个参数是初始延迟，第三个参数是间隔时间
        pool.scheduleAtFixedRate(() -> {
            log.info("2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1,  TimeUnit.SECONDS);

        // 第二个是初始延迟，第三个是任务与任务之间的间隔
        pool.scheduleWithFixedDelay(() -> {}, 1, 1, TimeUnit.SECONDS);

    }
}
