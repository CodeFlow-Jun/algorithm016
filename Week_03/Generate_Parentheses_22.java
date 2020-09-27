package Week03;
// 数字 n 代表生成括号的对数,
// 请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 示例：
// 输入：n = 3
// 输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//       ]

import java.util.ArrayList;
import java.util.List;

public class Generate_Parentheses_22 {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        helper(0, 0, n, "");
        return res;
    }
    private void helper(int left, int right, int n, String s) {
        if (left == n & right == n) {
            res.add(s);
            return ;
        }

        if (left < n) {
            helper(left + 1, right, n, s + "(");
        }
        if (left > right) {
            helper(left, right + 1, n, s + ")");
        }
    }
}
