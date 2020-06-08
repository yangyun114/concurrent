package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//给定一个字符串s，分割s使得s的每一个子串都是回文串
//返回所有的回文分割结果。（注意：返回结果的顺序需要和输入字符串中的字母顺序一致。）
//例如:给定字符串s="aab",
//返回
//  [↵    ["aa","b"],↵    ["a","a","b"]↵  ]
@Slf4j(topic = "PalindromePartitioningOne")
public class PalindromePartitioningOne {
    public static void main(String[] args) {
        String str = "aab";
        List<String> item = new ArrayList<>();
        solve(item, str);
    }

    // 如果要求输出所有可能的解，往往都是要用深度优先搜索。如果是要求找出最优的解，或者解的数量，往往可以使用动态规划
    private static void solve(List<String> item, String str) {
        if ("".equals(str)) {
            log.info("{}", item);
            return;
        }
        for (int i = 1; i <= str.length(); i++) {
            String front = str.substring(0, i);
            String back = str.substring(i);
            if (isPalindrome(front)) {
                item.add(front);
                solve(item, back);
                item.remove(item.get(item.size() - 1));
            }
        }
    }


    // 判断一个字符串是不是回文的
    private static boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}
