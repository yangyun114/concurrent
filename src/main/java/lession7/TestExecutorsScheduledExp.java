package lession7;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "TestExecutorsScheduledExp")
public class TestExecutorsScheduledExp {
    // 要求每周四定时执行任务
    public static void main(String[] args) {
        // 一周时间
        long period = 1000 * 3600 * 24 * 7;
//        long period = 1000;
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取本周四的时间
        LocalDateTime time = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
//        LocalDateTime time = now.withHour(16).withMinute(58).withSecond(0).withNano(0).with(DayOfWeek.FRIDAY);
        // 如果当前比本周四大，则需要加一周
        if (now.compareTo(time) > 0) {
            // 加一周
            time.plusWeeks(1);
        }
        // 当前时间到下一个日期的差值
        long initailDetay = Duration.between(now, time).toMillis();
        log.info("{}", initailDetay);

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.scheduleAtFixedRate(() -> {
            log.info("running...");
        }, initailDetay, period, TimeUnit.MILLISECONDS);
    }
}
