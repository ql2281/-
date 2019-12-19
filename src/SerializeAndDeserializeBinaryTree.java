import org.junit.Test;
import java.util.*;

class TreeNode {
    String val;
    TreeNode left;
    TreeNode right;
    public TreeNode(String val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public Map<Integer, String> serializeBinaryTree(TreeNode root) {
        // use a treemap to store the treenode index and value.
        // so in the deserialize binary tree, I can use this treemap as the input to construct the tree.
        // use BFS to do level order traversal of the tree, and record the index and value of each treenode,
        // at the same time, print out the index of current treenode and the val stored in the treenode.
        // if the node is null, i will print at this index, the node is null.
        if (root == null) {
            System.out.println("The input root is null");
            return new TreeMap<>();
        }
        Map<Integer, String> nodeMap = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int idx = -1;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            idx++;
            if (temp == null) {
                nodeMap.put(idx, null);
                System.out.println("At " + idx + " the Node value is null");

            } else {
                nodeMap.put(idx, temp.val);
                // print out the index of current treenode and the val stored in the treenode.
                System.out.println("At " + idx + " the Node value is " + temp.val);
                queue.offer(temp.left);
                queue.offer(temp.right);
            }
        }
        return nodeMap;
    }

    // Decodes encoded data to tree.
    public TreeNode deserializeBinaryTree(Map<Integer, String> map) {
        // use the treemap to construct the tree.
        // use BFS, and get treenode by reading the index in the map.
        // at each parent node, get the left and right children, and link them to the parent node.
        // if the node at this index is null, just skip it.
        if (map.isEmpty()) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        int i = 0;
        TreeNode root = new TreeNode(map.get(i));
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            i++;
            TreeNode left = map.get(i) == null ? null : new TreeNode(map.get(i));
            i++;
            TreeNode right = map.get(i) == null ? null : new TreeNode(map.get(i));
            curr.left = left;
            curr.right = right;
            if (left != null) queue.offer(left);
            if (right != null) queue.offer(right);
        }
        return root;
    }


    @Test
    public void test() {
        TreeNode root = new TreeNode("1");
        root.left = new TreeNode("2");
        root.right = new TreeNode("3");
        root.left.left = new TreeNode("4");
        root.left.right = null;
        root.right.left = new TreeNode("6");

        Map<Integer, String> serializedTree = serializeBinaryTree(root);

        TreeNode res = deserializeBinaryTree(serializedTree);
        System.out.println(res.val);
    }
}
