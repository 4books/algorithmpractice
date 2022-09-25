package programers.skillcheck.level2;
/*

 */

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution1 {
    public int solution(int n) {
        int answer = 0;
        while(n > 0){
            if(n % 2 != 0){//홀수
                n--;
                answer++;
            }
            n /= 2;
        }
        return answer;
    }
}

public class Q1 {

    Solution1 s = new Solution1();

    @Test
    void test(){
        assertThat(s.solution(5)).isEqualTo(2);
        assertThat(s.solution(6)).isEqualTo(2);
        assertThat(s.solution(5000)).isEqualTo(5);
    }

}
