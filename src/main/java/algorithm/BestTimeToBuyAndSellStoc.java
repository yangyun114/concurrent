package algorithm;

import lombok.extern.slf4j.Slf4j;

//假设你有一个数组，其中第i个元素是某只股票在第i天的价格。
//设计一个算法来求最大的利润。你最多可以进行两次交易。
//注意:
//你不能同时进行多个交易(即，你必须在再次购买之前出售之前买的股票)。
@Slf4j(topic = "BestTimeToBuyAndSellStoc")
public class BestTimeToBuyAndSellStoc {

    public static void main(String[] args) {
        int[] arr = {1, 4, 2};
        int res = solve(arr);
        log.info("{}", res);
    }

    private static int solve(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }
        // 理解为进行4次交易，余下的钱要求最大
        // 第一次交易为买，余额为最小值
        int buy1 = Integer.MIN_VALUE;
        // 第二次交易为卖，余额设置为0
        int sell1 = 0;
        // 同理第三次和第四次交易
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;

        for (int i = 0; i < arr.length; i++) {
            // 第一次买维护一个最大值，注意是取数组值的负数，buy1表示余额
            buy1 = Math.max(buy1, -arr[i]);
            // 第一次卖也维护一个最大值，取值等于数组值和第一次买的值的和，sell1也表示余额
            sell1 = Math.max(sell1, arr[i] + buy1);
            // 第三次和第四次同理
            buy2 = Math.max(buy2, sell1 - arr[i]);
            sell2 = Math.max(sell2, arr[i] + buy2);
        }

        // 返回最后的余额
        return sell2;
    }
}
