package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

// 给定中序和前序遍历，重建二叉树
@Slf4j
public class ConstructBinaryTreeFromInOrderAndPreOrderTraversal {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
//        List<Integer> inOrder = Arrays.asList(4,7,2,1,5,3,8,6);
//        List<Integer> preOrder = Arrays.asList(1,2,4,7,3,5,6,8);

        List<Integer> in = Arrays.asList(4,7,2);
        List<Integer> pre = Arrays.asList(2,4,7);

        Node root = solve(in, pre);
    }

    // 报错了，需要调试
    private static Node solve(List<Integer> in, List<Integer> pre) {
        if (pre.isEmpty())
            return null;

        Integer value = pre.get(0);
        int index = in.indexOf(value);

        Node root = new Node(value);

        List<Integer> newInLeft = in.subList(0, index);
        List<Integer> newPreLeft = pre.subList(1, index + 1);

        List<Integer> newInRight = in.subList(index + 1, in.size());
        List<Integer> newPreRight = pre.subList(index + 2, pre.size());


        root.left = solve(newInLeft, newPreLeft);
        root.right = solve(newInRight, newPreRight);
        return root;
    }
}
