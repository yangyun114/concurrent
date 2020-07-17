package algorithm;

import lombok.extern.slf4j.Slf4j;

//现在有一个包含n个物体的数组，其中物体颜色为颜色为红色、白色或蓝色，请对这个数组进行排序，让相同颜色的物体相邻，颜色的顺序为红色，白色，蓝色。
//我们用0,1,2分别代表颜色红，白，蓝
//注意：
//本题要求你不能使用排序库函数
//扩展：
//一个非常直接的解法是两步的计数排序的算法
//首先：遍历一遍数组，记录0,1,2的数量，然后重写这个数组，先将0写入，再将1写入，再将2写入
//你能给出一个只用一步，并且能在常数级空间复杂度解决这个问题的算法吗？
@Slf4j(topic = "SortColors")
public class SortColors {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 0, 1, 2};
        solve(arr);
    }

    private static void solve(int[] arr) {
//        int i = -1;
//        int j = arrSize;
//        int k = 1;
//        while (k < j) {
//            if (list.getElement(k) == 0) {
//                i++;
//                int temp = list.getElement(k);
//                list.setElement(k, list.getElement(i));
//                list.setElement(i, temp);
//                k++;
//            }
//            if (list.getElement(k) == 1) {
//                k++;
//            }
//            if (list.getElement(k) == 2) {
//                j--;
//                int temp = list.getElement(k);
//                list.setElement(k, list.getElement(j));
//                list.setElement(j, temp);
//            }
//        }
//        return list;

        int start = 0, end = arr.length - 1;
        // i注意要小于等于end
        for (int i = 0; i <= end; i++) {
            if (arr[i] < 1) {
                int temp = arr[i];
                arr[i] = arr[start];
                arr[start] = temp;
                start++;
            } else if (arr[i] > 1) {
                int temp = arr[i];
                arr[i] = arr[end];
                arr[end] = temp;
                end--;
                // 此时end位置的值换过来还没有与1进行比较，所以i不能前进
                i = i - 1;
            }
        }

    }
}
