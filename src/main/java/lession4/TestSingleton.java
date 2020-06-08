package lession4;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

public class TestSingleton {

}

// final修饰符避免子类重写
@Slf4j(topic = "Singleton")
final class Singleton implements Serializable{
    private Singleton() {

    }

    // 饿汉式单例，在第一次使用到Singleton类时就创建好单例对象，不存在线程安全问题
    private static final Singleton INSTANCE = new Singleton();

    public static Singleton getINSTANCE() {
        return INSTANCE;
    }

    // 反序列化会生成新的对象，需要使用readResolve来处理
    public Object readResolve() {
        return INSTANCE;
    }

}

final class SingletonTwo implements Serializable{
    private SingletonTwo() {

    }

    // 懒汉式单例，在第一次调用实例化方法时才创建

    // volatile是避免第一次多线程调用时，t1线程在执行到INSTANCE = new SingletonTwo();时
    // 可能出现先赋值操作导致INSTANCE不为null，并且还没有调用构造方法
    // 此时如果t2线程调用了第47行的代码，就会返回INSTANCE，但是这是错误的结果，因为没有来得及调用构造方法
    // volatile可以有效避免这种情况
    private static volatile SingletonTwo INSTANCE = null;

    public static SingletonTwo getINSTANCE() {
        // 避免在后续每次调用时都调用同步代码
        if (INSTANCE != null) {
            return INSTANCE;
        }
        synchronized (SingletonTwo.class) {
            // 重新判断一次的意义在于避免第一次多线程调用时出现问题
            if (INSTANCE != null) {
                return INSTANCE;
            }
            INSTANCE = new SingletonTwo();
            return INSTANCE;
        }
    }

    // 反序列化会生成新的对象，需要使用readResolve来处理
    public Object readResolve() {
        return INSTANCE;
    }

}

final class SingletonThree {
    private SingletonThree() {

    }

    // 懒汉式单例，因为在第一次使用SingletonThree类时没有创建单例，而是在调用方法时才创建
    // 类的加载需要使用到才进行加载，没有使用则不加载
    private static class LazyHolder {
        static final SingletonThree INSTANCE = new SingletonThree();
    }

    // JVM保证线程安全
    public static SingletonThree getInstance() {
        return LazyHolder.INSTANCE;
    }

}
