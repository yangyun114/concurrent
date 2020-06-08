package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

@Slf4j(topic = "TestAtomic")
public class TestAtomic {
    public static void main(String[] args) {
        // 无参即初始值为0，可以接收参数设定参数值
        AtomicInteger i = new AtomicInteger(0);

        // 第一个入参为期望值，第二个入参为更新值，如果期望值与主存中的最新的值相等则修改为更新值，反之修改失败
        i.compareAndSet(1, 2);

        // 自增并且获取，等价于++i，这四个方法都是原子操作
        i.incrementAndGet();
        i.decrementAndGet();

        // 先获取再自增，等价于i++
        i.getAndIncrement();
        i.getAndDecrement();

        // 获取到最新值
        i.get();

        // 获取并且增加，增加然后获取
        i.getAndAdd(5);
        i.addAndGet(5);

        // lambda表达式用来进行复杂的运算，value代表读取到的值，value * 10代表要设置的值
        i.updateAndGet(value -> value * 10);

    }
}
