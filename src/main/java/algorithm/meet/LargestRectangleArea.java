package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

// 给定直方图，求最大面积
@Slf4j(topic = "LargestRectangleArea")
public class LargestRectangleArea {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        solve(arr);
    }

    // 暴力解法
    private static void solve(int[] arr) {
        int maxArea = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int area = (j - i + 1) * getMin(arr, i, j);
                maxArea = Math.max(maxArea, area);
            }
        }
        log.info("{}", maxArea);
    }

    // 获取从i到j，数组中最小的值
    private static int getMin(int[] arr, int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            min = Math.min(min, arr[k]);
        }
        return min;
    }
}
