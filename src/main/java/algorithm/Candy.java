package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//有N个小朋友站在一排，每个小朋友都有一个评分
//你现在要按以下的规则给孩子们分糖果：
//每个小朋友至少要分得一颗糖果
//分数高的小朋友要他比旁边得分低的小朋友分得的糖果多
//你最少要分发多少颗糖果？
//输入，[1,2,2]，输出4
@Slf4j(topic = "Candy")
public class Candy {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2};
        int res = solve(arr);
        log.info("{}", res);
    }

    private static int solve(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 1、初始情况下，一人给一颗
        for (int i = 0; i < arr.length; i++) {
            map.put(i, 1);
        }

        // 2、从左到右扫描，如果左边比右边小，则右边的糖果数+1
        for (int i = 1; i < arr.length; i++) {
            // 后面那个and条件可加可不加，因为第一次所有值是相等的
            if (arr[i - 1] < arr[i] && map.get(i - 1) >= map.get(i)) {
                map.put(i, map.get(i) + 1);
            }
        }

        // 3、从右到左扫描，如果左边比右边大，则左边糖果数+1
        for (int i = arr.length - 1; i > 0 ; i--) {
            // 注意此时map.get(i - 1) <= map.get(i)表示，左边的糖果数必须比右边的要小，不然会出现重复分配，
            // 比如1， 2， 1这种情况，从左到右时第二个小朋友会分配一次，从右到左还会分配一次
            if (arr[i - 1] > arr[i] && map.get(i - 1) <= map.get(i)) {
                map.put(i - 1, map.get(i - 1) + 1);
            }
        }

        return map.entrySet().stream().mapToInt(i -> i.getValue()).sum();
    }
}
