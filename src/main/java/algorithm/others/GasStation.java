package algorithm.others;

import lombok.extern.slf4j.Slf4j;

//环形路上有n个加油站，第i个加油站的汽油量是gas[i].
//你有一辆车，车的油箱可以无限装汽油。从加油站i走到下一个加油站（i+1）花费的油量是cost[i]，你从一个加油站出发，刚开始的时候油箱里面没有汽油。
//求从哪个加油站出发可以在环形路上走一圈。返回加油站的下标，如果没有答案的话返回-1。
//注意：
//答案保证唯一。
@Slf4j(topic = "GasStation")
public class GasStation {
    public static void main(String[] args) {
        int[] gas = {2, 3, 1};
        int[] cost = {3, 1, 2};

        int res = solve(gas, cost);
        log.info("{}", res);
    }

    private static int solve(int[] gas, int[] cost) {
        // 假如从位置0开始，那么能走到下一个位置的条件是
        // gas[0] >= cost[0]也就是gas[0] - cost[0] >= 0
        // 继续能往下走的条件是
        // (gas[0] - cost[0]) + (gas[1] - cost[1]) >= 0
        // 所以假设start从gas最后一个位置开始，end从gas第一个位置，
        // 如果start能到end，则end往前进一步，如果start不能到end，那么start + 1也必然到不了end，所以start只能退后一步
        // 最后有解时，必然是从start到end走了一整圈，也就是start == end时，有解
        int start = gas.length - 1;
        int end = 0;
        // 用left记录余下的油量
        int left = gas[start] - cost[start];
        while (start > end) {
            if (left >= 0) {
                // 油量充足时，先计算剩下多少油，再end前进
                left += gas[end] - cost[end];
                ++end;
            } else {
                // 油量没了，说明从当前start到不了当前end，先start退后一格，此时end是不用动的，再累计一下油量
                --start;
                left += gas[start] - cost[start];
            }
        }
        return left >= 0 ? start : -1;
    }
}
