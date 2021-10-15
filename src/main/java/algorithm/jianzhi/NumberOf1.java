package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

//“请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。例如把9表示成二进制是1001，有2位是1。因此如果输入9，该函数输出2”
@Slf4j(topic = "NumberOf1")
public class NumberOf1 {
    public static void main(String[] args) {
        int i = 9;
        int count = numberOf13(9);
        log.info("{}", count);
    }

    // 这种方法无法计算负数
    private static int numberOf1(int i) {
        int count = 0;
        while (i != 0) {
            // 右移的过程中，如果最右边的值为1，则把count++
            if ((i & 1) == 1)
                count++;
            // 当i != 0时，一直把i右移
            i = i >> 1;
        }
        return count;
    }

    // 这种方式可以计算负数，但是需要循环32次
    private static int numberOf12(int n) {
        int count = 0, flag = 1;
        // 把flag左移
        for (int i = 0; i < 32; i++) {
            // 如果i和flag与等于flag本身，说明i此时最高位的值为1
            if ((n & flag) == flag)
                count++;
            flag = flag << 1;
        }
        return count;
    }

    // 最优算法，循环次数和1的个数相等
    // 从右到左，把1变成0，每变一个就把count自增；
    // 把1变成0的方法，就是n & n - 1
    private static int numberOf13(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= n - 1;
        }
        return count;
    }
}
