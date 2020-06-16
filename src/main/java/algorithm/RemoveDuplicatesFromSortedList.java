package algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//给出一个排好序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
//例如：
//给出的链表为1->2->3->3->4->4->5, 返回1->2->5.
//给出的链表为1->1->1->2->3,  返回2->3.
public class RemoveDuplicatesFromSortedList {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        int[] arr = new int[]{1, 2, 3, 3, 4, 4, 5};
        Node p = head;
        for (int i : arr) {
            p.next = new Node(i);
            p = p.next;
        }
        head = head.next;
        solve(head);
    }

    private static void solve(Node head) {
        if (head == null) {
            return;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            if (map.containsKey(p.value)) {
                map.put(p.value, map.get(p.value) + 1);
            } else {
                map.put(p.value, 1);
            }
            p = p.next;
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() == 1) {
                iterator.remove();
            }
        }
        Set<Integer> key = map.keySet();

        Node newHead = new Node(0);
        Node q = newHead;
        while (p != null) {
            //
        }
    }
}
