package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

// 将二维矩阵旋转90度
@Slf4j(topic = "RotaionMatrix")
public class RotaionMatrix {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        solve(arr);
    }

    private static void solve(int[][] arr) {
        log.info("{}", arr);

        // 先按照主对角线进行交换
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        log.info("{}", arr);

        // 然后以水平的分界线，进行翻转
        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[arr.length - 1 - i][j];
                arr[arr.length - 1 - i][j] = temp;
            }
        }

        log.info("{}", arr);
    }
}
