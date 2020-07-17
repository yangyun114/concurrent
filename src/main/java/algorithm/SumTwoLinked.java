package algorithm;

import lombok.extern.slf4j.Slf4j;

// 链表的结点对应一个数字，求和
//给定两个代表非负数的链表，数字在链表中是反向存储的（链表头结点处的数字是个位数，第二个结点上的数字是十位数...），求这个两个数的和，结果也用链表表示。
//输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出： 7 -> 0 -> 8
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

    private static Node solve(Node n1, Node n2) {
        Node p = n1;
        Node q = n2;
        Node res = new Node(0);
        Node cur = res;
        // 进位标识
        int flag = 0;
        while (p != null || q != null) {
            // 如果某一项为null，则认为是0
            int sum = (p == null ? 0 : p.val) + (q == null ? 0 : q.val);
            // 和对10取模，然后加上进位符
            cur.next = new Node((sum) % 10 + flag);
            cur = cur.next;

            // 大于10进位，小于则不进
            flag = sum >= 10 ? 1 : 0;
            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }
        // 如果最后还是进位符大于1，需要额外补上
        if (flag == 1) {
            cur.next = new Node(1);
        }
        return res.next;
    }

//    public static Node solve(Node n1, Node n2) {
//        Node res = new Node(0);
//        Node p = n1, q = n2, current = res;
//        // 进位标识
//        int carry = 0;
//        while (p != null || q != null) {
//            int x = p == null ? 0 : p.val;
//            int y = q == null ? 0 : q.val;
//            int sum = x + y + carry;
//            current.next = new Node(sum % 10);
//            carry = (x + y) / 10;
//            current = current.next;
//            if (p != null)
//                p = p.next;
//            if (q != null)
//                q = q.next;
//        }
//        if (carry == 1) {
//            current.next = new Node(1);
//        }
//        return res.next;
//    }
}

class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}
