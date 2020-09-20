package Week02;
// 给定一个 N 叉树，返回其节点值的前序遍历。
// 返回一个数组[1,3,5,6,2,4]
import java.util.ArrayList;
import java.util.List;

public class N_ary_Tree_Preorder_Traversal_589 {
    public class Node {
        int val;
        List<Node> children;
        Node(int val) {
            this.val = val;
            this.children = null;
        }
    }
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }
    private void helper(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            for (Node child : root.children) {
                helper(child, list);
            }
        }
    }

}
