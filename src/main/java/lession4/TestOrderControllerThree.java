package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "TestOrderControllerThree")
public class TestOrderControllerThree {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> {
            awaitSignal.print("a", a, b);
        }, "a").start();

        new Thread(() -> {
            awaitSignal.print("b", b, c);
        }, "b").start();

        new Thread(() -> {
            awaitSignal.print("c", c, a);
        }, "c").start();

        Thread.sleep(1000);
        awaitSignal.lock();
        a.signal();
        awaitSignal.unlock();
    }
}

@Slf4j(topic = "AwaitSignal")
class AwaitSignal extends ReentrantLock {
    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    // 参数1，要打印的内容，参数2，进入哪一间休息室，参数3，唤醒哪一间休息室
    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                log.info(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}
