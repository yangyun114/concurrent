package algorithm.stack;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
//括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
@Slf4j(topic = "ValidParentheses")
public class ValidParentheses {
    private static Stack<Character> stack = new Stack<>();
    private static List<Character> left = Arrays.asList('(', '{', '[');
    private static List<Character> right = Arrays.asList(')', '}', ']');
    public static void main(String[] args) {
        String str = "([)]";
        boolean res = solve(str);
        log.info("{}", res);
    }

    private static boolean solve(String str) {
        if (str == null)
            return false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 左括号，进栈
            if (left.contains(c)) {
                stack.push(c);
                continue;
            }
            // 右括号，与栈顶元素匹配，则出栈
            if (left.indexOf(stack.peek()) == right.indexOf(c)) {
                stack.pop();
                continue;
            }
            return false;
        }
        return stack.isEmpty();
    }


}
