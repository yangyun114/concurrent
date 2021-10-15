package algorithm.slowfastpointers;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

//对于一个给定的链表，返回环的入口节点，如果没有环，返回null
//拓展：
//你能给出不利用额外空间的解法么
@Slf4j(topic = "LinkedListCycleTwo")
public class LinkedListCycleTwo {

    private static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(4);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = second;

//        Node entry = solve(head);
        Node entry = solve1(head);
        log.info("{}", entry.value);
    }

    private static Node solve1(Node head) {
        // 使用快慢指针
        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // 以相同速度快指针从相遇位置开始，慢指针从head开始，则必定在入口处相遇
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    private static Node solve(Node head) {
        // 使用set，遍历到重复的set，则返回
        Set<Node> set = new HashSet<>();
        Node q = head;
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            set.add(q);
            q = q.next;
        }
        return null;
    }
}
