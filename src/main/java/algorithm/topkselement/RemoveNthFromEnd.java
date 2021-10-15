package algorithm.topkselement;

import lombok.extern.slf4j.Slf4j;

//给定一个链表，删除链表的倒数第n个节点并返回链表的头指针
//例如，
//   给出的链表为:1->2->3->4->5, n= 2.↵↵   删除了链表的倒数第n个节点之后,链表变为1->2->3->5.
//备注：
//题目保证n一定是合法的
//请尝试只用一步操作完成该功能
@Slf4j(topic = "RemoveNthFromEnd")
public class RemoveNthFromEnd {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node p = head;
        int i = 2;
        while (i < 6) {
            p.next = new Node(i);
            p = p.next;
            i++;
        }

        solve(head, 2);

        Node q = head;
        while (q != null) {
            log.info("{}", q.value);
            q = q.next;
        }
    }

    private static Node solve(Node head, int i) {
        if (head == null)
            return null;
        // 先获取链表的长度
        int length = 0;
        Node p = head;
        while (p != null) {
            length++;
            p = p.next;
        }
        // 顺向的位置
        Node slow = head, fast = head;
        int index = 0;
        while (fast != null) {
            if (index == length - i) {
                slow.next = fast.next;
                return head;
            }
            index++;
            slow = fast;
            fast = fast.next;
        }
        return head;
    }

}
