package lession4;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "TestDeadLock")
public class TestDeadLock {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("c1");
        Chopstick c2 = new Chopstick("c2");
        Chopstick c3 = new Chopstick("c3");
        Chopstick c4 = new Chopstick("c4");
        Chopstick c5 = new Chopstick("c5");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("伽利略", c3, c4).start();
        new Philosopher("阿基米德", c4, c5).start();
        new Philosopher("爱迪生", c5, c1).start();
    }


}

@Slf4j(topic = "Philosopher")
class Philosopher extends Thread {
    private Chopstick left;
    private Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            // 造成死锁
//            synchronized (left) {
//                synchronized (right) {
//                    eat();
//                }
//            }
            if(left.tryLock()) {
                try{
                    if (right.tryLock()) {
                        try {
                            eat();
                        }finally {
                            right.unlock();
                        }
                    }
                }finally {
                    left.unlock();
                }
            }
        }
    }

    private void eat() throws InterruptedException {
        log.info("开始吃饭...");
        Thread.sleep(1000);
    }
}

class Chopstick extends ReentrantLock {
    private String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopsticks{" +
                "name='" + name + '\'' +
                '}';
    }
}
