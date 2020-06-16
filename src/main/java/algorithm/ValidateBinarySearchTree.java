package algorithm;

import lombok.extern.slf4j.Slf4j;

//判断一个二叉树是否是一个二叉搜索树
@Slf4j(topic = "ValidateBinarySearchTree")
public class ValidateBinarySearchTree {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(2);
//        root.right = new Node(4);
//        root.left.left = new Node(1);
//        root.right.right = new Node(5);

        boolean flag = solve(root);
        log.info("{}", flag);
    }

    private static boolean solve(Node node) {
        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return true;
        }
        if (node.left != null && node.value < node.left.value) {
            return false;
        }
        if (node.right != null && node.right.value < node.value) {
            return false;
        }
        return solve(node.left) && solve(node.right);
    }
}
