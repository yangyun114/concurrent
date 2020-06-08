package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

// 计算后缀表达式的值
@Slf4j(topic = "PreExpression")
public class PreExpression {

    public static void main(String[] args) {
        String[] arr = {"1", "2", "-"};
        log.info("{}", solve(arr));
    }

    private static int solve(String[] arr) {
        int res = 0;
        Stack<String> stack = new Stack<>();
        for (String s : arr) {
            if ("+".equals(s)) {
                String x = stack.pop();
                String y = stack.pop();
                stack.push(String.valueOf(Integer.parseInt(x) + Integer.parseInt(y)));
                continue;
            }
            if ("-".equals(s)) {
                String x = stack.pop();
                String y = stack.pop();
                stack.push(String.valueOf(Integer.parseInt(y) - Integer.parseInt(x)));
                continue;
            }
            if ("*".equals(s)) {
                String x = stack.pop();
                String y = stack.pop();
                stack.push(String.valueOf(Integer.parseInt(x) * Integer.parseInt(y)));
                continue;
            }
            if ("/".equals(s)) {
                String x = stack.pop();
                String y = stack.pop();
                stack.push(String.valueOf(Integer.parseInt(y) / Integer.parseInt(x)));
                continue;
            }
            stack.push(s);
        }
        return Integer.parseInt(stack.peek());
    }
}
