package algorithm.subsets;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//给出一个仅包含数字的字符串，给出所有可能的字母组合。
//数字到字母的映射方式如下:(就像电话上数字和字母的映射一样)
//
//Input:Digit string "23"Output:["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//注意：虽然上述答案是按字典序排列的，但你的答案可以按任意的顺序给出
@Slf4j(topic = "LetterCombinationOfAPhone")
public class LetterCombinationOfAPhone {
    private static Map<Character, List<Character>> map = new HashMap<>();
    static {
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public static void main(String[] args) {
        String str = "23";
        solve(str, "");
    }

    private static void solve(String str, String item) {
        if (str.length() == 0) {
            log.info(item);
            return;
        }
        List<Character> list = map.get(str.charAt(0));
        for (int i = 0; i < list.size(); i++) {
            item += list.get(i);
            solve(str.substring(1), item);
            item = item.substring(0, item.length() - 1);
        }
    }
}

