package algorithm;

import lombok.extern.slf4j.Slf4j;

// 求1+2+3+...+n
@Slf4j(topic = "SumN")
public class SumN {
    private static int sum = 0;
    // 根据公式计算
    private int method1(int n) {
        return (1 + n) / 2;
    }

    // 迭代
    private int method2(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    // 递归
    private int method3(int n) {
        if (n == 1) {
            return n;
        }
        return method3(n - 1) + n;
    }

    // 短路
    private static int method4(int n) {
        boolean x = n > 1 && method4(n - 1) > 0;
        sum += n;
        return sum;
    }

    public static void main(String[] args) {
        int i = method4(5);
        log.info("{}", i);
    }
}
