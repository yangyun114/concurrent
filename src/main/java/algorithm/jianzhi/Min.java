package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

//“把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
// 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
// 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1”
@Slf4j
public class Min {
    public static void main(String[] args) {
        List<Integer> param = Arrays.asList(3, 4, 5, 1, 2);
        solve1(param);

        int min = solve2(param);
        log.info("{}", min);
    }

    // 1、暴力解法，复杂度O（n），不推荐
    private static int solve1(List<Integer> param) {
        int min = Integer.MAX_VALUE;
        for (Integer i : param) {
            min = Math.min(min, i);
        }
        return min;
    }

    // 2、二分查找
    private static int solve2(List<Integer> param) {
        int start = 0, end = param.size() - 1;
        while (start < end) {
            if (end - start == 1) {
                // 当只存在2个元素时，第二个元素一定就是我们所需要的最小值
                return param.get(end);
            }
            int mid = (start + end) / 2;

            // 考虑到可能存在特例，中间值 = 开头值 = 结尾值，此时只能对这个序列采取暴力查找
            if (param.get(mid) == param.get(start) && param.get(mid) == param.get(end)) {
                return solve1(param.subList(start, end + 1));
            }

            if (param.get(mid) >= param.get(start)) {
                // 如果中间值比开头大，说明中间值位于前半部分递增序列，此时取后半段
                start = mid;
            } else if (param.get(mid) <= param.get(end)){
                // 如果中间值比结尾小，说明中间值位于后半部分递增序列，此时取前半段
                end = mid;
            }
        }
        return -1;
    }
}
