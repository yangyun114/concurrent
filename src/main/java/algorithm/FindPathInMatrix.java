package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//判断一个矩阵中是否存在一条路径，路径可以从任何一个位置开始，每一步可以上下左右移动一格
//不能重复移动，例如有以下矩阵，包含有一条路径'bfce'
//[['a', 'b', 'c', 'e'],
// ['s', 'f', 'c', 's'],
// ['a', 'd', 'e', 'e']]
@Slf4j(topic = "FindPathInMatrix")
public class FindPathInMatrix {
    public static void main(String[] args) {
        char[][] arr = new char[][]{
            {'a', 'b', 'c', 'e'},
            {'s', 'f', 'c', 's'},
            {'a', 'd', 'e', 'e'}
        };
        String str = "bfce";
        solve(arr, str);
    }

    private static void solve(char[][] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == str.charAt(0)) {
                    List<String> visited = new ArrayList<>();
                    getPath(arr, i, j, str, visited);
                    // 打印路径
                    if (visited.size() == str.length())
                        log.info("{}", visited);
                }
            }
        }
    }

    private static void getPath(char[][] arr, int i, int j, String str, List<String> visited) {
        if (str.length() == 0)
            return;
        if (arr[i][j] == str.charAt(0)){
            // 不能直接存放数组值，因为可能会重复
            visited.add(String.valueOf(i) + j);
            String newStr = str.substring(1);
            // 上
            if (i != 0 && !visited.contains(String.valueOf(i - 1) + j))
                getPath(arr, i - 1, j, newStr, visited);
            // 下
            if (i != arr.length - 1 && !visited.contains(String.valueOf(i + 1) + j))
                getPath(arr, i + 1, j, newStr, visited);
            // 左
            if (j != 0 && !visited.contains(i + String.valueOf(j - 1)))
                getPath(arr, i, j - 1, newStr, visited);
            // 右
            if (j != arr[0].length - 1 && !visited.contains(i + String.valueOf(j + 1)))
                getPath(arr, i, j + 1, newStr, visited);

        }

    }

}
