package programers.level3.p92344;
/*
누적합 누적 합
https://jangcenter.tistory.com/121
https://school.programmers.co.kr/learn/courses/30/lessons/92344

너무 김 가서 볼것
 */


class Solution {

    static int N, M;
    static final int ATTACK = 1, HEEL = 2;

    public int solution(int[][] board, int[][] skill) {
        //스킬
        //0번 - type : 1 적 공격, 2 아군 회복
        //1~4번 r1, c1, r2, c2
        //5번 정도

        N = board.length;
        M = board[0].length;
        int[][] sum = new int[N + 1][M + 1];

        for (int n = 0; n < skill.length; n++) {
            int ty = skill[n][0];
            int r1 = skill[n][1];
            int c1 = skill[n][2];
            int r2 = skill[n][3];
            int c2 = skill[n][4];
            int dg = skill[n][5] * (ty == ATTACK ? -1 : 1);

            sum[r1][c1] += dg;
            sum[r1][c2 + 1] += (dg * -1);
            sum[r2 + 1][c1] += (dg * -1);
            sum[r2 + 1][c2 + 1] += dg;
        }

        //상하 누적합
        for (int r = 1; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sum[r][c] += sum[r - 1][c];
            }
        }

        //좌우 누적합
        for (int c = 1; c < M; c++) {
            for (int r = 0; r < N; r++) {
                sum[r][c] += sum[r][c - 1];
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + sum[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;

        answer = s.solution(new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
                new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}});
        expect = 10;
        System.out.println("result = " + answer + " " + "expect = " + expect);

        answer = s.solution(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                new int[][]{{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}});
        expect = 6;
        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
