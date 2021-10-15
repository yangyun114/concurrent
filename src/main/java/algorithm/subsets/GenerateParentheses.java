package algorithm.subsets;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
//例如，给出n=3，解集为：
//"((()))", "(()())", "(())()", "()(())", "()()()"
@Slf4j(topic = "GenerateParentheses")
public class GenerateParentheses {
    static List<String> res = new ArrayList<>();
    public static void main(String[] args) {
        int n = 3;
        String origin = "";
        for (int i = 0; i < n; i++) {
            origin += "(";
        }
        for (int i = 0; i < n; i++) {
            origin += ")";
        }
        // 1、先生成基础模式"((()))"，然后生成这种模式的所有排列组合
        // 2、所有排列组合中如果满足合法，则加到结果集
        // 3、注意去重
        solve(origin, "", n * 2);
        log.info("{}", res);
    }

    private static void solve(String origin, String item, int n) {
        if (n == 0) {
            if (match(item))
                res.add(item);
            return;
        }
        for (int i = 0; i < origin.length(); i++) {
            if (i > 0 && origin.charAt(i) == origin.charAt(i - 1))
                continue;
            item += origin.charAt(i);
            solve(origin, item, n - 1);
            item = item.substring(0, item.length() - 1);
        }
    }

    private static boolean match(String item) {
        Stack<Character> stack = new Stack<>();
        if (item == null || item.length() == 0)
            return true;
        for (int i = 0; i < item.length(); i++) {
            char c = item.charAt(i);
            if ('(' == c) {
                stack.push(c);
                continue;
            }
            if (')' == c) {
                if (stack.isEmpty())
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
