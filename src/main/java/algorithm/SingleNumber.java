package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//现在有一个整数类型的数组，数组中只有一个元素只出现一次，其余元素都出现三次。你需要找出只出现一次的元素
//注意：
//你需要给出一个线性时间复杂度的算法，你能在不使用额外内存空间的情况下解决这个问题么？
@Slf4j(topic = "SingleNumber")
public class SingleNumber {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2};
//        log.info("{}", solve(arr));

        log.info("{}", 1^2^3);
        log.info("{}", 3^1^2);

    }

    // 使用hashmap
    private static int solve(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
                continue;
            }
            map.put(i, 1);
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() == 1) {
                return next.getKey();
            }
        }
        return -1;
    }

    // 不使用hashmap


    // 提供一个额外算法，使用set获取所有不重复的元素，然后把值进行累加 * 3，再减去原有数组的累加值 / 2即可得到相应数字

    // 如果是两个重复，则根据异或的性质
    // 1、两个相同的数进行异或，返回0
    // 2、0跟任何数进行异或，等于该数
    // 3、异或具有交换性
    private int singleNum(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }
        return res;
    }
}
