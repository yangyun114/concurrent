package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

// 给定一系列的ip地址，对齐进行排序，如果一个部分的值较大，则不用比较下一部分
@Slf4j(topic = "Test")
public class SortIp {

    public static void main(String[] args) {
        int[] arr = new int[2];

        int[][] input = {{255, 0, 0, 1}, {12, 0, 0, 2}};
        for (int i = 0; i < input.length; i++) {
            arr[i] = getItem(input[i]);
        }
        quickSort(arr, 0, 1);

    }

    private static int getItem(int[] num) {
        return num[0] << 24 | num[1] << 16 | num[2] << 8 | num[3];
    }


    private static void quickSort(int[] arr, int start, int end) {
        // 递归终止条件
        if (start > end)
            return;
        
        int pivot = arr[start];
        int i = start, j = end;
        while (i < j) {
            // 找一个比pivot小的数
            while (arr[j] >= pivot && i < j) {
                j--;
            }
            // 找一个比pivot大的数
            while (arr[i] <= pivot && i < j) {
                i++;
            }
            // 找到之后，交换值
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        // 循环解锁，i = j，与pivot交换位置
        arr[start] = arr[i];
        arr[i] = pivot;

        // 递归进行pivot左右2边的部分
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }
}
