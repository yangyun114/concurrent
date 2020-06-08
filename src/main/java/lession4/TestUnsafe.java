package lession4;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Slf4j(topic = "TestUnsafe")
public class TestUnsafe {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        // 允许获得私有
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        log.info("{}", unsafe);

        // 获取类的域偏移地址
        long idOffSet = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long nameOffSet = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));

        // cas进行修改
        Teacher t = new Teacher();
        unsafe.compareAndSwapInt(t, idOffSet, 0, 1);
        unsafe.compareAndSwapObject(t, nameOffSet, null, "老王");

        log.info("{}", t);

    }
}

class Teacher{
    volatile int id;
    volatile String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
