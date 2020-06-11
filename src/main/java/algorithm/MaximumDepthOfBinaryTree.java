package algorithm;

import lombok.extern.slf4j.Slf4j;

//求给定二叉树的最大深度，
//最大深度是指树的根结点到最远叶子结点的最长路径上结点的数量。
@Slf4j(topic = "MaximumDepthOfBinaryTree")
public class MaximumDepthOfBinaryTree {
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
        int depth = solve(root);
        log.info("{}", depth);
    }

    private static int solve(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(solve(node.left), solve(node.right)) + 1;
    }
}
