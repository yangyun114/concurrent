package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//格雷码是一种二进制编码系统，如果任意两个相邻的代码只有一位二进制数不同，则称这种编码为格雷码（Gray Code）。
//给定一个非负整数n，表示代码的位数，打印格雷码的序列。格雷码序列必须以0开头。
//例如：给定n=2，返回[0,1,3,2]. 格雷码的序列为：
// 00 - 0↵01 - 1↵11 - 3↵10 - 2
//注意：
//对于一个给定的n，格雷码的序列不一定是唯一的，
//例如：根据题目描述，[0,2,3,1]也是一个有效的格雷码序列
@Slf4j(topic = "GrayCode")
public class GrayCode {
    static List<Integer> res = new ArrayList<>();

    public static void main(String[] args) {
        int n = 3;
        solve(n);
    }

    //当n=1时，为[0,1]
    //当n=2时，为[00,01,11,10]
    //当n=3时，为[000,001,011,010,110,111,101,100]
    //由此可以看出新的序列其实是在前面序列基础上插入新的值
    //其中前半部分的数值不变，后半部分的数值为上个序列中每个元素第n个位变1，逆向插入
    // n=1 返回 0 1
    // n=2 返回 0 1 3 2
    // n=3 返回 0 1 3 2 6 7 5 4
    // *观察规律：3=1+2,2=0+2; 6=2+4,7=3+4,5=1+4,4=0+4
    private static void solve(int n) {
        res.add(0);
        if (n == 0) {
            return;
        }
        res.add(1);
        if (n == 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int size = res.size();
//            int increase = (int) Math.pow(2, i);
            int increase = 1 << i;
            log.info("{}", increase);
            for (int j = size - 1;j >= 0;j--) {
                res.add(res.get(j) + increase);
            }
        }
        log.info("{}", res);
    }
}
