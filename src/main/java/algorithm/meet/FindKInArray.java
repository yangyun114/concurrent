package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

// 在数组中找到第k小的数
@Slf4j(topic = "FindKInArray")
public class FindKInArray {
    public static void main(String[] args) {
        List<Integer> param = Arrays.asList(2, 4, 1, 3, 5);
//        quickSort(param, 0, 4);
//        log.info("{}", param);
        int k = findK(4, param, 0, param.size() - 1);
        log.info("{}", k);
    }

    private static int findK(int k, List<Integer> list, int start, int end) {
        int i = partition(list, start, end);
        // 如果i == k - 1，说明此时已经是第k小的数了
        if (i == k - 1) {
            return list.get(i);
        } else if (i > k - 1) {
            // 如果i比k - 1大，说明我们要找的数在前半段
            return findK(k, list, start, i - 1);
        } else if (i < k - 1) {
            // 如果i比k - 1小，说明在后半段
            return findK(k, list, i + 1, end);
        }
        return 0;
    }

    // 把一个比pivot大的数放右边，比pivot小的数放左边
    private static int partition(List<Integer> list, int start, int end) {
        Integer pivot = list.get(start), i = start, j = end;
        while (i < j) {
            // 找一个比pivot小的数
            while (list.get(j) >= pivot && i < j) {
                --j;
            }
            // 找一个比pivot大的数
            while (list.get(i) <= pivot && i < j) {
                ++i;
            }
            // 找到之后，交换i和j位置的值
            Integer temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
        // 循环解锁，i = j，与pivot交换位置
        list.set(start, list.get(i));
        list.set(i, pivot);
        // 返回的是pivot的位置
        return i;
    }

    // 快速排序
    private static void quickSort(List<Integer> list, int start, int end) {
        if (start >= end)
            return;
        int i = partition(list, start, end);
        quickSort(list, start, i - 1);
        quickSort(list, i + 1, end);
    }





    // Todo，有待观察
    public static int partition(int[] array, int left, int right) {
        int k = array[left];
        int i = left;
        int j = right;
        while (j > i) {
            while (array[j] < k && j > i) {
                j--;
            }
            if (j > i) {
                array[i] = array[j];
                i++;
            }
            while (array[i] > k && j > i) {
                i++;
            }
            if (j > i) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = k;
        return i;
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = partition(array, left, right);
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }
}
