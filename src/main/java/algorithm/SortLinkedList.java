package algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "SortLinkedList")
public class SortLinkedList {
    public static void main(String[] args) {
        SortLinkedListNode head = new SortLinkedListNode(2);
        head.next = new SortLinkedListNode(3);
        head.next.next = new SortLinkedListNode(1);

        SortLinkedListNode res = sortList(head);
//        SortLinkedListNode p = res;
//        while (p != null) {
//            log.info("{}", p.value);
//            p = p.next;
//        }
    }

    private static SortLinkedListNode sortList(SortLinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        SortLinkedListNode preEnd = head, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            preEnd = slow;
            // 慢指针一次移动一个单位
            slow = slow.next;
            // 快指针一次移动两个单位
            fast = fast.next.next;
        }
        // 这行代码截断了链表，head -> preEnd是第一个，slow -> fast是第二个
        // head是第一个链表的头指针，preEnd是第一个链表的尾指针，
        // slow是第二个链表的头指针，fast是第二个链表的尾指针
        preEnd.next = null;

        // 递归的退出条件之一是只有一个结点，所以l1和l2最后都是一个单结点
        SortLinkedListNode l1 = sortList(head);
        log.info("l1:{}", l1.value);
        SortLinkedListNode l2 = sortList(slow);
        log.info("l1:{}, l2:{}", l1.value, l2.value);

        // 递归退出条件之二是对l1和l2结点进行排序
        SortLinkedListNode res = merge(l1, l2);
        log.info("res:{}", res.value);
        return res;
    }

    private static SortLinkedListNode merge(SortLinkedListNode l1, SortLinkedListNode l2) {
        SortLinkedListNode dummy = new SortLinkedListNode(0);
        SortLinkedListNode current = dummy;
        while (l1 != null && l2 != null) {
            if (l1.value <= l2.value) {
                // 把l1结点放到current后面
                current.next = l1;
                // l1结点指向null
                l1 = l1.next;
                // current往后移动一个位置，即替代了原有l1
                current = current.next;
            } else {
                // 与上述同理
                current.next = l2;
                l2 = l2.next;
                current = current.next;
            }
        }
        // 如果某个结点为null，则把current指向另一个结点
        if (l1 == null) {
            current.next = l2;
        }
        if (l2 == null) {
            current.next = l1;
        }

        // 无论哪种情况，都返回的是创建的辅助结点的后面的一个结点
        return dummy.next;
    }


}

class SortLinkedListNode {
    int value;
    SortLinkedListNode next;

    public SortLinkedListNode(int value) {
        this.value = value;
    }
}
