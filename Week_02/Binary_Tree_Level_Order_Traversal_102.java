package Week02;

import java.util.ArrayList;
import java.util.List;

// 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
// 示例：
// 二叉树：[3,9,20,null,null,15,7],
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回其层次遍历结果：
// [
//  [3],
//  [9,20],
//  [15,7]
// ]

public class Binary_Tree_Level_Order_Traversal_102 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    // 我们用list of list储存节点
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return lists;
        }
        helper(root, 0);
        return lists;
    }
    private void helper(TreeNode root, int level ) {
        // 把根节点当作第0层，从当前的level层开始，创建一个当前层的数组并放入二维数组lists中
        if (lists.size() == level) {
            lists.add(new ArrayList<Integer>());
        }
        // 将当前层的节点添加到对应的level数组中
        lists.get(level).add(root.val);
        // 递归处理下一层的孩子节点，层次+1
        if (root.left != null) {
            helper(root.left, level+1);
        }
        if (root.right != null) {
            helper(root.right, level+1);
        }
    }
}
