package algorithm;

import lombok.extern.slf4j.Slf4j;

//题目给出一个字符串s1，我们可以用递归的方法将字符串分成两个非空的子串来将s1表示成一个二叉树
//下面是s1=“great”的一种二叉树的表现形式：
//    great↵   /    ↵  gr    eat↵ /     /  ↵g   r  e   at↵           / ↵          a   t
//将字符串乱序的方法是：选择任意的非叶子节点，交换它的两个孩子节点。
//例如：如果我们选择节点“gr”交换他的两个孩子节点，就会产生一个乱序字符串"rgeat".
//     rgeat↵   /    ↵  rg    eat↵ /     /  ↵r   g  e   at↵           / ↵          a   t
//我们称"rgeat"是"great"的一个乱序字符串。
//类似的：如果我们继续交换“eat”的两个孩子节点和“at”的两个孩子节点，会产生乱序字符串"rgtae".
//     rgtae↵   /    ↵  rg    tae↵ /     /  ↵r   g  ta  e↵       / ↵      t   a
//我们称"rgtae"是"great"的一个乱序字符串。
//给出两个长度相同的字符串s1 和 s2，请判断s2是否是s1的乱序字符串。
@Slf4j(topic = "ScrambleString")
public class ScrambleString {
    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "taerg";

        solve(s1, s2, 0);
    }

    private static void solve(String s1, String s2, int temp) {

    }
}
