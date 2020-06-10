package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

//给定一个无序的整数类型数组，求最长的连续元素序列的长度。
//例如：
//给出的数组为[100, 4, 200, 1, 3, 2],
//最长的连续元素序列为[1, 2, 3, 4]. 返回这个序列的长度：4
//你需要给出时间复杂度在O（n）之内的算法
@Slf4j(topic = "LongestConsecutiveSequence")
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(new Integer[]{100, 4, 200, 1, 3, 2});
        int res = solve1(list);
        log.info("{}", res);
    }

    // 更好的解法，把所有元素放到set中，然后遍历list，如果该元素在set中，就把该元素移除，并且递归移除该元素的自增和自减元素
    private static int solve1(List<Integer> list) {
        HashSet<Integer> set = new HashSet<>(list);
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            int length = 0;
            Integer item = list.get(i);
            if (set.remove(item)) {
                ++length;
                while (set.remove(item + 1)) {
                    ++length;
                    ++item;
                }
                while (set.remove(item - 1)) {
                    ++length;
                    -- item;
                }
            }
            res = Math.max(res, length);
        }
        return res;
    }

    private static int solve(List<Integer> list) {
        Collections.sort(list);
        int slow = 0;
        int fast = 1;
        int length = 0;
        int res = 0;
        while (slow != fast) {
            if (list.get(fast) == list.get(slow) + 1) {
                ++length;
                res = Math.max(length, res);
                if (fast != list.size() - 1) {
                    ++fast;
                }
                ++slow;
            } else {
                length = 0;
                if (fast != list.size() - 1) {
                    ++fast;
                }
                ++slow;
            }
        }
        return res + 1;
    }

}
