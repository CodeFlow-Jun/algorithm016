package Week02;

import java.util.ArrayList;
import java.util.List;

public class N_ary_Tree_Postorder_Traversal_590 {
    class Node {
        int val;
        List<Node> children;
        Node(int val){
            this.val = val;
            this.children = null;
        }
    }
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }
    private void helper(Node root, List<Integer> list) {
        if (root != null) {
            for (Node child : root.children) {
                helper(child, list);
            }
            list.add(root.val);
        }
    }

}
