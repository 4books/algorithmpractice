package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Maximum_Depth_of_Binary_Tree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Solution {

        public int maxDepth(TreeNode root) {

            if (root == null) {
                return 0;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            int answer = 0;
            while (!q.isEmpty()) {
                answer++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (node.left != null) {
                        q.offer(node.left);
                    }
                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }
            }

            return answer;
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);

        root.left = new TreeNode(2);
        root.right = new TreeNode(4);

        root.left.left = new TreeNode(1);
        root.left.right = null;
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(-1);

        root.left.left.left = new TreeNode(5);
        root.left.left.right = new TreeNode(1);
        root.right.left.left = null;
        root.right.left.right = new TreeNode(6);
        root.right.right.left = null;
        root.right.right.right = new TreeNode(8);


        Solution s = new Solution();
        int answer = s.maxDepth(root);
        System.out.println("answer = " + answer);
    }
}
