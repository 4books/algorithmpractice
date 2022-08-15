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

                for (int i = 0; i < q.size(); i++) {
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
        TreeNode right = new TreeNode(2);
        TreeNode root = new TreeNode(1, null, right);
        Solution s = new Solution();
        int answer = s.maxDepth(root);
        System.out.println("answer = " + answer);
    }
}
