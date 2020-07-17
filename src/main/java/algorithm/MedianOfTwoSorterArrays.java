package algorithm;

import lombok.extern.slf4j.Slf4j;

//有两个大小分别为m和n的有序数组A和B。请找出这两个数组的中位数。你需要给出时间复杂度在O(log (m+n))以内的算法。
@Slf4j(topic = "MedianOfTwoSorterArrays")
public class MedianOfTwoSorterArrays {
    public static void main(String[] args) {
        int[] a = {1, 2, 5};
        int[] b = {1, 3, 4};
        float res = solve(a, b);
        log.info("{}", res);
    }

    private static float solve(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] c = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m || j < n) {
            if (i == m) {
                c[k] = b[j];
                j++;
                k++;
                continue;
            }
            if (j == n) {
                c[k] = a[i];
                i++;
                k++;
                continue;
            }
            if (a[i] < b[j]){
                c[k] = a[i];
                i++;
                k++;
            } else {
                c[k] = b[j];
                j++;
                k++;
            }
        }
        log.info("{}", c);
        log.info("{}", c[c.length / 2 - 1]);
        log.info("{}", c[c.length / 2]);

        return (m + n) % 2 == 0 ? (float) (c[c.length / 2 - 1] + c[c.length / 2]) / 2f : c[c.length / 2];
    }
}
