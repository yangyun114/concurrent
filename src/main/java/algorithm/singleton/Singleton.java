package algorithm.singleton;

// 饥汉式，默认是线程安全的，在类加载的时候，单例就已经创建好了
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton(){

    }

    public static Singleton getInstance() {
        return instance;
    }
}

// 懒汉式，非线程安全
class SingletonTwo {
    private static SingletonTwo instance;

    private SingletonTwo() {

    }

    public static SingletonTwo getInstance() {
        // 多线程时，这里可能会导致创建类2个实例
        if (instance == null)
            instance = new SingletonTwo();
        return instance;
    }
}

// 线程安全的懒汉
class SingletonThree {
    private static SingletonThree instance;

    private SingletonThree() {

    }

    public static synchronized SingletonThree getInstance() {
        // 通过加锁的方式，保证在同一时间，即使有多个线程调用，也只会生成一个实例
        // 每次生成实例，都要加锁，效率较低
        if (instance == null)
            instance = new SingletonThree();
        return instance;
    }
}

// 双重检验锁的懒汉
class SingletonFour {
    // 可能存在指令重排，一个线程刚创建完，但是还没有赋值成功，另外一个线程直接就返回出去了
    // 用volatile修饰就不会出现这个问题
    private static volatile SingletonFour instance;

    private SingletonFour() {

    }

    public static SingletonFour getInstance() {
        // 在生成实例之后，不会再获取锁了
        if (instance == null) {
            // 第一次生成，可能出现竞争
            synchronized (SingletonFour.class) {
                // 出现竞争之后，在同步代码块外的部分并不能得知其它线程是否已经生成实例，所以需要再进行判断
                if (instance == null)
                    instance = new SingletonFour();
            }
        }
        return instance;
    }
}

// 静态内部类实现
class SingletonFive {
    private static class SingletonHolder {
        // 这里是私有的，但是可以被外部类访问到
        private static final SingletonFive INSTANCE = new SingletonFive();
    }

    private SingletonFive() {

    }

    // 利用了虚拟机加载类的特性，当没有被调用时，内部类是不会被加载的
    // 当调用类此方法，内部类才会被加载
    public static final SingletonFive getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

class User {}
enum SingletonSix {
    INSTANCE;
    // 假定User是我们要使用的单例资源
    private User user;

    SingletonSix() {
        this.user = new User();
    }

    public User getInstance() {
        return user;
    }
}

class TestSingleton {
    public static void main(String[] args) {
        User user = SingletonSix.INSTANCE.getInstance();
        User user2 = SingletonSix.INSTANCE.getInstance();
        System.out.println(user == user2);
    }
}