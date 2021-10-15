package algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j(topic = "Sort")
public class Sort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
//        // 选择排序
//        solve1(arr);
//        // 冒泡排序
//        solve2(arr);
//        // 插入排序
//        solve3(arr);

        // 快速排序
//        solve4(arr);

        // 归并排序
        int[] res = solve5(arr);
        log.info("{}", res);
    }

    // 1、选择排序
    private static void solve1(int[] arr) {
        // 每次取最小值，放到数组的第一个位置
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        log.info("选择排序：{}", arr);
    }

    // 2、冒泡排序
    private static void solve2(int[] arr) {
        // 内循环每次从第一个位置开始，然后把相邻的2个元素换位置
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        log.info("冒泡排序：{}", arr);
    }

    // 3、插入排序
    private static void solve3(int[] arr) {

    }

    // 4、快速排序
    private static void solve4(int[] arr) {
        solve4(arr, 0, arr.length - 1);
        log.info("{}", arr);
    }

    // 给定arr和起点终点位置，返回pivot的位置
    private static void solve4 (int[] arr, int start, int end) {
        // 递归终止
        if (start > end)
            return;

        int pivot = arr[start];
        int i = start, j = end;
        while (i < j) {
            // 找一个比pivot小或等的数
            while (arr[j] >= pivot && i < j) {
                j--;
            }
            // 找一个比pivot大或等的数
            while (arr[i] <= pivot && i < j) {
                i++;
            }
            // 交换2个数的位置
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        // i和j相遇了
        arr[start] = arr[i];
        arr[i] = pivot;

        // 左半部分递归调用
        solve4(arr, start, i - 1);
        // 右半部分递归
        solve4(arr, i + 1, end);
    }

    // 5、归并排序
    private static int[] solve5(int[] arr) {
//        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);
        // 终止条件
        if (arr.length == 1)
            return arr;
        int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        return merge(solve5(left), solve5(right));
    }

    // 合并2个有序数组
    private static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length || j < right.length) {
            // left遍历完了
            if (i == left.length) {
                res[k++] = right[j++];
                continue;
            }
            // right遍历完了
            if (j == right.length) {
                res[k++] = left[i++];
                continue;
            }
            // 都没有遍历完
            if (left[i] < right[j]) {
                res[k++] = left[i++];
            } else {
                res[k++] = right[j++];
            }
        }
        return res;
    }
}
