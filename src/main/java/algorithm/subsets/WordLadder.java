package algorithm.subsets;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

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
        log.info("{}", result);

        int min = result.stream().mapToInt(i -> i.size()).min().getAsInt();
        log.info("{}", min);

        result.forEach(i -> {
            if (i.size() == min)
                log.info("{}", i);
        });

    }

    private static void solve(String start, String end, String[] dict, List<String> item, List<ArrayList<String>> result) {
        // start和end匹配的时候，终止
        if (match(start, end)) {
            log.info("{}", item);
            result.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < dict.length; i++) {
            // start和dict里面匹配，并且不能已经加到item中
            if (match(start, dict[i]) && !item.contains(dict[i])) {
                item.add(dict[i]);
                solve(dict[i], end, dict, item, result);
                item.remove(item.size() - 1);
            }
        }
    }

    // 判断2个字符串是否是match的
    private static boolean match(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i))
                count++;
        }
        return count == 1;
    }

}
