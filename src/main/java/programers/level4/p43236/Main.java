package programers.level4.p43236;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/43236

문제 설명
출발지점부터 distance만큼 떨어진 곳에 도착지점이 있습니다. 그리고 그사이에는 바위들이 놓여있습니다. 바위 중 몇 개를 제거하려고 합니다.
예를 들어, 도착지점이 25만큼 떨어져 있고, 바위가 [2, 14, 11, 21, 17] 지점에 놓여있을 때 바위 2개를 제거하면 출발지점, 도착지점, 바위 간의 거리가 아래와 같습니다.

제거한 바위의 위치	각 바위 사이의 거리	거리의 최솟값
[21, 17]	[2, 9, 3, 11]	2
[2, 21]	[11, 3, 3, 8]	3
[2, 11]	[14, 3, 4, 4]	3
[11, 21]	[2, 12, 3, 8]	2
[2, 14]	[11, 6, 4, 4]	4
위에서 구한 거리의 최솟값 중에 가장 큰 값은 4입니다.

출발지점부터 도착지점까지의 거리 distance, 바위들이 있는 위치를 담은 배열 rocks, 제거할 바위의 수 n이 매개변수로 주어질 때, 바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값 중에 가장 큰 값을 return 하도록 solution 함수를 작성해주세요.

제한사항
도착지점까지의 거리 distance는 1 이상 1,000,000,000 이하입니다.
바위는 1개 이상 50,000개 이하가 있습니다.
n 은 1 이상 바위의 개수 이하입니다.
입출력 예
distance	rocks	n	return
25	[2, 14, 11, 21, 17]	2	4
입출력 예 설명
문제에 나온 예와 같습니다.

출처

※ 공지 - 2020년 2월 17일 테스트케이스가 추가되었습니다.
 */

import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);

        int min = 1; //최소 거리
        int max = distance; //최대 거리

        //중간값을 순회 하면서 최소 거리 중 최대 거리를 찾는다.
        while (min <= max) {
            int removeCount = 0; //제거한 돌의 갯수
            int prev = 0; //거리 측정을 위한 지점 설정 처음은 0
            int mid = (min + max) / 2; //거리 중간값

            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid) {
                    //현재 mid 보다 작은 값이라면 돌 삭제
                    //이미 n을 넘겼으면 의미 없음. break
                    if (++removeCount > n) {
                        break;
                    }
                } else {
                    //mid 와 거리가 같거나 크다면 삭제하지 않고 해당 돌을 지점으로 설정
                    prev = rocks[i];
                }
            }

            //마지막 도착지점과의 거리 측정
            if (distance - rocks[rocks.length - 1] < mid) {
                removeCount++;
            }

            //n 개보다 같거나 적은 돌을 제거 했으면 정답 후보 및 더 큰 값을 탐색
            if (removeCount <= n) {
                min = mid + 1;
                answer = Math.max(mid, answer);
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;

        int answer = s.solution(distance, rocks, n);
        int expect = 4;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
