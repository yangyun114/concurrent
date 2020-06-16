package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

//二叉搜索树（BST）中的两个节点被错误地交换了，
//请在不改变树的结构的情况下恢复这棵树。
//备注；
//用O(n)的空间解决这个问题的方法太暴力了，你能设计一个常数级空间复杂度的算法么？
@Slf4j(topic = "RecoverBinarySearchTree")
public class RecoverBinarySearchTree {
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
        root.right = new Node(4);
        root.left.right = new Node(1);
        root.right.right = new Node(5);

        solve(root);
    }

    // 最暴力的方法，获取中序遍历的序列，然后构建二叉树
    private static void solve(Node node) {
        if (node == null) {
            return;
        }

    }
}
