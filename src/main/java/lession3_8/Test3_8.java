package lession3_8;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test3_8")
public class Test3_8 {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (true) {


                try {
                    Thread.sleep(500);
                    log.info("running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(runnable, "t");
        t.start();
    }
}
