package algorithm;

import lombok.extern.slf4j.Slf4j;

//字符串"PAYPALISHIRING"写成3行的Z字形的样式如下：
// P   A   H   N
// A P L S I I G
// Y   I   R
//
//按行读这个Z字形图案应该是 "PAHNAPLSIIGYIR"
//请编写代码完成将字符串转化为指定行数的Z字形字符串：
//string convert(string text, int nRows);
@Slf4j(topic = "ZigzagConversion")
public class ZigzagConversion {
    public static void main(String[] args) {
//        String str = "PAHNAPLSIIGYIR";
        String str = "1234567890";
        int n = 3;
        solve(str, n);
    }

    private static void solve(String str, int n) {
        if (n < 1)
            return;
        for (int i = 1; i <= n; i++) {
            int j = i - 1;
            if (j == 0 || j == n - 1) {
                while (j >= 0 && j <= str.length() - 1) {
                    if (j % (2 * n - 2) == 0) {
                        System.out.print(str.charAt(j));
                    } else {
                        System.out.print(" ");
                    }
                    j = j + 2;
                }
            } else {
                while (j >= 0 && j <= str.length() - 1) {
                    if (j % (2 * n - 2) == 0 || (j + 1) % (2 * n - 2) == 0) {
                        System.out.print(str.charAt(j));
                    } else {
                        System.out.print(" ");
                    }
                    j = j + 2;
                }
            }
            System.out.print("\n");

        }
    }
}
