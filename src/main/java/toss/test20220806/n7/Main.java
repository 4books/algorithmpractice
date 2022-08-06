package toss.test20220806.n7;
/*

 */

class Solution {
    public long solution(int money, long[][] stocks) {
        long answer = -1;
        //이 배열의 index 는 곧 사용한 돈
        //즉 dp[1] 은 돈을 1 사용했을때 최고의 가치이다
        long[] dp = new long[money + 1];

        for (int i = 0; i < stocks.length; i++) {
            long value = stocks[i][0];
            long price = stocks[i][1];

            //냅색 알고리즘
            for (int M = money; M >= price; M--) {
                //M원을 썼을때 가치를 비교
                //현재 돈에서 주식을 샀을 때 가치를 비교
                long preValue = dp[(int) (M - price)];
                dp[M] = Math.max(dp[M], preValue + value);
            }
        }

        for (int i = 1; i < dp.length; i++) {
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        long answer, expect;
        answer = s.solution(10, new long[][]{{1, 1}, {3, 5}, {3, 5}, {4, 9}});
        System.out.println("answer = " + answer);
        answer = s.solution(30, new long[][]{{1, 31}});
        System.out.println("answer = " + answer);

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
