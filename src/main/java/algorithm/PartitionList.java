package algorithm;

import lombok.extern.slf4j.Slf4j;

//给出一个链表和一个值x，以x为参照将链表划分成两部分，使所有小于x的节点都位于大于或等于x的节点之前。
//两个部分之内的节点之间要保持的原始相对顺序。
//例如：
//给出1->4->3->2->5->2和x = 3,
//返回1->2->2->3->4->5.
@Slf4j(topic = "PartitionList")
public class PartitionList {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2, 5, 2};
        Node head = new Node(0);
        Node p = head;
        for (int i : arr) {
            p.next = new Node(i);
            p = p.next;
        }
        head = head.next;

        Node solve = solve(head, 3);
        Node q = solve;
        while (q != null) {
            log.info("{}", q.value);
            q = q.next;
        }
    }

    private static Node solve(Node head, int target) {
        Node one = new Node(0);
        Node two = new Node(0);
        Node pOne = one;
        Node pTwo = two;
        Node p = head;
        while (p != null) {
            if (p.value < target) {
                pOne.next = new Node(p.value);
                pOne = pOne.next;
            } else {
                pTwo.next = new Node(p.value);
                pTwo = pTwo.next;
            }
            p = p.next;
        }
        pOne.next = two.next;
        one = one.next;
        return one;
    }
}
