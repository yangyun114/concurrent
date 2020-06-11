package algorithm;

import lombok.extern.slf4j.Slf4j;

////填充所有节点的next指针，指向它右兄弟节点。如果没有右兄弟节点，则应该将next指针设置为NULL。
////初始时，所有的next指针都为NULL
////注意：
////你只能使用常量级的额外内存空间
////此时已经不再是完美二叉树了
@Slf4j(topic = "PopulatingNextRightPointersInEachNodeTwo")
public class PopulatingNextRightPointersInEachNodeTwo {
    private static class Node {
        int value;
        Node left;
        Node right;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        solve(root);
    }

    private static void solve(Node node) {
        while (node != null) {
            // 在每一层的最左边都建立一个虚拟结点，注意，这里的每一层应该是在node结点的下一层
            Node tempLevelFirt = new Node(0);
            // cur用来在虚拟结点之后插入结点，并向后移动一个位置
            Node cur = tempLevelFirt;
            while (node != null) {
                // node的左结点不为空，则cur的next指向左结点，cur向前移动一个位置
                if (node.left != null) {
                    cur.next = node.left;
                    cur = cur.next;
                }
                // node的右结点不为空，则cur的next指向这个右结点，cur继续向前移动
                if (node.right != null) {
                    cur.next = node.right;
                    cur = cur.next;
                }
                // 内循环中，node往next方向移动，知道node同层没有结点为止
                node = node.next;
            }
            // 外循环中，temp结点是node结点下一层的最左边的虚拟结点，所以外层循环是把node结点往temp结点的next移动
            node = tempLevelFirt.next;
        }
    }
}
