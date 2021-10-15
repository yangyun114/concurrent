package test.testAnnotation;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Slf4j
public class TestAnnotation {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person(1);
        check(person);

    }

    static void check(Person person) throws IllegalAccessException {
        // 获取所有的属性
        Field[] fields = person.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 获取到field的Range类型的注解
            Range range = field.getAnnotation(Range.class);

            // 获取到field的值
            int value = (int) field.get(person);
            // 如果值不在范围内，抛出异常
            if (value > range.max() || value < range.min())
                throw new RuntimeException("年龄值不合法...");
        }
    }
}

class Person{
    @Range
    int age;

    public Person(int age) {
        this.age = age;
    }
}
