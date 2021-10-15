package algorithm.slowfastpointers;

import lombok.extern.slf4j.Slf4j;

// 反转链表
@Slf4j(topic = "ReverseLinkedList")
public class ReverseLinkedList {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        Node res = solve(head);
        Node p = res;
        while (p != null) {
            log.info("{}", p.value);
            p = p.next;
        }
    }

    private static Node solve(Node head) {
        if (head == null)
            return null;
        // 快慢指针找到中点
        Node slow = head, fast = head, p = head;
        while (fast != null && fast.next != null) {
            p = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 截断2个链表，第一个链表从head开始p结束，第二个链表从slow开始
        p.next = null;
        // 反转第二个链表
        Node second = new Node(0);
        Node q = slow;
        while (q != null) {
            second.next = q;
            q = q.next;
            second.next.next = second.next;
        }
        second = second.next;

        // 生成返回数据的节点
        Node res = new Node(0);
        Node a = head, b = second, c = res;
        while (a != null || b != null) {
            if (a == null) {
                c.next = b;
                b = b.next;
            }
            c.next = a;
            a = a.next;
            c = c.next;
            c.next = b;
            b = b.next;
            c = c.next;
        }
        return res.next;
    }
}
