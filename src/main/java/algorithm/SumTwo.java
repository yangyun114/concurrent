package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 给定一个数组和target，返回等于target的两个数相加的组合
@Slf4j(topic = "SumTwoNumbers")
public class SumTwo {

    private static void solve(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    log.info("{}", new int[]{arr[i], arr[j]});
                }
            }
        }
    }

    private static void solve1(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int component = target - arr[i];
            if (set.contains(component)) {
                log.info("{}", new int[] {component, arr[i]});
            }
            set.add(arr[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        solve1(arr, 8);
    }
}
