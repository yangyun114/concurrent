package algorithm.others;

import lombok.extern.slf4j.Slf4j;

//编写一个函数来查找字符串数组中的最长公共前缀
@Slf4j(topic = "LongestCommonPrefix")
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] arr = {"ab", "ac"};
        String res = solve(arr);
        log.info("{}", res);
    }

    private static String solve(String[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        int minLength = Integer.MAX_VALUE;
        String minString = null;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() < minLength) {
                minLength = arr[i].length();
                minString = arr[i];
            }
        }
        int i = 0;
        while (i < minLength) {
            char c = minString.charAt(i);
            for (int j = 0; j < arr.length; j++) {
                String item = arr[j];
                if (item.charAt(i) != c) {
                    return minString.substring(0, i);
                }
            }
            i++;
        }
        return minString;
    }
}
