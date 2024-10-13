package programers.level1.p87389;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/87389
 */


class Solution {
    public int solution(int n) {
        int answer = 0;
        while (true) {
            answer++;
            if(n % answer == 1){
                break;
            }
        }

        return answer;
    }

}

public class Main {

    Solution s = new Solution();


}
