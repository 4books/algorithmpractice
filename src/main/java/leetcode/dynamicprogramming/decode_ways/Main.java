package leetcode.dynamicprogramming.decode_ways;
/*
https://leetcode.com/problems/decode-ways/
 */

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int[] dp = new int[s.length() + 1];
        //한개씩 분리해서 디코딩
        dp[0] = 1;
        //두개씩 분리해서 디코딩
        dp[1] = 1;

        for (int i = 2; i <= s.length(); i++) {
            //십의 자리 체크
            //06은 6이 아니다
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            //숫자 체크
            int value = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            if (value >= 10 && value <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }
}

public class Main {

    Solution s = new Solution();

    @Test
    void testcase() {
        Assertions.assertThat(s.numDecodings("12")).isEqualTo(2);
        Assertions.assertThat(s.numDecodings("226")).isEqualTo(3);
        Assertions.assertThat(s.numDecodings("06")).isEqualTo(0);
    }
}
