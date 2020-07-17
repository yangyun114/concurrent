package algorithm;

import lombok.extern.slf4j.Slf4j;

//将给出的整数x翻转。
//例1:x=123，返回321
//例2:x=-123，返回-321
//你有思考过下面的这些问题么？
//如果整数的最后一位是0，那么输出应该是什么？比如10,100
//你注意到翻转后的整数可能溢出吗？假设输入是32位整数，则将翻转10000000003就会溢出，你该怎么处理这样的样例？抛出异常？这样做很好，但是如果不允许抛出异常呢？这样的话你必须重新设计函数（比如添加一个额外的参数）。
@Slf4j(topic = "ReverseInteger")
public class ReverseInteger {
    public static void main(String[] args) {
        Integer i = -100;
        Integer res = solve(i);
        log.info("{}", res);

        // char和int的等值转换，比如从'0'转为0
        char c = '1';
        int n = c - '0';
        log.info("{}", n);
    }

    private static Integer solve(Integer i) {
        boolean flag = true;
        if (i < 0) {
            flag = false;
            i = -i;
        }
        Long value = Long.valueOf(new StringBuilder().append(i).reverse().toString());
        if (value > Integer.MAX_VALUE)
            return 0;
        return flag ? value.intValue() : -value.intValue();
    }

}

//实现函数 atoi 。函数的功能为将字符串转化为整数
//提示：仔细思考所有可能的输入情况。这个问题故意描述的很模糊（没有给出输入的限制），你需要自己考虑所有可能的情况。
@Slf4j(topic = "StringToInteger")
class StringToInteger {
    public static void main(String[] args) {
        String str = "- 123";
        Integer res = solve(str);
        log.info("{}", res);
    }

    private static Integer solve(String str) {
        // 1、考虑正负号
        // 2、空格
        // 3、值溢出（大于或小于Integer的最大值）
        // 4、中间存在字母
        if (str == null || str.length() == 0)
            return null;

        // 正数true，负数false
        boolean flag = true;
        // 开始位置可能从0号位置开始，如果为负，从1号位置开始
        int index = 0;
        if (str.charAt(0) == '-') {
            index++;
            flag = false;
        }

        Integer res = 0;
        while (index < str.length()) {
            char c = str.charAt(index);
            // 如果为空格，
            if (c == ' ') {
                index++;
                continue;
            }
            if (c - '0' >= 0 && c - '9' <= 0) {
                // 使用try-catch处理越界
                try {
                    res = res * 10 + (c - '0');
                } catch (Exception e) {
                    return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            } else {
                // 如果不在上面范围内，跳出循环
                break;
            }
            index++;
        }
        return flag ? res : -res;
    }
}
