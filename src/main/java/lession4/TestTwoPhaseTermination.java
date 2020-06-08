package lession4;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestTwoPhaseTermination")
public class TestTwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination monitor = new TwoPhaseTermination();
        monitor.start();

        Thread.sleep(3500);
        monitor.stop();
    }
}

@Slf4j(topic = "TwoPhaseTermination")
class TwoPhaseTermination {
    // 监控线程
    private Thread monitor;

    private volatile boolean stop = false;

    // 启动监控
    public void start() {
        monitor = new Thread(() -> {
            while (!stop) {
                log.info("监控中...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("料理后事...");
        }, "监视器");
        monitor.start();
    }

    public void stop() {
        log.info("停止监控...");
        stop = true;
        monitor.interrupt();
    }
}
