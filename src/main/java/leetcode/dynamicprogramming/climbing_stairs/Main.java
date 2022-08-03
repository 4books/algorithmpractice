package leetcode.dynamicprogramming.climbing_stairs;
/*
https://leetcode.com/problems/climbing-stairs/
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int climbStairs(int n) {

        int[] steps = new int[Math.max(n + 1, 3)];
        steps[0] = 0;
        steps[1] = 1;
        steps[2] = 2; //1+1, 2

        for (int i = 3; i <= n; i++) {
            steps[i] = steps[i - 1] + steps[i - 2];
        }

        return steps[n];
    }

}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        int i = s.climbStairs(2);
        int i1 = s.climbStairs(3);
        int i2 = s.climbStairs(4);

        System.out.println();

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
