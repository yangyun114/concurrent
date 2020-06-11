package algorithm;

import lombok.extern.slf4j.Slf4j;

//判断一个二叉树是否是对称的
//例如：下面这棵二叉树是对称的
//    1↵   / ↵  2   2↵ /  / ↵3  4 4  3↵
//
//下面这棵二叉树不对称。
//    1↵   / ↵  2   2↵      ↵   3    3
@Slf4j(topic = "SymmetricTree")
public class SymmetricTree {
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
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(4);
        root.right.right = new Node(3);

        boolean flag = solve(root, root);
        log.info("{}", flag);
    }

    // 跟判断是否为平衡二叉树很类似
    private static boolean solve(Node left, Node right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        if (left.value != right.value) {
            return false;
        } else {
            return solve(left.left, right.right) && solve(left.right, right.left);
        }
    }

    // 额外解法可以使用层序遍历来进行判断
}
