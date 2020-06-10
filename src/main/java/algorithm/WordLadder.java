package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//给定两个单词（初始单词和目标单词）和一个单词字典，请找出所有的从初始单词到目标单词的最短转换序列：
//每一次转换只能改变一个单词
//每一个中间词都必须存在单词字典当中
//例如：
//给定的初始单词start="hit"，
//目标单词end ="cog"。
//单词字典dict =["hot","dot","dog","lot","log"]
//返回的结果为：
//   [↵    ["hit","hot","dot","dog","cog"],↵    ["hit","hot","lot","log","cog"]↵  ]
//注意：
//题目中给出的所有单词的长度都是相同的
//题目中给出的所有单词都仅包含小写字母
@Slf4j(topic = "WordLadder")
public class WordLadder {
    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        String[] dict = new String[]{"hot","dot","dog","lot","log"};

        List<ArrayList<String>> result = new ArrayList<>();
        solve(start, end, dict, new ArrayList<>(), result);

        int minSize = Integer.MAX_VALUE;
        for (ArrayList<String> item : result) {
            minSize = Math.min(minSize, item.size());
        }
        for (ArrayList<String> item : result) {
            if (minSize == item.size()) {
                log.info("{}", item);
            }
        }

    }

    private static void solve(String start, String end, String[] dict, ArrayList<String> res, List<ArrayList<String>> result) {
        // 终止条件
        if (match(start, end)) {
            log.info("{}", res);
            result.add(new ArrayList<>(res));
            return;
        }
        for (int i = 0; i < dict.length; i++) {
            String item = dict[i];
            if (match(start, item) && !res.contains(item)) {
                res.add(item);
                solve(item, end, dict, res, result);
                res.remove(res.size() - 1);
            }
        }
    }

    private static boolean match(String target, String item) {
        // 判断两个单词是否只需要换一个单词就能相同
        if (target == null || item == null || target.length() != item.length()) {
            return false;
        }
        char[] targetChars = target.toCharArray();
        char[] itemChars = item.toCharArray();
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if (targetChars[i] != itemChars[i]) {
                ++count;
            }
        }
        return count == 1 ? true : false;
    }
}
