import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int v) {
        val = v;
        left = right = null;
    }
}

public class Main {

    // Maps value -> node
    static Map<Integer, TreeNode> nodeMap = new HashMap<>();
    // Maps value -> parent
    static Map<Integer, TreeNode> parentMap = new HashMap<>();

    // Build maps for fast access
    static void buildMaps(TreeNode root, TreeNode parent) {
        if (root == null) return;

        nodeMap.put(root.val, root);
        parentMap.put(root.val, parent);

        buildMaps(root.left, root);
        buildMaps(root.right, root);
    }

    // Compute right view using level order traversal
    static List<Integer> rightView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode last = null;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                last = curr;

                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }

            // last node of this level = right view
            res.add(last.val);
        }

        return res;
    }

    // Remove subtree rooted at val, return info needed to restore
    static TreeNode[] removeSubtree(int val) {
        TreeNode node = nodeMap.get(val);
        TreeNode parent = parentMap.get(val);

        if (node == null || parent == null) {
            // Removing root or invalid
            return new TreeNode[]{null, null, null};
        }

        // Check if it's left or right child
        if (parent.left == node) {
            parent.left = null;
            return new TreeNode[]{parent, node, new TreeNode(0)}; // marker left
        } else {
            parent.right = null;
            return new TreeNode[]{parent, node, new TreeNode(1)}; // marker right
        }
    }

    // Restore subtree
    static void restoreSubtree(TreeNode[] info) {
        if (info[0] == null) return;

        TreeNode parent = info[0];
        TreeNode node = info[1];
        int side = info[2].val; // 0 = left, 1 = right

        if (side == 0) parent.left = node;
        else parent.right = node;
    }

    // Main function for queries
    static List<List<Integer>> processQueries(TreeNode root, int[] queries) {
        List<List<Integer>> answer = new ArrayList<>();

        for (int q : queries) {
            TreeNode[] info = removeSubtree(q);

            List<Integer> view = rightView(root);
            answer.add(view);

            restoreSubtree(info);
        }

        return answer;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        // Build maps
        buildMaps(root, null);

        int[] queries = {5, 3, 2};

        List<List<Integer>> ans = processQueries(root, queries);

        for (int i = 0; i < ans.size(); i++) {
            System.out.println("After removing subtree rooted at " + queries[i] + ": " + ans.get(i));
        }
    }
}
