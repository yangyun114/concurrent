package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "TestOrderControlFour")
public class TestOrderControlFour {
    static Thread a;
    static Thread b;
    static Thread c;
    public static void main(String[] args) {
        ParkUnpark parkUnpark = new ParkUnpark(5);
        a = new Thread(() -> {
            parkUnpark.print("a", b);
        }, "a");

        b = new Thread(() -> {
            parkUnpark.print("b", c);
        }, "b");

        c = new Thread(() -> {
            parkUnpark.print("c", a);
        }, "c");

        a.start();
        b.start();
        c.start();

        LockSupport.unpark(a);

    }
}

@Slf4j(topic = "ParkUnpark")
class ParkUnpark {
    private int loopNumber;

    public ParkUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < 5; i++) {
            LockSupport.park();
            log.info(str);
            LockSupport.unpark(next);
        }
    }
}
