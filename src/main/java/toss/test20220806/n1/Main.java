package toss.test20220806.n1;
/*

 */

class Solution {
    public int solution(String s) {

        int repeat = 1;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < s.length(); i++) {
            char t = s.charAt(i - 1);
            char c = s.charAt(i);

            if (t == c) {
                repeat += 1;
            } else {
                repeat = 1;
            }

            if(repeat == 3){
                repeat = 1;
                int tmp = c - '0';

                int num = tmp * 100 + tmp * 10 + tmp;
                max = Math.max(max, num);
            }

        }

        if(max == Integer.MIN_VALUE){
            max = -1;
        }

        return max;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        int answer, expect;

        answer = s.solution("123");
        System.out.println("answer = " + answer);


//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
