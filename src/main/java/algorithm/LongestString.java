package algorithm;

import lombok.extern.slf4j.Slf4j;

// 求给定字符串最长不重复子串
@Slf4j(topic = "LongestString")
public class LongestString {

    public static void main(String[] args) {
        log.info("{}", solve("abcabcbb"));
    }

    private static String solve(String param) {
        if (param.length() == 0) {
            return null;
        }
        if (param.length() == 1) {
            return param;
        }
        String res = null;
        int maxLength = 0;
        int i = 0, j = 1;
        while (i < param.length()) {
            while (j < param.length() && param.charAt(i) != param.charAt(j)) {
                j++;
            }
            if (j - i > maxLength) {
                maxLength = j - i;
                res = param.substring(i, j);
            }
            i++;
            j = i + 1;
        }
        return res;
    }
}
