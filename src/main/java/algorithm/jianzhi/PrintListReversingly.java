package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

// 给定一个链表节点，反向输出节点值
@Slf4j
public class PrintListReversingly {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        solve1(head);
        solve2(head);
        solve3(head);

    }

    // 1、改变原有结构，把链表的指针方向反转，一般情况不做考虑
    private static void solve1(Node head) {
        // 为空直接返回
        if (head == null) {
            return;
        }
        Node slow = null, fast = head;
        while (fast != null) {
            // 记录当前节点
            Node p = fast;
            // 快指针前移
            fast = fast.next;

            // 把当前节点的next指向慢指针
            p.next = slow;
            // 慢指针前移
            slow = p;
        }

        // 反转完成，顺序打印
        Node q = slow;
        while (q != null) {
            log.info("solve1:{}", q.value);
            q = q.next;
        }
    }

    // 2、使用栈
    private static void solve2(Node head) {
        Stack<Node> stack = new Stack<>();
        Node p = head;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            log.info("solve2:{}", pop.value);
        }
    }

    // 3、使用递归，遍历一个节点时，优先输出它的下一个节点
    private static void solve3(Node node) {
        if (node == null)
            return;
        solve3(node.next);
        log.info("solve3:{}", node.value);
    }


}
