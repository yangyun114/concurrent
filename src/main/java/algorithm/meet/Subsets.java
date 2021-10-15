package algorithm.meet;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给出一个可能包含重复元素的整数集合S，返回该整数集合的所有子集。
//注意：
//你给出的子集中的元素要按非递增的顺序排列
//给出的解集中不能包含重复的子集
//例如：
//如果S =[1,2,2], 给出的解集应该是：
// [↵  [2],↵  [1],↵  [1,2,2],↵  [2,2],↵  [1,2],↵  []↵]
@Slf4j(topic = "Subsets")
public class Subsets {
    static List<List<Integer>> res = new ArrayList<>();
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        solve(list, new ArrayList<>(), 0);
        log.info("{}", res);
    }

    private static void solve(List<Integer> list, List<Integer> item, int temp) {
        res.add(new ArrayList<>(item));

        for (int i = temp; i < list.size(); i++) {
            item.add(list.get(i));
            solve(list, item, i + 1);
            item.remove(item.size() - 1);
        }
    }
}
