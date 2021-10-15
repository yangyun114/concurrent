package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

// 写一个二分查找
@Slf4j(topic = "BinarySearch")
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 3;
        int solve = solve1(arr, target, 0, arr.length - 1);
        log.info("{}", solve);
    }

    // 递归
    private static int solve(int[] arr, int target, int left, int right) {
        if (left == right)
            return left;
        int center = (left + right) / 2;
        if (target > arr[center])
            return solve(arr, target, center + 1, right);
        else
            return solve(arr, target, left, center);
    }

    // 非递归
    private static int solve1(int[] arr, int target, int left, int right) {
        while (left < right) {
            int center = (left + right) / 2;
            if (arr[center] < target) {
                left = center + 1;
            } else {
                right = center;
            }
        }
        return left == right ? left : -1;
    }
}
