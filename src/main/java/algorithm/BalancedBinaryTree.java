package algorithm;

import lombok.extern.slf4j.Slf4j;

//判断给定的二叉树是否是平衡的
//在这个问题中，定义平衡二叉树为每个节点的左右两个子树高度差的绝对值不超过1的二叉树
@Slf4j(topic = "BalancedBinaryTree")
public class BalancedBinaryTree {
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
        root.left.right = new Node(3);

        boolean solve = solve(root);
        log.info("{}", solve);
    }

    private static boolean solve(Node node) {
        if (node == null) {
            return true;
        }
        int left = getDepth(node.left);
        int right = getDepth(node.right);
        if (left - right < 2 && left - right > -2) {
            // 左右子树必须都是平衡二叉树
            return solve(node.left) && solve(node.right);
        } else {
            return false;
        }
    }

    // 用来返回某个结点的高度
    private static int getDepth(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }
}
