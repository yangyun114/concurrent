package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j(topic = "MinTwo")
public class MinTwo {
    public static void main(String[] args) {
        List<Integer> param = Arrays.asList(2, 4, 5, 3, 2, 1);

//        1, 2, 3, 2




        int index = getMaxIndex(param);
        int a = (1 + 0) / 2;
        log.info("{}", a);
    }

    private static int getMaxIndex(List<Integer> param) {
        int start = 0, end = param.size() - 1, mid = (start + end) / 2;

        while (mid > 0 && mid < param.size() - 1) {
            if (param.get(mid) > param.get(mid - 1) && param.get(mid) > param.get(mid + 1)) {
                // 此时刚好就是mid
                return mid;
            } else if (param.get(mid) < param.get(mid + 1)) {
                // 此时位于递增段
                start = mid + 1;
                mid = start + (end - start) / 2;
            } else {
                // 此时位于递减段
                end = mid - 1;
                mid = start + (end - start) / 2;
            }
        }
        return -1;
    }
}
