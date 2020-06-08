package algorithm;

import lombok.extern.slf4j.Slf4j;

// 对链表进行插入排序
@Slf4j(topic = "InsertionSortLinkedList")
public class InsertionSortLinkedList {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(3);
        head.next.next = new Node(1);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(4);

        Node res = solve(head);
        Node p = res;
        while (p != null) {
            log.info("{}", p.value);
            p = p.next;
        }

    }

    private static Node solve(Node head) {
        Node res = new Node(0);
        if (head == null) {
            return head;
        }
        Node p = head;
        while (p != null) {
            Node q = res;
            while (q != null) {
                // 如果p指针元素比q指针元素都大，则放到最后面
                if (q.next == null) {
                    q.next = new Node(p.value);
                    break;
                }
                // 如果p指针的元素比q指针下一个的元素小，则在q指针之后生产一个结点，结点生成之后跳出本次循环
                if (p.value < q.next.value) {
                    Node node = new Node(p.value);
                    node.next = q.next;
                    q.next = node;
                    break;
                }
                q = q.next;
            }
            p = p.next;
        }
        return res.next;
    }

}
