package algorithm;

import lombok.extern.slf4j.Slf4j;

//请写出一个高效的在m*n矩阵中判断目标值是否存在的算法，矩阵具有如下特征：
//每一行的数字都从左到右排序
//每一行的第一个数字都比上一行最后一个数字大
//例如：
//对于下面的矩阵：
//[↵  [1,   3,  5,  7],↵  [10, 11, 16, 20],↵  [23, 30, 34, 50]↵]
//要搜索的目标值为3，返回true；
@Slf4j(topic = "SearchA2dMatrix")
public class SearchA2dMatrix {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int t = 10;
        solve(arr, t);
    }

    private static void solve(int[][] arr, int t) {
        if (arr == null || arr.length == 0) {
            log.info("{}", false);
            return;
        }
        int i = 0;
        while (arr[i][0] < t) {
            i++;
            if (i > arr.length) {
                log.info("{}", false);
                return;
            }
        }
        if (arr[i][0] == t) {
            log.info("{}", arr[i][0]);
            return;
        }
        i--;
        int j = 0;
        while (arr[i][j] < t) {
            j++;
        }
        if (arr[i][j] == t) {
            log.info("{}", arr[i][j]);
            return;
        }
        log.info("{}", false);

    }
}
