package toss.test20220806.n4;
/*

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public long[] solution(long[][] invitationPairs) {
        //직접 초대한 사람 X 10 + 내가 초대한 사람이 초대한 사람 X 3 + 초대한 사람이 초대한 사람이 초대한 사람
        System.out.println();
        return null;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;
//        s.solution(new long[][]{{1, 2}, {3, 4}});
        s.solution(new long[][]{{1, 2}, {2, 3}, {2, 4}, {2, 5}, {5, 6}, {5, 7}, {6, 8}, {2, 9}});
//        s.solution(new long[][]{{1, 2}, {1, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 6}});

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
/*
테스트 1
입력값 〉
[[1, 2], [3, 4]]
기댓값 〉
[1, 3]
실행 결과 〉
실행한 결괏값 undefined가 기댓값 [1,3]과 다릅니다.
출력 〉
b = 2
b = 4
테스트 2
입력값 〉
[[1, 2], [1, 3], [3, 4], [4, 5], [4, 6], [4, 6]]
기댓값 〉
[4, 1, 3]
실행 결과 〉
실행한 결괏값 undefined가 기댓값 [4,1,3]과 다릅니다.
출력 〉
b = 2
b = 3
테스트 3
입력값 〉
[[1, 2], [2, 3], [2, 4], [2, 5], [5, 6], [5, 7], [6, 8], [2, 9]]
기댓값 〉
[2, 1, 5]
실행 결과 〉
실행한 결괏값 undefined가 기댓값 [2,1,5]과 다릅니다.
출력 〉
b = 2
b = 3
 */
    }
}
