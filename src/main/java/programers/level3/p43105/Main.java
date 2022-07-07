package programers.level3.p43105;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/43105

문제 설명
스크린샷 2018-09-14 오후 5.44.19.png

위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.

삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.

제한사항
삼각형의 높이는 1 이상 500 이하입니다.
삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
입출력 예
triangle	result
[[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]	30
출처
 */

class Solution {
    public int solution(int[][] triangle) {

        //최초 시작은 1, 0
        for (int i = 1; i < triangle.length; i++) {
            //첫번째 수
            triangle[i][0] += triangle[i - 1][0];
            //마지막 수
            triangle[i][i] += triangle[i - 1][i - 1];
            //중간수
            for (int j = 1; j < i; j++) {
                //상위 두개 중 더 큰 값
                int max = Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                triangle[i][j] += max;
            }
        }

        int answer = 0;
        int[] result = triangle[triangle.length - 1];
        for (int i = 0; i < result.length; i++) {
            answer = Math.max(answer, result[i]);
        }
        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        int answer = s.solution(triangle);
        int expect = 30;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
