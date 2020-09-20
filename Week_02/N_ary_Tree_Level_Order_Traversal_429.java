package Week02;

import java.util.ArrayList;
import java.util.List;

public class N_ary_Tree_Level_Order_Traversal_429 {
    class Node {
        int val;
        List<Node> children;
        Node(int val) {
            this.val = val;
            this.children = null;
        }
    }
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return lists;
        }
        helper(root, 0);
        return lists;
    }
    private void helper(Node root, int level) {
        if (lists.size() == level) {
            lists.add(new ArrayList<Integer>());
        }
        lists.get(level).add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                helper(child, level + 1);
            }
        }
    }
}
