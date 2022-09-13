package programers.level1.p87389;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/87389
 */

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void test(){
        Assertions.assertThat(s.solution(10)).isEqualTo(3);
        Assertions.assertThat(s.solution(12)).isEqualTo(11);
    }

}
