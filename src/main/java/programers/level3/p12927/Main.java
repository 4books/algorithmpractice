package programers.level3.p12927;
/*

 */

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < works.length; i++) {
            q.offer(works[i]);
        }

        for (int i = 0; i < n; i++) {
            if (q.isEmpty()) {
                break;
            }
            int poll = q.poll() - 1;
            if (poll == 0) {
                continue;
            }
            q.offer(poll);
        }

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            answer += Math.pow(poll, 2);
        }

        return answer;
    }

}

public class Main {

    Solution s = new Solution();

    @Test
    void test() {
        assertThat(s.solution(4, new int[]{4, 3, 3})).isEqualTo(12);
        assertThat(s.solution(1, new int[]{2, 1, 2})).isEqualTo(6);
        assertThat(s.solution(3, new int[]{1, 1})).isEqualTo(0);
    }

}
