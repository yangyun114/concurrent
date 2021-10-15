package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

// 单链表的排序
@Slf4j(topic = "SolveTwo")
public class SortLinkedList {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private static void sort(Node head) {
        Node p = head;
        while (p != null) {
            Node q = p, k = q.next;
            // 内层循环，相邻2个节点判断大小，交换位置
            while (q != null && k != null) {
                if (q.value > k.value) {
                    int temp = k.value;
                    k.value = q.value;
                    q.value = temp;
                }
                q = q.next;
                k = k.next;
            }
            p = p.next;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(1);
        node.next.next = new Node(3);
        node.next.next.next = new Node(5);
        node.next.next.next.next = new Node(4);
        sort(node);

        Node p = node;
        while (p != null) {
            log.info("{}", p.value);
            p = p.next;
        }
    }
}
