package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//给出两个整数n和k，返回从1到n中取k个数字的所有可能的组合
//例如：
//如果n=4，k=2，结果为
@Slf4j(topic = "Combinations")
public class Combinations {
    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        solve(n, k, new ArrayList<>());
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
