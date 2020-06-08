package algorithm;

import lombok.extern.slf4j.Slf4j;

// 将给定的单链表L： L 0→L 1→…→L n-1→L n,
//重新排序为： L 0→L n →L 1→L n-1→L 2→L n-2→…
//对于给定的单链表{1,2,3,4}，将其重新排序为{1,4,2,3}.
@Slf4j(topic = "ReorderList")
public class ReorderList {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Node head = new Node(arr[0]);
        Node p = head;
        for (int i = 1; i < arr.length; i++) {
            p.next = new Node(arr[i]);
            p = p.next;
        }

        Node res = reOrder(head);
        Node q = res;
        while (q != null) {
            log.info("q:{}", q.value);
            q = q.next;
        }

    }

    private static Node reOrder(Node head) {
        // 首先将给定的链表，分为两个链表
        Node preEnd = head, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            preEnd = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 截断，前链表head～preEnd，后链表为slow～fast
        preEnd.next = null;

        // 反转后链表
        Node second = new Node(0);
        Node p = second;
        // q遍历后链表
        Node q = slow;
        while (q != null) {
            // 头插法反转
            Node node = new Node(q.value);
            node.next = p.next;
            p.next = node;
            q = q.next;
        }
        // 去除辅助头结点
        second = second.next;

        // 得到两个链表head和second，依次遍历插入新的链表
        Node res = new Node(0);
        Node one = head;
        Node two = second;
        Node r = res;
        // 后链表比较长
        while (one != null || two != null) {
            if (one != null) {
                r.next = new Node(one.value);
                r.next.next = new Node(two.value);
                one = one.next;
                two = two.next;
                r = r.next.next;
            } else {
                r.next = new Node(two.value);
                two = two.next;
                r = r.next;
            }
        }
        return res.next;

    }

}
