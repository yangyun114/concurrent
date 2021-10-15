package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FindLoopInLinkedList {
    private static class Node {
        String value;
        Node next;

        public Node(String value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {

    }

    private boolean isLoop(Node head) {
        // 使用list来存储节点
        List<Node> list = new ArrayList<>();
        Node p = head;
        // 遍历节点
        while (p != null) {
            // 如果list已经包含p，说明已经放进去过，存在环，直接返回true
            if (list.contains(p))
                return true;
            list.add(p);
            p = p.next;
        }
        // 遍历结束，说明不存在环，返回false
        return false;
    }
}
