package algorithm;

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
        String str = "A man, a plan, a canal: Panama";
        log.info("{}", solve(str));

    }

    private static boolean solve(String str) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Pattern.matches("^[A-Za-z0-9]+$", String.valueOf(c))) {
                list.add(Character.toLowerCase(c));
            }
        }
        log.info("{}", list);
        int start = 0, end = list.size() - 1;
        while (start < end) {
            if (list.get(start).equals(list.get(end))) {
                ++start;
                --end;
            } else {
                return false;
            }
        }
        if (start >= end) {
            return true;
        }
        return false;
    }
}
