package leetcode.dynamicprogramming.longest_increasing_subsequence;
/*
https://leetcode.com/problems/longest-increasing-subsequence/
 */

import java.util.ArrayList;

class Solution {
    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if(list.size() == 0 || num > list.get(list.size() - 1)){
                list.add(num);
            } else {
                int left = 0;
                int right = list.size() - 1;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) < num) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                list.set(right, num);
            }
        }

        return list.size();
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        int answer, expect;
//        answer = s.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
//        System.out.println("answer = " + answer);
//        answer = s.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
//        System.out.println("answer = " + answer);
//        answer = s.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7});
//        System.out.println("answer = " + answer);
//        answer = s.lengthOfLIS(new int[]{1, 2, 3});
//        System.out.println("answer = " + answer);
        answer = s.lengthOfLIS(new int[]{3, 1, 2});
        System.out.println("answer = " + answer);


//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
