package algorithm.jianzhi;

import lombok.extern.slf4j.Slf4j;

// “请实现一个函数，把字符串中的每个空格替换成"%20"。例如输入“We are happy.”，则输出“We%20are%20happy.”。”
@Slf4j
public class ReplaceBlank {
    public static void main(String[] args) {
        String str = "We are happy.";
        String res = solve(str);
    }

    // 常规思路：从前往后遍历，每碰到一个空格，就把后面的元素向后移动2个单位，然后把3个空出来的单位替换为目标字符串，复杂度n^2
    // 优化方案：从后往前遍历，事先遍历出空格的个数，那么最后一个字符向后移动的单位是确定的。比如，'.'向后移动4个单位，（2个空格 * 2），进而可以确认所有字符的位置
    private static String solve(String str) {
        // 空格个数，原始字符串长度
        int blankNum = 0, originalEnd = str.length(), i = 0;
        while (i <= originalEnd) {
            if (str.charAt(i) == ' ')
                ++blankNum;
            ++i;
        }
        int resEnd = originalEnd + 2 * blankNum;
        return null;
    }
}
