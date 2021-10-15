package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

//给定一个二叉树，返回该二叉树的之字形层序遍历，（从左向右，下一层从右向左，一直这样交替）
//例如：
//给定的二叉树是{3,9,20,#,#,15,7},
//    3↵   / ↵  9  20↵    /  ↵   15   7
//该二叉树之字形层序遍历的结果是
//[↵  [3],↵  [20,9],↵  [15,7]↵]
@Slf4j(topic = "BinaryTreeZigzagLevelOrderTraversal")
public class BinaryTreeZigzagLevelOrderTraversal {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static private List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        solve(root);
        log.info("{}", list);
    }

    private static void solve(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        // 为真，元素正序，为假倒序
        boolean flag = true;
        while (!queue.isEmpty()) {
            // 每次循环时，queue的size即为这一层元素的个数
            int curSize = queue.size();
            List<Integer> item = new ArrayList<>();
            for (int i = 0; i < curSize; i++) {
                Node node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                // for循环，把每一层的元素收集起来
                item.add(node.value);
            }
            // 交替地去翻转list
            if (flag) {
                list.add(item);
                flag = false;
            } else {
                Collections.reverse(item);
                list.add(item);
                flag = true;
            }
        }
    }

}
