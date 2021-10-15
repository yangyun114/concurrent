package algorithm.subsets;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//给定一个字符串s和一组单词dict，在s中添加空格将s变成一个句子，使得句子中的每一个单词都是dict中的单词
//返回所有可能的结果
//例如：给定的字符串s ="catsanddog",
//dict =["cat", "cats", "and", "sand", "dog"].
//返回的结果为["cats and dog", "cat sand dog"].
@Slf4j(topic = "WordBreak")
public class WordBreak {
    static List<List<String>> res = new ArrayList<>();
    public static void main(String[] args) {
        String str = "catsanddog";
        String[] dict = new String[]{"cat", "cats", "and", "sand", "dog"};
        solve(new ArrayList<>(), str, Arrays.asList(dict));
        List<String> collect = res.stream().map(i -> String.join(" ", i)).collect(Collectors.toList());
        log.info("{}", collect);
    }

    // 用list记录好操作一点
    private static void solve(List<String> item, String str, List<String> dict) {
        if (str.isEmpty()) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 1; i <= str.length(); i++) {
            String sub = str.substring(0, i);
            if (dict.contains(sub)) {
                // 回溯法
                item.add(sub);
                solve(item, str.substring(i), dict);
                item.remove(item.size() - 1);
            }
        }
    }

}
