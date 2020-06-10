package algorithm;

import lombok.extern.slf4j.Slf4j;

//给出一个值numRows，生成杨辉三角的前numRows行
//例如，给出 numRows = 5,
//返回
//[↵     [1],↵    [1,1],↵   [1,2,1],↵  [1,3,3,1],↵ [1,4,6,4,1]↵]
@Slf4j(topic = "PascalsTriangleTwo")
public class PascalsTriangleTwo {
    public static void main(String[] args) {
        int numRows = 5;
        solve(numRows);
    }

    private static void solve(int numRows) {
        if (numRows == 0) {
            return;
        }
        int[] arr = new int[numRows];
        arr[0] = 1;
        for (int i = 0; i < numRows; i++) {
            for (int j = i - 1; j > 0; j--) {
                arr[j] = arr[j] + arr[j - 1];
            }
            arr[i] = 1;
            log.info("{}", arr);
        }
    }
}
