package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

// 大数相加
@Slf4j(topic = "BigNumSum")
public class BigNumSum {
    public static void main(String[] args) {
        String a = "123456";
        String b = "123";
        solve(a, b);
    }

    // 转化为字符串进行操作
    private static String solve(String a, String b) {
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();

        char[] res = new char[Math.max(a.length(), b.length())];
        int i = 0, j = 0, k = 0;
        // 进位标识
        boolean flag = false;
        while (i < a.length() || j < b.length()) {
            if (i == a.length()) {
                continue;
            }
            if (j == b.length()) {
                continue;
            }
            Integer add = add(a.charAt(a.length() - 1 - i), b.charAt(b.length() - 1 - j));
            if (add >= 10) {
                add -= 10;
                res[res.length - 1 - k] = String.valueOf(add).charAt(0);
                flag = true;
            } else {
                add -= 10;
                flag = false;
            }
        }


        return res.toString();
    }

    // 计算2个char相加
    private static Integer add(char a, char b) {
        return Integer.valueOf(a) + Integer.valueOf(b);
    }
}
