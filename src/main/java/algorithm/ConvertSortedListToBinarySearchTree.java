package algorithm;

import lombok.extern.slf4j.Slf4j;

//给定一个单链表，其中的元素按升序排序，请将它转化成平衡二叉搜索树（BST）
//平衡二叉搜索树，即根结点大于左子树值，小于右子树值，同时它是一个平衡二叉树
@Slf4j(topic = "ConvertSortedListToBinarySearchTree")
public class ConvertSortedListToBinarySearchTree {
    // 链表结点
    private static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
    // 树结点
    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        TreeNode root = solve(head, null);
        traverse(root);

    }

    // 中序遍历二叉树
    private static void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        log.info("{}", node.value);
        traverse(node.left);
        traverse(node.right);
    }

    private static TreeNode solve(ListNode head, ListNode tail) {
        // 引入tail的目的是，不去改变原有链表的结构，因为把slow.next == null，会截断链表
        if (head == tail) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast.next != tail && fast.next.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 如果是单数，slow在中间位置停止，如果双数，slow在中间位置的前一个结点停止，
        // 可以理解为slow为绝对的中心位置，并且slow是属于前一部分的尾部
        TreeNode root = new TreeNode(slow.value);
        root.left = solve(head, slow);
        root.right = solve(slow.next, tail);
        return root;
    }
}
