package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 求给定字符串最长不重复子串
@Slf4j(topic = "LongestString")
public class LongestString {

    public static void main(String[] args) {
        log.info("{}", solve("abcabcbb"));
    }

    private static String solve(String str) {
        if (str == null || str.length() == 0)
            return null;
        if (str.length() == 1)
            return str;
        int i = 0, j = 0, maxLength = 0;
        String res = null;
        Map<Character, Integer> map = new HashMap<>();
        while (i < str.length()) {
            while (j < str.length()){
                // 如果包含了，则更新最大长度并退出循环
                if (map.containsKey(str.charAt(j))) {
                    if (j - i > maxLength) {
                        maxLength = j - i;
                        res = str.substring(i, j);
                    }
                    break;
                }
                map.put(str.charAt(j), j);
                j++;
            }
            // 如果j已经到达边界结束
            if (j == str.length())
                return res;
            // 删除左边界到j地方的所有字符
            int index = map.get(str.charAt(j));
            while (i <= index) {
                map.remove(str.charAt(i));
                i++;
            }
        }
        return res;
    }

    // 改进
    // 1、如果已经包含右边界，动态更新左边界
    // 2、不重复字符串为窗口的值
    // 3、右边界移动时，更新map中的值，不需要删除废弃的左边界中的值，因为左边界是动态维护的

}
