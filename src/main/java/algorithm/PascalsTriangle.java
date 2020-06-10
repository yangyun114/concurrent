package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

//给出一个索引k，返回杨辉三角的第k行
//例如，k=3，
//返回[1,3,3,1].
//备注：
//你能将你的算法优化到只使用O(k)的额外空间吗?
@Slf4j(topic = "PascalsTriangle")
public class PascalsTriangle {
    public static void main(String[] args) {
        int rowIndex = 3;
        solve(rowIndex);
    }

    private static void solve(int rowIndex) {
        int[] row = new int[rowIndex + 1];
        if(rowIndex == 0){
            log.info("{}", row);
            return;
        }
        rowIndex++;
        row[0] = 1;
        for(int i = 1;i < rowIndex; i++) {
            for(int j = i - 1;j > 0;j--) {
                row[j] = row[j - 1] + row[j];
            }
            row[i] = 1;
        }
        log.info("{}", row);
        return;
    }
}
