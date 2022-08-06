package toss.test20220806.n2;
/*

 */

class Solution {
    public int solution(int[] levels) {
        double tmp = (double) levels.length * 3 / 4;
        int number = (int) Math.round(tmp);

        if(number >= levels.length){
            return -1;
        }
        return levels[number];
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        int answer, expect;
        answer = s.solution(new int[]{1});
        System.out.println("answer = " + answer);
        answer = s.solution(new int[]{1,2,3,4});
        System.out.println("answer = " + answer);
        answer = s.solution(new int[]{1,2,3,4,5});
        System.out.println("answer = " + answer);
        answer = s.solution(new int[]{1,2,3,4,5,6,7,8,9});
        System.out.println("answer = " + answer);

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
