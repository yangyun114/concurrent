package algorithm;

import lombok.extern.slf4j.Slf4j;

//给出两个字符串S和T，要求在O（n）的时间复杂度内在S中找出最短的包含T中所有字符的子串。
//例如：
//S ="ADOBECODEBANC"
//T ="ABC"
//找出的最短子串为"BANC".
//注意：
//如果S中没有包含T中所有字符的子串，返回空字符串 “”；
//满足条件的子串可能有很多，但是题目保证满足条件的最短的子串唯一。
@Slf4j(topic = "MinimumWindowSubstring")
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        log.info("{}", contain(s, t));
        solve(s, t);
    }

    private static void solve(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return;
        }
        int i = 0, res = Integer.MAX_VALUE;
        while (i < s.length()) {
            int j = i;
            while (!contain(s.substring(i, j), t)) {
                // 如果不包含t，则j向后移动
                j++;
                // 如果j已经找到最后了，还没找到，则直接结束
                if (j > s.length()) {
                    log.info("{}", res);
                    return;
                }
            }
            res = Math.min(res, j - i);

            i++;
            while (!contain(t, String.valueOf(s.charAt(i)))) {
                i++;
            }
        }
    }

    private static boolean contain(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            if (s.indexOf(String.valueOf(t.charAt(i))) == -1) {
                return false;
            }
        }
        return true;
    }
}
