package lession4;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestCountTwo")
public class TestCountTwo {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            room.add();
        }, "t1");
        Thread t2 = new Thread(() -> {
            room.sub();
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("counter = {}", room.getCounter());
    }
}

class Room {
    private int counter = 0;

    public void add() {
        synchronized (this) {
            counter++;
        }
    }

    public synchronized void sub() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }
}

