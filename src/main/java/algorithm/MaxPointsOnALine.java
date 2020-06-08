package algorithm;

import lombok.extern.slf4j.Slf4j;

//给定任意的点，问最多有多少个点在同一条直线上
@Slf4j(topic = "MaxPointsOnALine")
public class MaxPointsOnALine {

    public static void main(String[] args) {
        int[][] points = new int[][]{{2, 3}, {3, 3}, {-5, 3}};
        log.info("{}", solve(points));
    }

    private static int solve(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }

        int count = 2;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                // 第一个点取其中2个点
                int[] a = points[i];
                int[] b = points[j];
                if (a[0] == b[0] && a[1] == b[1]) {
                    count++;
                    continue;
                }
                for (int k = j + 1; k < points.length; k++) {
                    // 遍历
                    int[] c = points[k];
                    if (b[0] == a[0]) {
                        if (c[0] == a[0]) {
                            count++;
                            continue;
                        }
                        continue;
                    }
                    if ((b[1] - a[1]) * (c[0] - a[0]) == (c[1] - a[1]) * (b[0] - a[0])) {
                        count++;
                    }

                }


            }
            
        }
        
        return count;
    }

}
