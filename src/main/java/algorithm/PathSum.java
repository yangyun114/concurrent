package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

//给定一个二叉树和一个值sum，请找出所有的根节点到叶子节点的节点值之和等于sum的路径，
//例如：
//给出如下的二叉树，sum=22，
//              5↵             / ↵            4   8↵           /   / ↵          11  13  4↵         /      / ↵        7    2  5   1
//返回
//[↵   [5,4,11,2],↵   [5,8,4,5]↵]↵
@Slf4j(topic = "PathSum")
public class PathSum {
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(4);
        root.right = new Node(8);
        root.left.left = new Node(11);
        root.left.right = new Node(13);
        root.right.left = new Node(4);
        root.left.left.left = new Node(7);
        root.left.left.right = new Node(2);
        root.right.left.left = new Node(5);
        root.right.left.right = new Node(1);

        int sum = 22;
        solve(root, sum, new ArrayList<>());
    }

    private static void solve(Node node, int sum, ArrayList<Integer> item) {
        if (node == null) {
            if (sum == 0) {
                log.info("{}", item);
            }
            return;
        }
        sum -= node.value;
        item.add(node.value);
        solve(node.left, sum, item);
        // 注意这个if，能够去重
        if (node.right != null) {
            solve(node.right, sum, item);
        }
        item.remove(item.size() - 1);
    }
}
