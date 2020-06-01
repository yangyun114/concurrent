package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j(topic = "TestTicketSell")
public class TestTicketSell {

    public static void main(String[] args) throws InterruptedException {
        TicketWindow window = new TicketWindow(1000);

        // 只在主线程使用
        List<Thread> ThreadList = new ArrayList<>();
        // sellList在多线程中使用
        List<Integer> sellList = new Vector<>();

        for (int i = 0;i < 2000;i++) {
            Thread thread = new Thread(() -> {
                // 随机购买1～5张票
                int sell = window.sell(random());
                // 每次都使用的新的sell
                sellList.add(sell);
            });
            ThreadList.add(thread);
            thread.start();
        }

        // 等待所有线程执行完成
        for (Thread thread : ThreadList) {
            thread.join();
        }

        // 打印售出的票和余票
        log.info("售出：{}", sellList.stream().mapToInt(i -> i).sum());
        log.info("余票：{}", window.getCount());
    }

    private static int random() {
        // 随机生成1～5
        Random random = new Random();
        return random.nextInt(5) + 1;
    }


}

class TicketWindow {
    // 余票数量
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    // 卖票
    public synchronized int sell (int amount) {
        // 如果余票数量大于需要的数量，返回购得的数量即可
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else
            return 0;
    }
}
