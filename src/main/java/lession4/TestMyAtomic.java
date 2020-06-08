package lession4;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Slf4j(topic = "TestMyAtomic")
public class TestMyAtomic {

}

class MyAtomicInteger {
    // 声明一个值
    private volatile int value;
    // 偏移量
    private static final long valueOffSet;
    // 用来实现cas
    private static final Unsafe UNSAFE;
    static {
        UNSAFE = Unsafe.getUnsafe();
        try {
            valueOffSet = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            // 静态代码块无法抛出异常，需要转换为运行时异常
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getValue() {
        return value;
    }

    public void decrement(int amount) {
        while (true) {
            int prev = this.value;
            int next = prev - amount;
            if (UNSAFE.compareAndSwapInt(this, valueOffSet, prev, next)) {
                break;
            }
        }
    }
}

class UnsafeUtil {
    private static final Unsafe UNSAFE;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }
}
