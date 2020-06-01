package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j(topic = "TestTranfer")
public class TestTranfer {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);

        Thread t1 = new Thread(() -> {
            for (int i = 0;i < 1000;i++) {
                a.transfer(b, randomAccmout());
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0;i < 1000;i++) {
                b.transfer(a, randomAccmout());
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("total:{}", a.getAmount() + b.getAmount());
    }

    // Random类是线程安全的
    static Random random = new Random();

    private static int randomAccmout() {
        return random.nextInt(100) + 1;
    }


}

// 账户
class Account {
    private int amount;

    public Account(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // 入参为目标账户和转账金额
    public void transfer(Account account, int amount) {
        synchronized (Account.class) {
            if (this.getAmount() >= amount) {
                // 如果当前账户大于需要转账的金额，则进行转账，当前金额减少，待转账户增加
                this.setAmount(this.getAmount() - amount);
                account.setAmount(account.getAmount() + amount);
            }
        }
    }
}
