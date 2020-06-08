package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Stack;

//给出一个字符串s，分割s使得分割出的每一个子串都是回文串
//计算将字符串s分割成回文分割结果的最小切割数
//例如:给定字符串s="aab",
//返回1，因为回文分割结果["aa","b"]是切割一次生成的
@Slf4j(topic = "PalindromePartitioning")
public class PalindromePartitioning {

    public static void main(String[] args) {
        String str = "abbacd";

        int count = solve(str);
        log.info("{}", count);
    }

    private static int solve(String str) {
        if (str == null || str.length() == 0 || str.length() == 1
            || str.equals(new StringBuilder(str).reverse().toString())) {
            return 0;
        }

        // 首先求str从头开始计算的一个最长回文字符串长度
        int length = getLength(str);
        return solve(str.substring(length)) + 1;
    }

    private static int getLength(String str) {
        if (str == null) {
            return 0;
        }
        if (str.length() < 2) {
            return str.length();
        }
        int count = 0;
        for (int i = 1; i <= str.length(); i++) {
            String newStr = str.substring(0, i);
            if (newStr.equals(new StringBuilder(newStr).reverse().toString())) {
                count = Math.max(count, newStr.length());
            }
        }
        return count;
    }
}
