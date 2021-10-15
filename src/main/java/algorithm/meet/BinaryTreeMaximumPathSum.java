package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

//给定一个二叉树，请计算节点值之和最大的路径的节点值之和是多少。
//这个路径的开始节点和结束节点可以是二叉树中的任意节点
//例如：
//给出以下的二叉树，
//       1↵      / ↵     2   3
//返回的结果为6
//意思是，从一个结点出发到达另一个结点，有一个路线，然后对路线上的结点求和，得出和最大的值
@Slf4j(topic = "BinaryTreeMaximumPathSum")
public class BinaryTreeMaximumPathSum {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    static int sum;
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        sum = Integer.MIN_VALUE;
        solve(root);
    }

    // 这个方法，是用来返回当前节点的最大高度的
    private static int solve(Node node) {
        if (node == null)
            return 0;
        // 跟0对比，去除负数的情况
        int left = Math.max(0, solve(node.left));
        int right = Math.max(0, solve(node.right));
        // 最大路径和，等于左子树最大高度加上右子树最大高度，再加上本身
        sum = Math.max(sum, left + right + node.value);
        return Math.max(left, right) + node.value;
    }
}
