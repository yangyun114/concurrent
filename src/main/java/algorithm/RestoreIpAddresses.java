package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//现在有一个只包含数字的字符串，将该字符串重新存储成IP地址的形式，返回所有可能的情况。
//例如：
//给出的字符串为"25525511135",
//返回["255.255.11.135", "255.255.111.35"]. (顺序没有关系)
@Slf4j(topic = "RestoreIpAddresses")
public class RestoreIpAddresses {
    private static List<List<String>> res = new ArrayList<>();
    public static void main(String[] args) {
        String str = "25525511135";
        solve(str, new ArrayList<>());

        log.info("{}", res);

        List<String> collect = res.stream().map(i -> String.join(".", i)).collect(Collectors.toList());
        log.info("{}", collect);
    }

    private static void solve(String str, List<String> item) {
        if (str.length() == 0) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 1; i <= str.length(); i++) {
            String sub = str.substring(0, i);
            String tail = str.substring(i);
            if (match(sub) && item.size() < 4) {
                item.add(sub);
                solve(tail, item);
                item.remove(item.size() - 1);
            }
        }
    }

    private static boolean match (String item) {
        if (0 < Long.valueOf(item) && Long.valueOf(item) < 256) {
            return true;
        }
        return false;
    }
}
