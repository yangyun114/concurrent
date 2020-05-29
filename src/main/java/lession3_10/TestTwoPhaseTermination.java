package lession3_10;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestTwoPhaseTermination")
public class TestTwoPhaseTermination {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                // 获得当前线程
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    // 如果被打断，料理后事退出循环
                    log.info("料理后事...");
                    break;
                }
                // 没有被打断，正常走流程
                try {
                    // 情况1被打断，走catch块
                    Thread.sleep(2000);
                    // 情况2被打断，打断标记为true
                    log.info("正在监控...");
                } catch (InterruptedException e) {
                    // 设置打断标记为true
                    currentThread.interrupt();
                    e.printStackTrace();
                }
            }
        }, "t");
        t.start();
        Thread.sleep(10000);
        log.info("停止监控");
        t.interrupt();
    }
}
