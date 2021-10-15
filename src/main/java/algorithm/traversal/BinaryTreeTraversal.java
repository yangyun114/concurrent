package algorithm.traversal;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 给出二叉树的常见遍历方式
@Slf4j(topic = "BinaryTreeTraversal")
public class BinaryTreeTraversal {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        // 递归，先序
        post(root);
        // 非递归，先序
        post1(root);
        // 层次遍历
        level(root);
    }

    private static void level(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            // 使用for循环，记录每一层，如不需要记录则可以不使用
            for (int i = 0; i < curSize; i++) {
                Node node = queue.poll();
                log.info("{}", node.value);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
    }

    private static void post(Node node) {
        if (node == null)
            return;
        log.info("{}", node.value);
        post(node.left);
        post(node.right);
    }

    private static void post1(Node root) {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                // log在这里，为先序
                log.info("{}", node.value);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // log在这里，为中序
            node = node.right;
            // 后序较难，暂不处理
        }
    }
}
