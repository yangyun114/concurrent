package test.testTime;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Slf4j
public class testDate {
    public static void main(String[] args) {
        final String[] availableIDs = TimeZone.getAvailableIDs();
        log.info("{}", availableIDs);

        // LocaleDateTime，不带时区获取时间
        LocalDateTime now = LocalDateTime.now();

        // ZonedDateTime，带时区获取时间
        ZonedDateTime now1 = ZonedDateTime.now();

        // 获取时间戳
        Instant now2 = Instant.now();

        // 格式化工具
        DateTimeFormatter fo = DateTimeFormatter.ofPattern("");
    }
}
