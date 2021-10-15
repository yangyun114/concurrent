package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

// 实现斐波那契数列
@Slf4j
public class Fibonacci {
    public static void main(String[] args) {
        int n = 6;
        solve1(n);

        int res = solve2(n);
        log.info("{}",res);
    }

    // 1、递归，效率低
    private static int solve1(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        return solve1(n - 1) + solve1(n - 2);
    }

    // 2、循环的方式
    private static int solve2(int n) {
        if (n == 1)
            return 1;
        int one = 0, two = 1, res = 0;
        for (int i = 2; i <= n; i++) {
            res = one + two;
            one = two;
            two = res;
        }
        return res;
    }
}
