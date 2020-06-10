package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

//填充所有节点的next指针，指向它右兄弟节点。如果没有右兄弟节点，则应该将next指针设置为NULL。
//初始时，所有的next指针都为NULL
//注意：
//你只能使用常量级的额外内存空间
//可以假设给出的二叉树是一个完美的二叉树(即，所有叶子节点都位于同一层，而且每个父节点都有两个孩子节点)。
@Slf4j(topic = "PopulatingNextRightPointersInEachNode")
public class PopulatingNextRightPointersInEachNode {
    private static class Node {
        int value;
        Node left;
        Node right;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        solve(root);
    }

    private static void solve(Node root) {
        // 存放node
        List<Node> list = new ArrayList<>();
        // 使用层次遍历
        LinkedList<Node> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            Node node = queue.removeLast();
            list.add(node);
            if (node.left != null) {
                queue.addFirst(node.left);
            }
            if (node.right != null) {
                queue.addFirst(node.right);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (match(i + 2)) {
                list.get(i).next = null;
            } else {
                list.get(i).next = list.get(i + 1);
            }
        }

        list.forEach(i -> {
            log.info("结点值:{}, 左结点:{}, 右结点:{}, next结点:{}", i.value,
                    i.left == null ? null : i.left.value,
                    i.right == null ? null : i.right.value,
                    i.next == null ? null : i.next.value);
        });
    }

    // 判断一个数是不是2的n次方
    private static boolean match(int n) {
        int i = 0;
        while (1 << i < n) {
            ++i;
        }
        if (1 << i == n) {
            return true;
        }
        return false;
    }
}
