package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

//假设你有一个数组，其中第i个元素表示某只股票在第i天的价格。
//设计一个算法来寻找最大的利润。你可以完成任意数量的交易(例如，多次购买和出售股票的一股)。
// 但是，你不能同时进行多个交易(即，你必须在再次购买之前卖出之前买的股票)。
@Slf4j(topic = "BestTimeToBuyAndSellStocTwo")
public class BestTimeToBuyAndSellStocTwo {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2};
        int res = solve(arr);
        log.info("{}", res);
    }

    private static int solve(int[] arr) {
        int res = 0;
        if (arr == null || arr.length < 2) {
            return res;
        }
        // 如果比前一天大，则累计递增值
        for (int i = 1; i < arr.length; i++) {
            // 注意一定是大于，不能大于等于，比如889这种情况
            if (arr[i] > arr[i - 1]) {
                res += arr[i] - arr[i - 1];
            }
        }
        return res;
    }
}
