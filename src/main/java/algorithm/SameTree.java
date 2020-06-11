package algorithm;

import lombok.extern.slf4j.Slf4j;

//给出两个二叉树，请写出一个判断两个二叉树是否相等的函数。
//判断两个二叉树相等的条件是：两个二叉树的结构相同，并且相同的节点上具有相同的值
@Slf4j(topic = "SameTree")
public class SameTree {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(2);
        root1.left.left = new Node(3);
        root1.left.right = new Node(4);
        root1.right.left = new Node(4);
        root1.right.right = new Node(3);

        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(2);
        root2.left.left = new Node(3);
        root2.left.right = new Node(4);
        root2.right.left = new Node(4);

        boolean flag = solve(root1, root2);
        log.info("{}", flag);
    }

    private static boolean solve(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        if (node1.value != node2.value) {
            return false;
        } else {
            return solve(node1.left, node2.left) && solve(node1.right, node2.right);
        }
    }
}
