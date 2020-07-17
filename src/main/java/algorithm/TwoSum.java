package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;

//给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
//你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
//假设给出的数组中只存在唯一解
//例如：
//给出的数组为 {2, 7, 11, 15},目标值为9
//输出 ndex1=1, index2=2
@Slf4j(topic = "TwoSum")
public class TwoSum {
    static HashMap<Integer, Integer> map = new HashMap<>();
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        solve(arr, target);
        log.info("{}", list);
    }

    private static void solve(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])){
                list.add(map.get(target - arr[i]) + 1);
                list.add(i + 1);
                return;
            }
            map.put(arr[i], i);
        }
    }
}
