package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@Slf4j(topic = "TestFieldUploader")
public class TestFieldUploader {
    public static void main(String[] args) {
        Student stu = new Student("小王");
        AtomicReferenceFieldUpdater updater =
                AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        // 第一个是要更新的对象，第二是初始值，第三个是更新后的值
        log.info("{}", updater.compareAndSet(stu, "小", "张三"));
        log.info("{}", stu);
    }

}

class Student {
    // 必须结合volatile
    volatile String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
