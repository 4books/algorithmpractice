package backjoon.platinum4.p9376;
/*
https://www.acmicpc.net/problem/9376

문제
상근이는 감옥에서 죄수 두 명을 탈옥시켜야 한다. 이 감옥은 1층짜리 건물이고, 상근이는 방금 평면도를 얻었다.

평면도에는 모든 벽과 문이 나타나있고, 탈옥시켜야 하는 죄수의 위치도 나타나 있다. 감옥은 무인 감옥으로 죄수 두 명이 감옥에 있는 유일한 사람이다.

문은 중앙 제어실에서만 열 수 있다. 상근이는 특별한 기술을 이용해 제어실을 통하지 않고 문을 열려고 한다. 하지만, 문을 열려면 시간이 매우 많이 걸린다. 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 개수를 구하는 프로그램을 작성하시오. 문을 한 번 열면 계속 열린 상태로 있는다.

입력
첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 수는 100개를 넘지 않는다.

첫째 줄에는 평면도의 높이 h와 너비 w가 주어진다. (2 ≤ h, w ≤ 100) 다음 h개 줄에는 감옥의 평면도 정보가 주어지며, 빈 공간은 '.', 지나갈 수 없는 벽은 '*', 문은 '#', 죄수의 위치는 '$'이다.

상근이는 감옥 밖을 자유롭게 이동할 수 있고, 평면도에 표시된 죄수의 수는 항상 두 명이다. 각 죄수와 감옥의 바깥을 연결하는 경로가 항상 존재하는 경우만 입력으로 주어진다.

출력
각 테스트 케이스마다 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 최솟값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dR = {-1, 1, 0, 0};//상 하 좌 우
    static final int[] dC = {0, 0, -1, 1};
    static int total, R, C;
    static char[][] jail;
    static char WALL = '*', DOOR = '#', EMPTY = '.', PRISONER = '$';
    static int[][] dp;
    static boolean[][] visited;


    static class Point implements Comparable<Point> {
        int row, col, cnt;

        public Point(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Point[] prisoners = new Point[3];

        total = Integer.parseInt(br.readLine());
        for (int test = 0; test < total; test++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            jail = new char[R + 2][C + 2];

            //데이터 받기
            int priCount = 0;
            for (int i = 0; i < R; i++) {
                String temp = br.readLine();
                for (int j = 0; j < C; j++) {
                    jail[i + 1][j + 1] = temp.charAt(j);
                    if (jail[i + 1][j + 1] == PRISONER) {
                        prisoners[priCount++] = new Point(i + 1, j + 1, 0);
                    }
                }
            }
            prisoners[priCount] = new Point(0, 0, 0);

            //3가지 방법
            //1. 죄수1 => 죄수2 만나고 나옴
            //2. 1번 반대
            //3. 외부에서 둘다 데리고 옴

            int[][] first = bfs(prisoners[0]);
            int[][] second = bfs(prisoners[1]);
            int[][] third = bfs(prisoners[2]);

            sb.append(getMinSum(first, second, third, jail)).append("\n");

        } //테스트 케이스 끝

        System.out.println(sb);


    } //END of Main Method

    private static int[][] bfs(Point point) {

        //문 연 횟수가 적은 것부터 탐색 해야함
        PriorityQueue<Point> pq = new PriorityQueue<>();
        visited = new boolean[R + 2][C + 2];
        dp = new int[R + 2][C + 2];

        pq.offer(point);
        visited[point.row][point.col] = true;

        while (!pq.isEmpty()) {
            Point tmp = pq.poll();
            dp[tmp.row][tmp.col] = tmp.cnt;

            for (int d = 0; d < 4; d++) {
                int nRow = tmp.row + dR[d];
                int nCol = tmp.col + dC[d];

                if (isOutRange(nRow, nCol) || visited[nRow][nCol] || jail[nRow][nCol] == WALL) {
                    continue;
                }
                visited[nRow][nCol] = true;
                int nextCnt = jail[nRow][nCol] == DOOR ? tmp.cnt + 1 : tmp.cnt;
                pq.offer(new Point(nRow, nCol, nextCnt));
            }
        }
        return dp;
    }

    private static int getMinSum(int[][] first, int[][] second, int[][] third, char[][] jail) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < R + 2; i++) {
            for (int j = 0; j < C + 2; j++) {
                if (jail[i][j] == WALL) {
                    continue;
                }
                if(first[i][j] != -1 && second[i][j] != -1 && third[i][j] != -1){
                    int sum = first[i][j] + second[i][j] + third[i][j];
                    if (jail[i][j] == DOOR) {
                        //한사람만 열면 됨
                        sum -= 2;
                    }
                    min = Math.min(min, sum);
                }
            }
        }
        return min;
    }

    private static boolean isOutRange(int row, int col) {
        return row < 0 || row >= R + 2 || col < 0 || col >= C + 2;
    }

}


