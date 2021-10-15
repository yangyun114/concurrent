package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Stack;

// 求给定二叉树的后序遍历
@Slf4j(topic = "BinaryTreePostorderTraversal")
public class BinaryTreePostorderTraversal {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Node root = new Node(1);

        LinkedList<Node> queue = new LinkedList<>();
        queue.addFirst(root);
        for (int i = 1; i < arr.length; i++) {
            Node node = queue.getLast();
            if (!queue.isEmpty()) {
                if (i % 2 == 1) {
                    // 单数为左子树
                    node.left = new Node(arr[i]);
                    queue.addFirst(node.left);
                } else {
                    node.right = new Node(arr[i]);
                    queue.addFirst(node.right);

                    // 右子树生成完毕，出队
                    queue.removeLast();
                }
            }
        }

        log.info("{}", root.value);
        log.info("{}", root.left.value);
        log.info("{}", root.right.value);
        log.info("{}", root.left.left.value);
        log.info("{}", root.left.right.value);

        post1(root);
    }

    private static void post(Node root) {
        if (root == null) {
            return;
        }
        post(root.left);
        post(root.right);
        log.info("{}", root.value);
    }

    private static void post1(Node root) {
        // 用一个栈记录访问的子树
        Stack<Integer> flagStack = new Stack<>();
        // 记录结点的栈
        Stack<Node> nodeStack = new Stack<>();

        if (root == null) {
            return;
        }

        nodeStack.push(root);
        flagStack.push(0);
        while (!nodeStack.isEmpty()) {
            // peek返回头元素
            Node node = nodeStack.peek();
            Integer flag = flagStack.peek();

            // 后序
//            post(flag, node, flagStack, nodeStack);

            // 先序
//            pre(flag, node, flagStack, nodeStack);

            // 中序
            in(flag, node, flagStack, nodeStack);
        }

    }

    private static void in(Integer flag, Node node, Stack<Integer> flagStack, Stack<Node> nodeStack) {
        if (0 == flag) {
//            log.info("{}", node.value);
            // 0表示左右子树未访问
            flagStack.pop();
            flagStack.push(1);
            if (node.left != null) {
                nodeStack.push(node.left);
                flagStack.push(0);
            }
        }

        if (1 == flag) {
            log.info("{}", node.value);
            // 1仅仅访问过左子树
            flagStack.pop();
            flagStack.push(2);
            if (node.right != null) {
                nodeStack.push(node.right);
                flagStack.push(0);
            }
        }

        if (2 == flag) {
            // 2表示左右子树都访问过了
//            log.info("{}", node.value);
            flagStack.pop();
            nodeStack.pop();
        }
    }

    private static void pre(Integer flag, Node node, Stack<Integer> flagStack, Stack<Node> nodeStack) {
        if (0 == flag) {
            log.info("{}", node.value);
            // 0表示左右子树未访问
            flagStack.pop();
            flagStack.push(1);
            if (node.left != null) {
                nodeStack.push(node.left);
                flagStack.push(0);
            }
        }

        if (1 == flag) {
            // 1仅仅访问过左子树
            flagStack.pop();
            flagStack.push(2);
            if (node.right != null) {
                nodeStack.push(node.right);
                flagStack.push(0);
            }
        }

        if (2 == flag) {
            // 2表示左右子树都访问过了
//            log.info("{}", node.value);
            flagStack.pop();
            nodeStack.pop();
        }
    }

    private static void post(Integer flag, Node node, Stack<Integer> flagStack, Stack<Node> nodeStack) {
        if (0 == flag) {
            // 0表示左右子树未访问
            flagStack.pop();
            flagStack.push(1);
            if (node.left != null) {
                nodeStack.push(node.left);
                flagStack.push(0);
            }
        }

        if (1 == flag) {
            // 1仅仅访问过左子树
            flagStack.pop();
            flagStack.push(2);
            if (node.right != null) {
                nodeStack.push(node.right);
                flagStack.push(0);
            }
        }

        if (2 == flag) {
            // 2表示左右子树都访问过了
            log.info("{}", node.value);
            flagStack.pop();
            nodeStack.pop();
        }

    }

}
