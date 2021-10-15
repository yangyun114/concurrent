package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

//“在一个二维数组中，每一行都按照从左到右递增的顺序排序，
// 每一列都按照从上到下递增的顺序排序。
// 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。”
@Slf4j
public class FindInPartiallySortedMatrix {
    public static void main(String[] args) throws Exception {
        int[][] arr = {
            {1, 2, 8, 9},
            {2, 4, 9, 12},
            {4, 7, 10, 13},
            {6, 8, 11, 15}
        };
        int target = 5;
        // 行、列
        int rows = arr.length;
        int columns = arr[0].length;
        boolean res = solve(arr, rows, columns, target);
        log.info("{}", res);
    }

    private static boolean solve(int[][] arr, int rows, int columns, int target) throws Exception {
        // 判空
        if (arr == null || rows <= 0 || columns <= 0)
            throw new Exception("数据有误");

        // 从右上角开始找起，row和column为当前位置
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            // 记录当前值
            int item = arr[row][column];
            if (item == target) {
                // 如果相等，直接返回true
                return true;
            } else if (item < target) {
                // 如果值比目标小，把行数加1
                ++row;
            } else {
                // 如果值比目标大，把列数减1
                --column;
            }
        }
        // 循环结束了，还没有找到，返回false
        return false;
    }
}
