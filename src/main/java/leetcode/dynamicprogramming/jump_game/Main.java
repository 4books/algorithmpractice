package leetcode.dynamicprogramming.jump_game;
/*
https://leetcode.com/problems/jump-game/
 */

class Solution {
    public boolean canJump(int[] nums) {
        int N = nums.length;

        int pos = 0;
        for (int i = 0; i < N; i++) {

            //해당 index 의 점프
            int jump = nums[i];
            //해당 index + jump 거리 중 더 큰 것을 찾음
            //즉 최대로 점프 했을때의 위치
            pos = Math.max(pos, jump + i);

            if(pos >= N - 1){
                //pos 가 도착지점에 왔을 경우
                return true;

            } else if(pos == i){
                //pos 가 더 이상 앞으로 나가지 못하는 경우
                return false;
            }
        }
        return true;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
