package leetcode.array.maximun_subarray;
/*
Kadane's Algorithm
카데인 알고리즘
https://leetcode.com/problems/maximum-subarray/
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i] + sum, nums[i]);
            max = Math.max(max, sum);
        }

        return max;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        int max = s.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println("max = " + max);

        max = s.maxSubArray(new int[]{5, 4, -1, 7, 8});
        System.out.println("max = " + max);





        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
