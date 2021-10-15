package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//给出两个整数n和k，返回从1到n中取k个数字的所有可能的组合
//例如：
//如果n=4，k=2，结果为
@Slf4j(topic = "Combinations")
public class Combinations {
    public static void main(String[] args) {
//        int n = 4;
//        int k = 2;
//        solve(n, k, new ArrayList<>());

        // 给定字符串和正整数，给出排列
        // 给定ab，2，返回aa，bb，ab，ba
        String str = "ab";
        int m = 2;
        List<String> res = new ArrayList<>();
        solve1(str, m, "", res);
        log.info("{}", res);
    }

    private static void solve1(String str, int m, String item, List<String> res) {
        if (m == 0) {
            res.add(item);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            item += str.charAt(i);
            solve1(str, m - 1, item, res);
            item = item.substring(0, item.length() - 1);
        }
    }

    private static void solve(int n, int k, List<Integer> list) {
        if (k == 0) {
            log.info("{}", list);
            return;
        }
        for (int i = 1; i <= n; i++) {
            list.add(i);
            solve(n, k - 1, list);
            list.remove(list.size() - 1);
        }
    }
}
