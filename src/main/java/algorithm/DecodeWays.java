package algorithm;

import lombok.extern.slf4j.Slf4j;

//一条仅包含字母‘A’-‘Z’的消息用下列的方式加密成数字
//'A' -> 1↵'B' -> 2↵...↵'Z' -> 26
//现在给出加密成数字的密文，请判断有多少种解密的方法
//例如：
//给出的密文为“12”，可以解密为"AB"(1 2) 或者"L"(12).
//所以密文"12"的解密方法是2种.
@Slf4j(topic = "DecodeWays")
public class DecodeWays {
    public static void main(String[] args) {
        String str = "12231123";
        int s = solve(str);
        log.info("{}", s);
    }

    private static int solve(String str) {
        if (str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        if (str.length() == 2 && Integer.valueOf(str) < 27) {
            return 2;
        }
        int length = str.length();
        String lastTwo = str.substring(length - 2);
        if (Integer.valueOf(lastTwo) < 27) {
            return solve(str.substring(0, length - 2)) + 2;
        } else {
            return solve(str.substring(0, length - 1)) + 1;
        }
    }
}
