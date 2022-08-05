package leetcode.array.contains_duplicate;
/*
https://leetcode.com/problems/contains-duplicate/
 */

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if(!set.add(num)){
                break;
            }
        }

        return nums.length != set.size();
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        boolean b = s.containsDuplicate(new int[]{1, 2, 3, 1});
        System.out.println("b = " + b);

        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
