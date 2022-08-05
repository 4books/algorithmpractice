package leetcode.array.maximun_product_subarray;
/*
https://leetcode.com/problems/maximum-product-subarray/
 */

class Solution {
    public int maxProduct(int[] nums) {

        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int now = nums[i];
            int a = max * now;
            int b = min * now;
            max = Math.max(now, Math.max(a, b));
            min = Math.min(now, Math.min(a, b));
            ans = Math.max(ans, max);
        }

        return ans;
    }

}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        int answer, expect;
        answer = s.maxProduct(new int[]{2, 3, -2, 4});
        System.out.println("answer = " + answer);
        answer = s.maxProduct(new int[]{-2, 0, -1});
        System.out.println("answer = " + answer);
        answer = s.maxProduct(new int[]{-2, 3, -4});
        System.out.println("answer = " + answer);


//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
