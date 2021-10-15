package algorithm.twoponters;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//判断题目给出的字符串是不是回文，仅考虑字符串中的字母字符和数字字符，并且忽略大小写
//例如："A man, a plan, a canal: Panama"是回文
//"race a car"不是回文
//注意：
//你有没有考虑过字符串可能为空？这是面试时应该提出的一个好问题。
//针对这个问题，我们定义空字符串是回文
@Slf4j(topic = "ValidPalindrome")
public class ValidPalindrome {
    public static void main(String[] args) {
        String str = "race a car";
        log.info("{}", solve(str.toLowerCase()));

    }

    private static boolean solve(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            char start = str.charAt(i);
            char end = str.charAt(j);
            if (!match(start)) {
                i++;
                continue;
            }
            if (!match(end)) {
                j--;
                continue;
            }
            if (start == end) {
                i++;
                j--;
                continue;
            }
            return false;
        }
        return i >= j;
    }

    // 判断是否是字母或者数字
    private static boolean match(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('Z' <= c && c <= 'Z');
    }
}
