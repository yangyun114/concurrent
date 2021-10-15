package algorithm.subsets;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//给定一个字符串S和一个字符串T，计算S中的T的不同子序列的个数。
//字符串的子序列是由原来的字符串删除一些字符（也可以不删除）在不改变相对位置的情况下的剩余字符（例如，"ACE"is a subsequence of"ABCDE"但是"AEC"不是）
//例如：
//S ="rabbbit", T ="rabbit"
//返回3
@Slf4j(topic = "DistinctSubsequences")
public class DistinctSubsequences {
    static int count = 0;

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        solve(s, t);
        log.info("{}", count);
    }

    private static void solve(String s, String t) {
        if (t.length() == 0) {
            count++;
            return;
        }
        char firstChar = t.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (firstChar == s.charAt(i)) {
                solve(s.substring(i + 1), t.substring(1));
            }
        }
    }
}
