package algorithm.treedfs;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

//给出一棵树的中序遍历和后序遍历，请构造这颗二叉树
//注意：
//保证给出的树中不存在重复的节点
@Slf4j(topic = "ConstructBinaryTreeFromInorderAndPostorderTraversal")
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        List<Integer> in = Arrays.asList(new Integer[]{4, 2, 5, 1, 6, 3, 7});
        List<Integer> post = Arrays.asList(new Integer[]{4, 5, 2, 6, 7, 3, 1});
        Node root = solve(in, post);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            log.info("{}", node.value);
            if (node.left != null)
                queue.offer(node.left);
            if ((node.right != null))
                queue.offer(node.right);
        }

    }

    private static Node solve(List<Integer> in, List<Integer>  post) {
        if (in.size() == 1 && post.size() == 1) {
            return new Node(in.get(0));
        }
        // 获取后序数组中最后一个元素，这个一定是根结点
        int value = post.get(post.size() - 1);
        int index = in.indexOf(value);

        Node root = new Node(value);

        // 截取的时候要注意
        List<Integer> newInLeft = in.subList(0, index);
        List<Integer> newPostLeft = post.subList(0, index);

        List<Integer> newInRight = in.subList(index + 1, in.size());
        List<Integer> newPostRight = post.subList(index, in.size() - 1);

        root.left = solve(newInLeft, newPostLeft);
        root.right = solve(newInRight, newPostRight);

        return root;
    }
}
