package algorithm.kwaymerge;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//合并k个已排序的链表并将其作为一个已排序的链表返回。分析并描述其复杂度。
@Slf4j(topic = "MergeKSortedLists")
public class MergeKSortedLists {
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    static Node res;
    public static void main(String[] args) {
        List<Node> list = new ArrayList<>();
        solve(list);
    }

    // 处理边界情况
    private static void solve(List<Node> list) {
        List<Node> item = new ArrayList<>();
        if (list == null || list.isEmpty())
            return;
        for (int i = 0; i < list.size(); i+=2) {
            item.add(merge(list.get(i), list.get(i + 1)));
        }
        solve(item);
    }

    // merge2个有序链表
    private static Node merge(Node one, Node two) {
        Node res = new Node(0);
        Node p = one, q = two, r = res;
        while (p != null || q != null) {
            if (p == null) {
                r.next = q;
                break;
            }
            if (q == null) {
                r.next = p;
                break;
            }
            if (p.value < q.value) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }
        return res.next;
    }
}
