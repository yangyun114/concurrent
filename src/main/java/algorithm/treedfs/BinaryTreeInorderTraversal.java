package algorithm.treedfs;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

//非递归方式，中序遍历二叉树
@Slf4j(topic = "BinaryTreeInorderTraversal")
public class BinaryTreeInorderTraversal {
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
        root.right = new Node(3);
        solve(root);
    }

    private static void solve(Node root) {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            // 重点是递归把当前节点所有的左子树节点添加到栈中
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            // 添加完了之后出栈
            node = stack.pop();
            log.info("{}", node.value);
            node = node.right;
        }
    }
}
