package algorithm;

import lombok.extern.slf4j.Slf4j;

//找出给出的字符串S中最长的回文子串。假设S的最大长度为1000，并且只存在唯一解。
@Slf4j(topic = "LongestPalindromicSubstring")
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String str = "abcba";
        String res = solve(str);
    }

    static int left = 0, maxLength = 0;
    private static String solve(String str) {
        if (str == null)
            return null;
        for (int i = 0; i < str.length(); i++) {
            // aba
            find(str, i, i);
            // aa
            find(str, i, i + 1);
        }
        return str.substring(left, left + maxLength);
    }

    // 同时向两边延伸
    private static void find(String str, int i, int j) {
        while (i > -1 && j < str.length() && str.charAt(i) == str.charAt(j)) {
            i--;
            j++;
        }
        left = i + 1;
        maxLength = Math.max(maxLength, j - i - 1);
    }


}
