package algorithm;

import lombok.extern.slf4j.Slf4j;

//给出n个数字，代表直方图的条高，直方图每一条的宽度为1，请计算直方图中最大矩形的面积
@Slf4j(topic = "LargestRectangleInHistogram")
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] arr = {2,1,5,6,2,3};
        solve(arr);
    }

    private static void solve(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        int res = Math.min(arr[i], arr[j]) * arr.length;
        while (i <= j) {
            if (arr[i] < arr[i+1]) {

            }
        }
    }
}
