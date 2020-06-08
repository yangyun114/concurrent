package algorithm;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//现在有一个这样的链表：链表的每一个节点都附加了一个随机指针，随机指针可能指向链表中的任意一个节点或者指向空。
//请对这个链表进行深拷贝。
@Slf4j(topic = "CopyListWithRandomPointer")
public class CopyListWithRandomPointer {

    private static class Node{
        int value;
        Node next;
        Node random;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);

        head.next = two;
        two.next = three;
        head.random = three;
        two.random = head;

        Node res = solve(head);
        Node p = res;
        while (p != null) {
            log.info("value:{}, next.value:{}, random.valur:{}", p.value,
                    p.next == null ? null : p.next.value, p.random == null ? null : p.random.value);
            p = p.next;
        }
    }

    private static Node solve(Node head) {
        HashMap<Node, Integer> map = new HashMap<>();
        Node res = new Node(0);
        Node m = res;
        Node p = head;
        while (p != null) {
            Node node = new Node(p.value);
            m.next = node;
            m = m.next;
            if (p.random != null) {
                map.put(node, p.random.value);
            }
            p = p.next;
        }
        res = res.next;

        Iterator<Map.Entry<Node, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Node, Integer> next = iterator.next();
            Node q = res;
            while (q != null) {
                if (q.value == next.getValue()) {
                    next.getKey().random = q;
                }
                q = q.next;
            }
        }
        return res;

    }
}
