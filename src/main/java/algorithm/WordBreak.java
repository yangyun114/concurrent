package algorithm;

import lombok.extern.slf4j.Slf4j;

//给定一个字符串s和一组单词dict，在s中添加空格将s变成一个句子，使得句子中的每一个单词都是dict中的单词
//返回所有可能的结果
//例如：给定的字符串s ="catsanddog",
//dict =["cat", "cats", "and", "sand", "dog"].
//返回的结果为["cats and dog", "cat sand dog"].
@Slf4j(topic = "WordBreak")
public class WordBreak {
    public static void main(String[] args) {
        String str = "catsanddog";
        String[] dict = new String[]{"cat", "cats", "and", "sand", "dog"};
        solve("", str, dict);
    }

    private static void solve(String item, String str, String[] dict) {
        if (str.length() == 0) {
            return;
        }
        if (contain(str, dict)) {
            item = item + " " + str;
            log.info("{}", item);
            return;
        }
        for (int i = 1; i <= str.length(); i++) {
            String sub = str.substring(0, i);
            if (contain(sub, dict)) {
                solve(item + " " + sub, str.substring(i), dict);
            }

        }
    }

    private static boolean contain(String str, String[] dict) {
        // 判断str在dict中是否存在
        for (String s : dict) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
