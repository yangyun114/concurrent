package algorithm.twoponters;

import lombok.extern.slf4j.Slf4j;

//给定n个非负整数a1，a2，…，an，其中每个数字表示坐标(i, ai)处的一个点。以（i，ai）和（i，0）（i=1,2,3...n）为端点画出n条直线。你可以从中选择两条线与x轴一起构成一个容器，最大的容器能装多少水？
//注意：你不能倾斜容器
//例如：
//输入 [1,8,6,2,5,4,8,3,7]
//输出: 49
@Slf4j(topic = "ContainerWithMostWater")
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        int res = solve(arr);
        log.info("{}", res);
    }

    private static int solve(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;
        int i = 0, j = arr.length - 1, res = 0;
        while (i < j) {
            res = Math.max(res, (j - i) * Math.min(arr[i], arr[j]));
            if (arr[i] < arr[j])
                i++;
            else
                j--;
        }
        return res;
    }
}
