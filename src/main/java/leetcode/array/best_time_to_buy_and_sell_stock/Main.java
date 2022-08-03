package leetcode.array.best_time_to_buy_and_sell_stock;
/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */

class Solution {
    public int maxProfit(int[] prices) {

        int min = Integer.MAX_VALUE;
        int answer = 0;

        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);

            if (prices[i] > min) {
                answer = Math.max(answer, prices[i] - min);
            }
        }

        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        s.maxProfit(new int[]{7, 1, 5, 3, 6, 4});

        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
