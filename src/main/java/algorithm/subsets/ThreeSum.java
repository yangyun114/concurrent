package algorithm.subsets;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

//给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
//注意：
//三元组（a、b、c）中的元素必须按非降序排列。（即a≤b≤c）
//解集中不能包含重复的三元组。
//    例如，给定的数组 S = {-1 0 1 2 -1 -4},解集为(-1, 0, 1) (-1, -1, 2)
@Slf4j(topic = "ThreeSum")
public class ThreeSum {
    static List<List<Integer>> res = new ArrayList<>();
    public static void main(String[] args) {
        int[] arr = {-4, -1, -1, 0, 1, 2};
        int target = 0;
//        solve(arr, target);
//        solve(arr, target, 0, new ArrayList<>());

        solve1(arr, target);
        log.info("{}", res);

    }

    // 转化
    private static void solve1(int[] arr, int target) {
        // 顺序遍历数组，获取一个元素后，转化为2数相加
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            int tempTarget = target - arr[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < arr.length; j++) {
                if (map.containsKey(tempTarget - arr[j])) {
                    res.add(Arrays.asList(arr[i], tempTarget - arr[j], arr[j]));
                }
                map.put(arr[j], j);
            }
        }
    }

    // 回溯法
    private static void solve(int[] arr, int target, int temp, List<Integer> item) {
        if (temp == 3) {
            if (target == 0) {
                res.add(new ArrayList<>(item));
            }
            return;
        }
        for (int i = temp; i < arr.length; i++) {
            // 如果元素跟之前的重复，则进入下一次循环
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            // 这一步非常关键，如果当前的元素比数组里面最后一个小，则需要break掉这次循环
            if (item.size() > 0 && arr[i] < item.get(item.size() - 1))
                break;
            item.add(arr[i]);
            solve(arr, target - arr[i], temp + 1, item);
            item.remove(item.size() - 1);
        }
    }

    // 暴力算法
    private static void solve(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            int one = arr[i];
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            for (int j = i + 1; j < arr.length; j++) {
                int two = arr[j];
                for (int k = j + 1; k < arr.length; k++) {
                    int three = arr[k];
                    if (one + two + three == target) {
                        List<Integer> item = Arrays.asList(one ,two, three);
                        res.add(item);
                    }
                }
            }
        }
    }
}
