package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

// 中文转数字
@Slf4j(topic = "Chinese2Number")
public class Chinese2Number {
    // 亿、万、千、百、十
    public static void main(String[] args) {
        String str = "三百零五亿零九百八十五万零三十";
        solve(str);
    }

    private static void solve(String str) {
        int yiIndex = str.indexOf("亿");
        int wanIndex = str.indexOf("万");
        log.info("{}, {}", yiIndex, wanIndex);

        String yi = str.substring(0, yiIndex);
        String wan = str.substring(yiIndex + 1, wanIndex);
        String ge = str.substring(wanIndex + 1);

        toNumber(yi);

    }

    private static void toNumber(String str) {
        int qianIndex = str.indexOf("千");
        int baiIndex = str.indexOf("百");
        int shiIndex = str.indexOf("十");

        String qian = null, bai = null, shi = null;
        if (qianIndex != -1)
            qian = str.substring(0, qianIndex);
        if (baiIndex != -1) {
            if (qianIndex != -1)
                bai = str.substring(qianIndex + 1, baiIndex);
            else
                bai = str.substring(0, baiIndex);
        }
        if (shiIndex != -1) {

        }
    }

    private static int getNumber(String str) {
        switch (str) {
            case "一" : return 1;
            case "二" : return 2;
            case "三" : return 3;
            case "四" : return 4;
            case "五" : return 5;
            case "六" : return 6;
            case "七" : return 7;
            case "八" : return 8;
            case "九" : return 9;
        }
        return -1;
    }
}
