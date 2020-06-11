package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

//给定一个二叉树，返回该二叉树由底层到顶层的层序遍历，（从左向右，从叶子节点到根节点，一层一层的遍历）
//例如：
//给定的二叉树是{3,9,20,#,#,15,7},
//     3↵   / ↵  9  20↵    /  ↵   15   7
//该二叉树由底层到顶层层序遍历的结果是
// [↵  [15,7]↵  [9,20],↵  [3],↵]
@Slf4j(topic = "BinaryTreeLevelOrderTraversal")
public class BinaryTreeLevelOrderTraversal {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private static List<List<Integer>> list = new ArrayList<>();
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        solve(root);
    }

    // 层次遍历
    private static void solve(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 获取队列当前的长度，这个长度一定是同一层结点的个数
            List<Integer> item = new ArrayList<>();
            int curSize = queue.size();
            // for循环解锁之后，原来的那一层结点已经遍历完毕，里面的元素全部是新一层的元素
            for (int i = 0; i < curSize; i++) {
                Node node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                item.add(node.value);
            }
            list.add(item);
        }
        Collections.reverse(list);
        log.info("{}", list);
    }
}
