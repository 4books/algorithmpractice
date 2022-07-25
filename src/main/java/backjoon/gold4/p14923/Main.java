package backjoon.gold4.p14923;
/*
https://www.acmicpc.net/problem/14923

홍익이는 사악한 마법사의 꾐에 속아 N x M 미로 (Hx, Hy) 위치에 떨어졌다. 다행히도 홍익이는 마법사가 만든 미로의 탈출 위치(Ex, Ey)를 알고 있다. 하지만 미로에는 곳곳에 마법사가 설치한 벽이 있어 홍익이가 탈출하기 어렵게 하고 있다.

홍익이는 마법사의 연구실에서 훔친 지팡이가 있어, 벽을 길로 만들 수 있다. 그렇지만, 안타깝게도 마법의 지팡이는 단 한 번만 사용할 수 있다.

이때, 홍익이를 도와 미로에서 탈출할 수 있는지 알아보고, 할 수 있다면 가장 빠른 경로의 거리 D는 얼마인지 알아보자.

인접한 칸으로 이동하는데 똑같은 시간이 들고, 벽을 부수는 데 시간이 걸리지 않는다.

입력
N M
Hx Hy
Ex Ey
N X M 행렬
2 ≤ N ≤ 1000, 2 ≤ M ≤ 1000
1 ≤ Hx, Hy, Ex, Ey ≤ 1000
(Hx, Hy)≠ (Ex, Ey)
행렬은 0과 1로만 이루어져 있고, 0이 빈 칸, 1이 벽이다.
출력
D (탈출 할 수 없다면, -1을 출력한다.)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, ans = -1;
    static Point START, END;
    static int[][] board, dp;
    static int[] DR = {-1, 1, 0, 0};
    static int[] DC = {0, 0, -1, 1};//좌우상하
    
    
    static boolean[][][] visited;
    static Queue<Point> queue;

    static class Point {
        int row, col, cast;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
            this.cast = 0;
        }

        public Point(int row, int col, int cast) {
            this.row = row;
            this.col = col;
            this.cast = cast;
        }

        public boolean isEnd() {
            return this.row == END.row && this.col == END.col;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        board = new int[R][C];

        //3차원 bfs
        //2번 index 에 마법 사용 여부를 넣는다.
        //벽을 부수고 가는 경로와 벽을 부수지 않는 경로를 동시에 체크
        visited = new boolean[R][C][2];
        dp = new int[R][C];

        token = new StringTokenizer(br.readLine());
        START = new Point(Integer.parseInt(token.nextToken()) - 1, Integer.parseInt(token.nextToken()) - 1);
        token = new StringTokenizer(br.readLine());
        END = new Point(Integer.parseInt(token.nextToken()) - 1, Integer.parseInt(token.nextToken()) - 1);

        for (int r = 0; r < R; r++) {
            token = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(token.nextToken());
            }
        }

        //현재 탐색 queue
        queue = new LinkedList<>();
        queue.add(new Point(START.row, START.col, 1));
        visited[START.row][START.col][1] = true;

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (temp.isEnd()) {
                ans = dp[temp.row][temp.col];
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nRow = temp.row + DR[d];
                int nCol = temp.col + DC[d];

                if (outRange(nRow, nCol)) {
                    continue;
                }

                //마법을 시전하여 벽을 부숴 이동함
                if(temp.cast > 0 && !visited[nRow][nCol][temp.cast - 1] && board[nRow][nCol] == 1){
                    visited[nRow][nCol][temp.cast - 1] = true;
                    queue.offer(new Point(nRow, nCol, temp.cast - 1));
                    dp[nRow][nCol] = dp[temp.row][temp.col] + 1;
                }
                if(!visited[nRow][nCol][temp.cast] && board[nRow][nCol] == 0){
                    visited[nRow][nCol][temp.cast] = true;
                    queue.offer(new Point(nRow, nCol, temp.cast));
                    dp[nRow][nCol] = dp[temp.row][temp.col] + 1;
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean outRange(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}


