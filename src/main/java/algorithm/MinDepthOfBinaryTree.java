package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 给定一个二叉树，求其最小深度
@Slf4j(topic = "MinDepthOfBinaryTree")
public class MinDepthOfBinaryTree {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(1);
        tree.left = new BinaryTree(1);
        tree.right = new BinaryTree(1);

        tree.left.left = new BinaryTree(1);
        tree.left.right = new BinaryTree(1);

        log.info("{}", solve1(tree));
    }

    // 递归
    private static int solve(BinaryTree tree) {
        if (tree == null) {
            return 0;
        }
        int leftDepth = solve(tree.left);
        int rightDepth = solve(tree.right);
        return leftDepth < rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    // 非递归
    private static int solve1(BinaryTree tree) {
        if (tree == null) {
            return 0;
        }
        int depth = 0;
        LinkedList<BinaryTree> queue = new LinkedList<>();
        queue.addFirst(tree);
        while (!queue.isEmpty()) {
            BinaryTree node = queue.removeLast();
            if (node.left != null && node.right != null) {
                queue.addFirst(node.left);
                queue.addFirst(node.right);
                depth++;
            } else {
                return depth;
            }
        }
        return depth;
    }
}

class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(int value) {
        this.value = value;
    }
}
