package lession4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAccount {
    public static void main(String[] args) {
        AccountTwo account = new AccountUnsafe(10000);
        AccountTwo.demo(account);

        AccountTwo account1 = new AccountCas(10000);
        AccountTwo.demo(account1);
    }
}

// 无锁技术
class AccountCas implements AccountTwo {

    private AtomicInteger balance;

    public AccountCas(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        /*while (true) {
            // 局部变量是在工作内存中，不在主存
            // 获取余额的最新值
            int prev = balance.get();
            // 修改后的余额
            int next = prev - amount;
            // 真正修改，获取到的最新值和要修改之后的值
            if (balance.compareAndSet(prev, next))
                break;
        }*/
//        balance.addAndGet(-1 * amount);
        balance.updateAndGet(value -> value - amount);
    }
}

class AccountUnsafe implements AccountTwo {

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        this.balance -= amount;
    }
}

interface AccountTwo {

    // 获取余额
    Integer getBalance();

    // 取款
    void withdraw(Integer amount);

    static void demo(AccountTwo account) {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            // 模拟1000个人取款
            Thread t = new Thread(() -> {
                account.withdraw(10);
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
