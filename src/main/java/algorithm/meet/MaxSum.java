package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

// 最长连续子序列之和
// 给定的是数组[−2, 1, −3, 4, −1, 2, 1, −5, 4]，最大连续子序列是[4, -1, 2, 1]，最长子序列和是6。
@Slf4j(topic = "MaxSum")
public class MaxSum {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = solve(arr);
        log.info("{}", res);

        // 动态规划
        solve1(arr);
    }

    private static void solve1(int[] arr) {
        int max = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            // 如果到i - 1位置的值是大于0的，说明可以把i也累计上去
            if (sum > 0)
                sum += arr[i];
            // 如果到i - 1的累积值是小于0的，那么前面的部分直接舍弃掉，转而从i开始累积
            else
                sum = arr[i];
            // 获取整个部分中累积最大的值
            max = Math.max(max, sum);
        }
        log.info("{}", max);
    }

    // 暴力算法，获取所有的连续子序列
    private static int solve(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

}
