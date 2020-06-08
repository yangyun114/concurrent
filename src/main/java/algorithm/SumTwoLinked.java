package algorithm;

import lombok.extern.slf4j.Slf4j;

// 链表的结点对应一个数字，求和
@Slf4j(topic = "SumTwoLinked")
public class SumTwoLinked {
    public static void main(String[] args) {
        Node n1 = new Node(2);
        n1.next = new Node(4);
        n1.next.next = new Node(3);

        Node n2 = new Node(5);
        n2.next = new Node(6);
        n2.next.next = new Node(4);

        Node res = solve(n1, n2);
        while (res != null) {
            log.info("{}", res.val);
            res = res.next;
        }

    }

    public static Node solve(Node n1, Node n2) {
        Node res = new Node(0);
        Node p = n1, q = n2, current = res;
        // 进位标识
        int carry = 0;
        while (p != null || q != null) {
            int x = p == null ? 0 : p.val;
            int y = q == null ? 0 : q.val;
            int sum = x + y + carry;
            current.next = new Node(sum % 10);
            carry = (x + y) / 10;
            current = current.next;
            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }
        if (carry == 1) {
            current.next = new Node(1);
        }
        return res.next;
    }
}

class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}
