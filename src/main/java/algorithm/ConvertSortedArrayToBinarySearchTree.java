package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给出一个升序排序的数组，将其转化为平衡二叉搜索树（BST）.
@Slf4j(topic = "ConvertSortedArrayToBinarySearchTree")
public class ConvertSortedArrayToBinarySearchTree {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Node root = solve(list);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            log.info("{}", node.value);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
        log.info("{}", root.value);
        log.info("{}", root.left.value);
        log.info("{}", root.right.value);
        log.info("{}", root.left.right.value);
        log.info("{}", root.right.right.value);

    }

    private static Node solve(List<Integer> arr) {
        if (arr.isEmpty())
            return null;
        int middle = (arr.size() - 1) / 2;
        Node root = new Node(arr.get(middle));
        root.left = solve(arr.subList(0, middle));
        root.right = solve(arr.subList(middle + 1, arr.size()));
        return root;
    }
}
