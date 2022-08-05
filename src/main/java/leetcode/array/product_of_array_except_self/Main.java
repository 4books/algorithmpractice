package leetcode.array.product_of_array_except_self;
/*
https://leetcode.com/problems/product-of-array-except-self/
 */

import java.util.Arrays;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int zeroCount = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                if(zeroCount > 1){
                    break;
                }
                continue;
            }
            product *= nums[i];
        }

        int[] answer = new int[nums.length];
        if (zeroCount > 1) {
            return answer;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && zeroCount == 1) {
                //하나의 0 이 있을 경우
                answer[i] = 0;
            } else if (nums[i] != 0) {
                //0이 없고 0이 아닐때
                answer[i] = product / nums[i];
            } else {
                //해당 숫자가 0 이라면
                answer[i] = product;
            }
        }
        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        s.productExceptSelf(new int[]{1, 2, 3, 4});
        s.productExceptSelf(new int[]{-1, 1, 0, -3, 3});
        s.productExceptSelf(new int[]{0,0});
        s.productExceptSelf(new int[]{1,0,0,1});

        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
