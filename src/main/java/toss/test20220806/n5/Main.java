package toss.test20220806.n5;
/*

 */

import java.util.*;

class Solution {
    public int solution(int[] tasks) {
        int answer = 0;
        Arrays.sort(tasks);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < tasks.length; i++) {
            set.add(tasks[i]);
        }

        int start = 0;
        int count = 0;
        int total = 0;

        while (start < tasks.length && total < set.size()) {
            int curr = tasks[start];

            for (int i = start; i < tasks.length; i++) {
                if (tasks[i] != curr) {
                    start = i;
                    break;
                }
                count++;
            }

            if (count >= 3) {
                //3의 배수
                answer += count / 3;
                count %= 3;
            }
            if (count >= 2) {
                //2의 배수
                answer += count / 2;
                count %= 2;
            }

            if (count > 0) {
                answer = -1;
                break;
            }

            count = 0;
            total++;
        }

        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;
        answer = s.solution(new int[]{1, 1, 2, 3, 3, 2, 2});
        System.out.println("answer = " + answer);
        answer = s.solution(new int[]{4, 1, 1, 1, 1, 2, 3});
        System.out.println("answer = " + answer);
        answer = s.solution(new int[]{1, 1, 2, 2});
        System.out.println("answer = " + answer);
        answer = s.solution(new int[]{1, 1, 1, 1, 1, 1, 1});
        System.out.println("answer = " + answer);
//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
