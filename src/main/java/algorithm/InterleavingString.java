package algorithm;

import lombok.extern.slf4j.Slf4j;

//给出三个字符串s1, s2, s3,判断s3是否可以由s1和s2交织而成。
//例如：
//给定
//s1 ="aabcc",
//s2 ="dbbca",
//如果s3 ="aadbbcbcac", 返回true
//如果s3 ="aadbbbaccc", 返回false
@Slf4j(topic = "InterleavingString")
public class InterleavingString {
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        solve(s1, s2, s3);
    }

    private static void solve(String s1, String s2, String s3) {
        if (s3.length() == 0) {
            log.info("{}", true);
            return;
        }
        int i = 0;
        while (i < s1.length()) {
            if (s3.charAt(i) == s1.charAt(i)) {
                i++;
            } else {
                break;
            }
        }
        int j = 0;
        while (j < s2.length()) {
            if (s3.charAt(j) == s2.charAt(j)) {
                j++;
            } else {
                break;
            }
        }
        if (i == 0 && j == 0) {
            log.info("{}", false);
            return;
        }
        if (i > j) {
            String newS1 = s1.substring(i);
            String newS3 = s3.substring(i);
            solve(newS1, s2, newS3);
        } else {
            String newS2 = s2.substring(j);
            String newS3 = s3.substring(j);
            solve(s1, newS2, newS3);
        }
    }
}
