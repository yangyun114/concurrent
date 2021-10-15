package test;

import java.util.*;

// 集合
public class TestCollection {
    public static void main(String[] args) {
        // 栈
        Stack<Integer> stack = new Stack<>();

        // 队列
        Queue<Integer> queue = new LinkedList<>();

        // 双向队列
        Deque<Integer> deque = new LinkedList<>();

        // 构造不可变空list
        List<Integer> emptyList = Collections.emptyList();

        // 初始化时传值
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){
            {
                put(1, 1);
            }
        };

        //


    }
}
