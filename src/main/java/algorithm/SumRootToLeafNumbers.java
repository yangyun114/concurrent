package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定一个仅包含数字0-9的二叉树，每一条从根节点到叶子节点的路径都可以用一个数字表示。
//例如根节点到叶子节点的一条路径是1->2->3,那么这条路径就用123来代替。
//找出根节点到叶子节点的所有路径表示的数字之和
//例如：
//    1↵   / ↵  2   3
//根节点到叶子节点的路径1->2用数字12代替
//根节点到叶子节点的路径1->3用数字13代替
//所以答案为12+13=25
@Slf4j(topic = "SumRootToLeafNumbers")
public class SumRootToLeafNumbers {
    private static class Node {
        int value;
        Node left;
        Node right;

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
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        solve(res, item, root);
        log.info("{}", res);
        int sum = res.stream().mapToInt(i -> i.stream().mapToInt(j -> j).sum()).sum();
        log.info("{}", sum);

        int sum1 = res.stream().mapToInt(i -> {
            StringBuffer str = new StringBuffer();
            i.stream().map(j -> {
                log.info("{}", str);
                return str.append(j);
            });
            return Integer.parseInt(str.toString());
        }).sum();
        log.info("{}", sum1);


    }

    private static void solve(List<List<Integer>> res, List<Integer> item, Node node) {
        item.add(node.value);
        if (node.left == null && node.right == null) {
            log.info("{}", item);
            res.add(new ArrayList<>(item));
            item.remove(item.size() - 1);
            return;
        }
        if (node.left != null) {
            solve(res, item, node.left);
        }
        if (node.right != null) {
            solve(res, item, node.right);
        }
        item.remove(item.size() - 1);
    }
}
