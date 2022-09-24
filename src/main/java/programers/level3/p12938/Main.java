package programers.level3.p12938;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/12938


 */

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;


class Solution {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{-1};
        }

        int[] answer = new int[n];
        int t = s / n; //중간값
        int sum = s - (t * n);
        for (int i = 0; i < n; i++) {
            if (i >= n - sum)
                answer[i] = t + 1;
            else
                answer[i] = t;
        }
        return answer;
    }

}

public class Main {

    Solution s = new Solution();

    @Test
    void test() {
        assertThat(s.solution(2, 9)).isEqualTo(new int[]{4, 5});
        assertThat(s.solution(2, 1)).isEqualTo(new int[]{-1});
        assertThat(s.solution(2, 8)).isEqualTo(new int[]{4, 4});
    }

}
