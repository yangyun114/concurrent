package lession3_1;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test3_1")
public class Test3_1 {


    public static void testDaemonThread() throws ExecutionException, InterruptedException {
//        // 线程创建
//        Thread t1 = new Thread("t1"){
//            @Override
//            public void run() {
//                log.info("111");
//            }
//        };
//        // 线程启动
////        t1.setName("t1");
//        t1.start();
//
//        // 使用runnable配合thread，将任务和线程创建分离，推荐使用
//        Runnable runnable = () -> {
//            log.info("222");
//        };
//        Thread t2 = new Thread(runnable, "t2");
//        t2.start();
//
//        // 使用futureTask创建
//        Callable<String> callable = () -> {
//            log.info("333");
//            Thread.sleep(1000);
//            return "你好";
//        };
//        FutureTask<String> task = new FutureTask<>(callable);
//        Thread t3 = new Thread(task, "t3");
//        t3.start();
//
//        log.info("{}", task.get());

        // 创建线程
        Thread t1 = new Thread(() -> {
            log.info("t1 run...");
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        log.info("main run");


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("000");
        testDaemonThread();
    }

}
