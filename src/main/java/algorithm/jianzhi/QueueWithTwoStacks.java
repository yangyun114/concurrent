package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

// 用2个栈，实现一个队列
// 用2个队列，实现一个栈
@Slf4j
public class QueueWithTwoStacks {
    private static class MyQueue<T> {

        // 添加
        void offer(T t) {

        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(null);
        queue.poll();
        queue.peek();
    }
}
