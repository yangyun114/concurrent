package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

//给定一个值n,请生成所有的存储值1...n.的二叉搜索树（BST）的结构
//例如：
//给定n=3，你的程序应该给出下面五种不同的二叉搜索树（BST）
@Slf4j(topic = "UniqueBinarySearchTrees")
public class UniqueBinarySearchTrees {
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int n = 3;
        solve(1, n, null);
    }

    private static void solve(int start, int end, Node item) {
        if (start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
        }
    }

    private static void traverse(Node node) {
        if (node == null) {
            return;
        }
        log.info("{}", node.value);
        traverse(node.left);
        traverse(node.right);
    }
}
