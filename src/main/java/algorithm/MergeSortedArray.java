package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给出两个有序的整数数组A和B，请将数组B合并到数组A中，变成一个有序的数组
//注意：
//可以假设A数组有足够的空间存放B数组的元素，A和B中初始的元素数目分别为m和n
@Slf4j(topic = "MergeSortedArray")
public class MergeSortedArray {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(Arrays.asList(1, 3, 5));
        List<Integer> b = new ArrayList<>(Arrays.asList(2, 4, 6));
        solve(a, b);
    }

    private static void solve(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < b.size(); i++) {
            Integer insert = b.get(i);
            int index = 0;
            while (index < a.size() && a.get(index) < insert) {
                index++;
            }
            a.add(-1);
            for (int j = a.size() - 1; j > index; j--) {
                a.set(j, a.get(j - 1));
            }
            a.set(index, insert);
        }
        log.info("{}", a);
    }

}
