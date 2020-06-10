package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

//给出一个三角形，计算从三角形顶部到底部的最小路径和，每一步都可以移动到下面一行相邻的数字，
//例如，给出的三角形如下：
//[↵     [2],↵    [3,4],↵   [6,5,7],↵  [4,1,8,3]↵]
//最小的从顶部到底部的路径和是2 + 3 + 5 + 1 = 11。
//注意：
//如果你能只用O（N）的额外的空间来完成这项工作的话，就可以得到附加分，其中N是三角形中的行总数。
//解析如下：
//// 给定一个三角形，找出从顶到底的最小路径和，每一步可以从上一行移动到下一行相邻的数字
////    [
////         [2],                 [2],
////        [3,4],              [3, 4],            [2],
////       [6,5,7],      ==>   [7, 6, 10]     ==>  [9, 10]   ==>     [11]
////      [4,1,8,3]
////    ]
@Slf4j(topic = "Triangle")
public class Triangle {
    public static void main(String[] args) {
        Integer[][] arr = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        List<List<Integer>> res = new ArrayList<>();
        solve(arr, 0, 0, new ArrayList<>(), res);
        log.info("{}", res);
        int min = res.stream().mapToInt(i -> i.stream().mapToInt(j -> j).sum()).min().getAsInt();
        log.info("{}", min);

        // 解法二，每次循环消除掉最后一层，如上图所示
        List<List<Integer>> list = new ArrayList<>();
        for (Integer[] item : arr) {
            list.add(Arrays.asList(item));
        }
        solve(list);

    }

    private static void solve(List<List<Integer>> list) {
        if (list.size() == 1) {
            log.info("{}", list.get(0).get(0));
            return;
        }
        // 把最后一层移除掉
        List<Integer> remove = list.remove(list.size() - 1);
        // 替换新的最后一层的数字
        List<Integer> last = list.get(list.size() - 1);
        for (int i = 0; i < last.size(); i++) {
            last.set(i, last.get(i) + Math.min(remove.get(i) , remove.get(i + 1)));
        }
        solve(list);
    }

    // 类比于二叉树，求到底部元素的所有路径
    private static void solve(Integer[][] arr, int i, int j, ArrayList<Integer> item, List<List<Integer>> res) {
        if (i == arr.length) {
            res.add(new ArrayList<>(item));
            return;
        }
        item.add(arr[i][j]);
        solve(arr, i + 1, j, item, res);
        // 注意，如果没有这个if，会导致结果重复
        if (i != arr.length - 1) {
            solve(arr, i + 1, j + 1, item, res);
        }
        item.remove(item.size() - 1);
    }

}
