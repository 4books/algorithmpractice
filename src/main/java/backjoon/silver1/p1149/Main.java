package backjoon.silver1.p1149;
/*
https://www.acmicpc.net/problem/1149

문제
RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.

집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.

1번 집의 색은 2번 집의 색과 같지 않아야 한다.
N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
입력
첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int RED = 0, GREEN = 1, BLUE = 2;
    static int[][] dp, house;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        house = new int[num][];

        StringTokenizer st = null;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());

            house[i] = new int[3];
            house[i][RED] = Integer.parseInt(st.nextToken());
            house[i][GREEN] = Integer.parseInt(st.nextToken());
            house[i][BLUE] = Integer.parseInt(st.nextToken());
        }

        dp = new int[num][3];
        dp[0][RED] = house[0][RED];
        dp[0][GREEN] = house[0][GREEN];
        dp[0][BLUE] = house[0][BLUE];

        //집 색칠 비용 최소값 누개
        int min = Math.min(getCost(num - 1, RED), Math.min(getCost(num - 1, GREEN), getCost(num - 1, BLUE)));
        System.out.println(min);


    } //END of Main Method

    static int getCost(int index, int color) {
        int nextIndex = index - 1;
        //탐색하지 않은 dp 일 경우
        if (dp[index][color] == 0) {
            if (color == RED) {
                dp[index][RED] = Math.min(getCost(nextIndex, GREEN), getCost(nextIndex, BLUE)) + house[index][RED];
            } else if (color == GREEN) {
                dp[index][GREEN] = Math.min(getCost(nextIndex, RED), getCost(nextIndex, BLUE)) + house[index][GREEN];
            } else {
                dp[index][BLUE] = Math.min(getCost(nextIndex, GREEN), getCost(nextIndex, RED)) + house[index][BLUE];
            }
        }
        return dp[index][color];
    }

}


