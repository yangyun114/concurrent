package algorithm.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class MaxMatrix {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String k = scanner.nextLine();
        int[][] arr = new int[Integer.valueOf(k)][2];

        String next = scanner.nextLine();
        String[] s = next.split(" ");
        for (int i = 0; i < s.length; i++) {
            log.info("{}", s[i]);

            String[] split = s[i].split(",");
            for (int j = 0; j < split.length; j++) {
                arr[i][j] = Integer.valueOf(split[j]);
            }

        }

        log.info("{}", arr);

//        MaxMatrix matrix = new MaxMatrix();
//
//        System.out.println("行列分别为");
//        Scanner scanner = new Scanner(System.in);
//        int row = scanner.nextInt();
//        System.out.println("行:" + row);
//
//        int col = scanner.nextInt();
//        System.out.println("列:" + col);
//
//        System.out.println("请输入行数据，数字之间使用空格分开，每行输入完成后使用换行键");
//
//        int[][] param = new int[row][col];
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                int currentInt = scanner.nextInt();
//                param[i][j] = currentInt;
//            }
//        }
//
//        int result = matrix.subMaxMatrix(param);
//        System.out.println("结果为:" + result);
    }

    public int subMaxMatrix(int[][] matrix) {

        int[][] total = matrix;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                total[i][j] += total[i-1][j];
            }
        }

        int maximum = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                //result 保存的是从 i 行 到第 j 行 所对应的矩阵上下值的和
                int[] result = new int[matrix[0].length];
                for (int f = 0; f < matrix[0].length; f++) {
                    if (i == 0) {
                        result[f] = total[j][f];
                    } else {
                        result[f] = total[j][f] - total[i - 1][f];
                    }
                }
                int maximal = maxSubsequence(result);

                if (maximal > maximum) {
                    maximum = maximal;
                }
            }
        }

        return maximum;
    }

    public int maxSubsequence(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[] maxSub = new int[array.length];
        maxSub[0] = array[0];

        for (int i = 1; i < array.length; i++) {
            maxSub[i] = (maxSub[i-1] > 0) ? (maxSub[i-1] + array[i]) : array[i];
            if (max < maxSub[i]) {
                max = maxSub[i];
            }
        }
        return max;
    }


}
