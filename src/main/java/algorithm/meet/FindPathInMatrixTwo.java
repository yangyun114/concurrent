package algorithm.meet;

import java.util.ArrayList;
import java.util.List;

//给定一个M*N的矩阵，里面有数字1，0。 然后给定一个坐标，x, y。 请写一个程序计算与坐标 x,y 值相同，且连通的数字个数。“连通” 定义为：左右或者上下数值一样则认为这两个块是连通的，连通具有传递性。
//
//输入：array,  x, y
//输出：n
//
//举例：
//输入：
//array=[
//[1, 0, 1, 0]
//[1, 0 ,0 0 ]
//[1, 1, 0, 1 ]
//[0, 1,  0,  1]
//[1,  1, 0,  0 ]
//]
//
//x, y = (0, 1)
public class FindPathInMatrixTwo {
    // 用来记录连通量
    static int count = 0;
    // 用来记录访问过的坐标
    // 用来记录访问过的位置的坐标，比如(0,1)记录为"01"
    static List<String> visited = new ArrayList<>();
    public static void main(String[] args) {
        int[][] arr = {
                {1,0,1,0},
                {1,0,0,0},
                {0,1,0,1},
                {1,1,0,0}};

        int x = 0, y = 1;
        solve(arr, x, y, arr[x][y]);

        System.out.println("连通量为: " + count);
    }

    private static int solve(int[][] arr, int i, int j, int target) {
        if (arr[i][j] == target) {
            // 记录当前的坐标，把count自增
            visited.add(String.valueOf(i) + j);
            ++count;
            // 上
            if (i != 0 && !visited.contains(String.valueOf(i - 1) + j))
                solve(arr, i - 1, j, target);
            // 下
            if (i != arr.length - 1 && !visited.contains(String.valueOf(i + 1) + j))
                solve(arr, i + 1, j, target);
            // 左
            if (j != 0 && !visited.contains(i + String.valueOf(j - 1)))
                solve(arr, i, j - 1, target);
            // 右
            if (j != arr[0].length - 1 && !visited.contains(i + String.valueOf(j + 1)))
                solve(arr, i, j + 1, target);
        }
        return 0;
    }

}
