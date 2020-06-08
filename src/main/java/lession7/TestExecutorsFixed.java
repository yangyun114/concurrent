package lession7;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j(topic = "TestExecutors")
public class TestExecutorsFixed {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 固定大小线程池
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "my_pool_" + t.getAndIncrement());
            }
        });

        // execute方法
//        pool.execute(() -> {
//            log.info("1");
//        });
//
//        pool.execute(() -> {
//            log.info("2");
//        });
//
//        pool.execute(() -> {
//            log.info("3");
//        });

        // submit方法，用来获取带返回值的情况
//        Future<String> submit = pool.submit(() -> {
//            log.info("running...");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "ok";
//        });
//        log.info(submit.get());

//        List<Callable<String>> taskList = Arrays.asList(() -> {
//            log.info("begin");
//            Thread.sleep(1000);
//            return "1";
//        }, () -> {
//            log.info("begin");
//            Thread.sleep(2000);
//            return "2";
//        }, () -> {
//            log.info("begin");
//            Thread.sleep(2000);
//            return "3";
//        });
//        // 接受任务集合，执行集合中的所有任务，可以通过传入限制时间
//        List<Future<String>> futures = pool.invokeAll(taskList);
//        futures.forEach(f -> {
//            try {
//                log.info("{}", f.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        });

        // 接收集合，不执行所有任务，只要有一个任务执行成功，则返回这个任务的执行结果，其他任务不会执行
//        String s = pool.invokeAny(taskList);
//        log.info(s);

        // shutdown方法把状态从running 变为 shutdown
        pool.shutdown();

        pool.shutdownNow();

    }
}
