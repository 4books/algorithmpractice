package programers.level3.p43238;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/43238

입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때, 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.

제한사항
입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
심사관은 1명 이상 100,000명 이하입니다.
입출력 예
n	times	return
6	[7, 10]	28
입출력 예 설명
가장 첫 두 사람은 바로 심사를 받으러 갑니다.

7분이 되었을 때, 첫 번째 심사대가 비고 3번째 사람이 심사를 받습니다.

10분이 되었을 때, 두 번째 심사대가 비고 4번째 사람이 심사를 받습니다.

14분이 되었을 때, 첫 번째 심사대가 비고 5번째 사람이 심사를 받습니다.

20분이 되었을 때, 두 번째 심사대가 비지만 6번째 사람이 그곳에서 심사를 받지 않고 1분을 더 기다린 후에 첫 번째 심사대에서 심사를 받으면 28분에 모든 사람의 심사가 끝납니다.

출처

※ 공지 - 2019년 9월 4일 문제에 새로운 테스트 케이스를 추가하였습니다. 도움을 주신 weaver9651 님께 감사드립니다.
 */

import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;

        //걸리는 시간 오름 차순
        Arrays.sort(times);

        long min = 1; //최소 심사 시간
        long max = (long) times[times.length - 1] * n;//최대(최악) 심사 시간

        while (min <= max) {
            //심사관들이 처리한 사람 수
            long sum = 0;

            //이분 탐색을 위해 중간 값을 찾는다.
            long mid = (min + max) / 2;

            //각 심사관들이 mid 시간 만큼 처리 가능한 사람 수
            for (int t : times) {
                sum += mid / t;
            }

            //n 보다 더 많이 처리할 수 있으면 정답 후보 및 더 적은 시간 탐색
            if (sum >= n) {
                max = mid - 1;
                answer = mid;
            } else { //더 많은 시간 필요
                min = mid + 1;
            }
        }

        return answer;
    }

}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int n = 6;
        int[] times = {7, 10};

        long answer = s.solution(n, times);
        int expect = 28;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
