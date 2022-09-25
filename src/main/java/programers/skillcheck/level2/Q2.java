package programers.skillcheck.level2;
/*

 */

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;


class Solution2 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i = 0; i < answer.length; i++) {
            for(int j = i + 1; j < answer.length; j++) {
                answer[i]++;
                if(prices[i] > prices[j]) {
                    break;
                }
            }
        }

        return answer;
    }
}

public class Q2 {

    Solution2 s = new Solution2();

    @Test
    void test(){
        assertThat(s.solution(new int[]{1, 2, 3, 2, 3}))
                .isEqualTo(new int[]{4, 3, 1, 1, 0});
    }

}
