package Week02;
// 给定一个二叉树，返回它的 前序 遍历。
//
// 示例:
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
// 输出: [1,2,3]

import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Preorder_Traversal_144 {
    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    private void preOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preOrder(root.left, list);
            preOrder(root.right, list);
        }
    }

}
