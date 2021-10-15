package test;

import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

// 枚举
@Slf4j
public class TestEnum {
    public static void main(String[] args) {
        Week week = Week.SUN;
        switch (week) {
            case SUN:
                System.out.println("1 = " + 1);
        }
        week.ordinal();

        // 枚举类型使用EnumMap
        Map<Week, String> map = new EnumMap<>(Week.class);
        map.put(Week.SUN, "星期天");
        log.info("{}", map.get(Week.SUN));
    }
}  

enum Week {
    SUN(0), MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6);

    // 字段应尽量定义为final类型
    private final int weekNum;

    // 构造方法默认是private，所以可省略
    Week(int weekNum) {
        this.weekNum = weekNum;
    }
}
