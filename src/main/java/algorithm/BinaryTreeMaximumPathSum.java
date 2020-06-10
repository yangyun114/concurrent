package algorithm;

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

    private static int solve(Node node) {
        if (node == null) {
            return 0;
        }
        // 跟0对比取最大值的原因是，结点值可能为负，如果为负的话，那么不走这个结点，sum的值会更大
        int left = Math.max(0, solve(node.left));
        int right = Math.max(0, solve(node.right));
        sum = Math.max(sum, left + right + node.value);
        return Math.max(left, right) + node.value;
    }
}
