package programers.level2.p12951;
/*

 */

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String[] letter = s.toLowerCase().split("");

        boolean nextFlag = true;
        for (int i = 0; i < letter.length; i++) {
            if(nextFlag){
                answer.append(letter[i].toUpperCase());
            } else {
                answer.append(letter[i]);
            }
            nextFlag = letter[i].equals(" ");
        }

        return answer.toString();
    }

}

public class Main {

    Solution s = new Solution();

    @Test
    void test(){
        assertThat(s.solution("3people unFollowed me")).isEqualTo("3people Unfollowed Me");
        assertThat(s.solution("for the last week")).isEqualTo("For The Last Week");
    }

}
