package test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class TestReflection {
    @SneakyThrows
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Object obj = new Person("xiao ming");
        // 获取到类实例
        Class<?> cls = obj.getClass();
        // 获取到对应的属性
        Field field = cls.getDeclaredField("name");
        // 忽略掉这个属性是不是私有的
        field.setAccessible(true);
        // 调用field的get方法来获取值
        Object value = field.get(obj);
        log.info("{}", value);
        // 调用set方法来重置field
        field.set(obj, "xiao hong");
        Object value2 = field.get(obj);
        log.info("{}", value2);

        // 获取方法
        Method method = cls.getDeclaredMethod("getName");
        String name = (String) method.invoke(obj);
        log.info("{}", name);
        Method getScore = cls.getDeclaredMethod("getScore", String.class, String.class);
        int score = (int) getScore.invoke(obj, "", "");
        log.info("{}", score);

        // 构造方法
        Constructor<?> constructor = cls.getConstructor(String.class);
        Person xiao_li = (Person) constructor.newInstance("xiao li");
        log.info("{}", xiao_li.getName());
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore(String name, String num) {
        return 99;
    }
}
