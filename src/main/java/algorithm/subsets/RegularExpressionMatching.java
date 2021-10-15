package algorithm.subsets;

import lombok.extern.slf4j.Slf4j;

//请实现支持'.'and'*'.的通配符模式匹配
//'.' 可以匹配任何单个字符。
// '*' 可以匹配任何字符序列（包括空序列）。
// 匹配应该覆盖整个输入字符串（而不是部分）。
// 函数声明为：bool isMatch(const char *s, const char *p)
// 下面给出一些样例：
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "a*") → true
// isMatch("aa", ".*") → true
// isMatch("ab", ".*") → true
// isMatch("aab", "c*a*b") → true
@Slf4j(topic = "RegularExpressionMatching")
public class RegularExpressionMatching {
    // Todo，此题存疑
    public static void main(String[] args) {
        String target = "aab";
        String exp = "c*a*b";
        boolean res = solve(target, exp);
        log.info("{}", res);
    }

    private static boolean solve(String target, String exp) {
        if (exp == null || target == null
                || exp.length() == 0 || target.length() == 0)
            return false;
        for (int i = 0; i < exp.length(); i++) {
            if (target.charAt(0) == exp.charAt(i) || exp.charAt(i) == '.') {
                // 如果exp字符跟target的第一个字符相等，或者exp出现字符'.'，则从当前位置开始比较
                if (match(target, exp.substring(i)))
                    return true;
                else
                    continue;
            } else if (exp.charAt(i) == '*') {
                // 如果exp出现字符'.'，则直接为true
                return true;
            }
        }
        return false;
    }

    private static boolean match(String target, String sub) {
        int i = 0;
        while (i < target.length()) {
            if (i == sub.length())
                // 已经到达了sub的边界
                return false;
            // 字符相等或者为'.'
            if (target.charAt(i) == sub.charAt(i) || sub.charAt(i) == '.') {
                i++;
            } else if (sub.charAt(i) == '*') {
                return true;
            } else {
                return false;
            }
        }
        // 循环解锁，认为匹配
        return true;
    }
}
