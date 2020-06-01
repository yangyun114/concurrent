package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

@Slf4j(topic = "TestGuardedTwo")
public class TestGuardedTwo {
    public static void main(String[] args) throws InterruptedException {
        // 产生3个居民
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        Thread.sleep(2000);

        for (Integer id : MailBoxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }
    }
}

@Slf4j(topic = "People")
class People extends Thread {
    // 拿信
    @Override
    public void run() {
        GuardedObjectTwo guardedObjectTwo = MailBoxes.createGuardedObjectTwo();
        log.info("开始收信 id:{}", guardedObjectTwo.getId());
        Object mail = guardedObjectTwo.get();
        log.info("收到信了 id:{}, 内容:{}", guardedObjectTwo.getId(), mail);
    }
}

@Slf4j(topic = "Postman")
class Postman extends Thread {
    // 收信
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObjectTwo guardedObjectTwo = MailBoxes.getGuardedObjectTwo(id);
        log.info("送信 id:{}, mail:{}", id, mail);
        guardedObjectTwo.complete(mail);
    }
}

class MailBoxes {
    private static Map<Integer, GuardedObjectTwo> boxes = new Hashtable<>();

    // 产生唯一id
    private static int id = 1;
    private static synchronized int generateId() {
        return id++;
    }

    public static GuardedObjectTwo createGuardedObjectTwo () {
        GuardedObjectTwo go = new GuardedObjectTwo(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static GuardedObjectTwo getGuardedObjectTwo(int id) {
        // 使用remove，减小static变量所占的内存
        return boxes.remove(id);
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

class GuardedObjectTwo {
    // 唯一标识
    private int id;

    // 结果
    private Object response;

    public GuardedObjectTwo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // 给获取结果的线程使用
    public Object get(){
        synchronized (this) {
            // 为空则等待
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            }
            return response;
        }
    }

    public Object get(long timeout) throws InterruptedException {
        synchronized (this) {
            // 开始时间
            long begin = System.currentTimeMillis();
            // 经历的时间
            long passed = 0;
            // 为空则等待
            while (response == null) {
                long wait = timeout - passed;
                if (wait <= 0) {
                    break;
                }
                // 可能会有虚假唤醒问题，所以不能直接使用timeout
                this.wait(wait);
                passed = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    // 给结果完成的线程使用
    public void complete (Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
