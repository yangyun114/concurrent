import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

////评测题目:
//
///**
//手中有数张牌，顺序未知。循环以下操作，将手中的牌堆在桌上：
//
//将手中最上面的一张牌放桌上，然后再将最上面的一张牌放入手中余下的牌底；
//
//最后桌上的牌，从上到下分别为A 2 3 4 5 6 7 8 9 10 J Q K；
//请写代码计算原手中牌的顺序；
//
//时间控制在40分钟以内，代码写完提交在下面。
//
//*/
@Slf4j
public class Solve {
    public static void main(String[] args) {
        String[] arr = {"1", "2", "3", "4", "5", "6"
                , "7", "8", "9", "10", "J", "Q", "K"};
        arr = Arrays.copyOf(arr, 1);
        log.info("{}", arr);
//        final int length = arr.length;
//        String[] res = new String[length];
//        solve(arr, res);
    }

    private static void solve(String[] arr, String[] res) {
        if (arr.length == 0) {
            return;
        }
        arr = Arrays.copyOf(arr, 1);
    }
}
