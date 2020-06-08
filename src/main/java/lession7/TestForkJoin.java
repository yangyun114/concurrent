package lession7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Slf4j(topic = "TestForkJoin")
public class TestForkJoin {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        MyTask task = new MyTask(100);
        Integer sum = pool.invoke(task);
        log.info("{}", sum);
    }
}

// 求1 + 2 + ... + n的值
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            return 1;
        }
        MyTask t1 = new MyTask(n - 1);
        // 执行子任务
        t1.fork();
        // 获取子任务的结果
        return n + t1.join();
    }
}
