package programers.level2.p118667;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/118667?language=java
 */


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.Queue;


class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        //queue 초기화
        long sum1 = 0, sum2 = 0;
        for (int i : queue1) {
            q1.offer(i);
            sum1 += i;
        }
        for (int i : queue2) {
            q2.offer(i);
            sum2 += i;
        }

        int count = 0;
        while (sum1 != sum2) {
            if (sum1 > sum2 && !q1.isEmpty()) {
                Integer poll = q1.poll();
                sum1 -= poll;

                q2.add(poll);
                sum2 += poll;
                count++;
            }
            if (sum1 < sum2 && !q2.isEmpty()) {
                Integer poll = q2.poll();
                sum2 -= poll;

                q1.add(poll);
                sum1 += poll;
                count++;
            }

            //종료 조건은 어떻게?
            if (count > 300_000) {
                break;
            }
        }
        if (count > 300_000) {
            return -1;
        }
        return count;
    }
}

public class Main {

    Solution s = new Solution();

    @Test
    void testCase() {
        assertThat(s.solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1})).isEqualTo(2);
        assertThat(s.solution(new int[]{1, 2, 1, 2}, new int[]{1, 10, 1, 2})).isEqualTo(7);
        assertThat(s.solution(new int[]{1, 1}, new int[]{1, 5})).isEqualTo(-1);
    }


}
