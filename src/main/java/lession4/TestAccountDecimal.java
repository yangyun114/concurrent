package lession4;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j(topic = "TestAccountDecimal")
public class TestAccountDecimal {
    public static void main(String[] args) {
        AccountDecimal account = new AccountDecimalCas(new BigDecimal(10000));
        AccountDecimal.demo(account);
    }

}

class AccountDecimalCas implements AccountDecimal {

    private AtomicReference<BigDecimal> balance;

    public AccountDecimalCas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
//        while (true) {
//            BigDecimal pre = balance.get();
//            BigDecimal next = pre.subtract(amount);
//            if (balance.compareAndSet(pre, next)) {
//                break;
//            }
//        }
        balance.updateAndGet(value -> value.subtract(amount));
    }
}

interface AccountDecimal {
    // 获取余额
    BigDecimal getBalance();

    // 取款
    void withdraw(BigDecimal amount);

    static void demo(AccountDecimal account) {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            // 模拟1000个人取款
            Thread t = new Thread(() -> {
                account.withdraw(new BigDecimal(10));
            }, "t" + i);
            threadList.add(t);
        }
        long start = System.nanoTime();
        // 让每个线程都启动
        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost: " + (end - start) / 1000_000 + " ms");
    }
}
