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
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2 {

    static int R, C, cast;
    static boolean isEnd;
    static final int MAX = 987654321;
    static final int TRUE = 1, FALSE = 0;
    static final int WALL = 1, ROAD = 0, BROKEN = -1;
    static Point START, END, LAST = null;
    static int[][] board, dp;
    static int[] DR = {-1, 1, 0, 0};
    static int[] DC = {0, 0, -1, 1};//좌우상하
    static int[][] visited;
    static Queue<Point> queue;
    static Stack<Point> stack;

    static class Point {
        int row, col, dis;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
            this.dis = 0;
        }

        public Point(int row, int col, int dis) {
            this.row = row;
            this.col = col;
            this.dis = dis;
        }

        public boolean isEqual(Point p) {
            return this.row == p.row && this.col == p.col;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        board = new int[R][C];
        visited = new int[R][C];
        dp = new int[R][C];

        token = new StringTokenizer(br.readLine());
        START = new Point(Integer.parseInt(token.nextToken()) - 1, Integer.parseInt(token.nextToken()) - 1);
        token = new StringTokenizer(br.readLine());
        END = new Point(Integer.parseInt(token.nextToken()) - 1, Integer.parseInt(token.nextToken()) - 1);

        dp[END.row][END.col] = MAX;


        for (int r = 0; r < R; r++) {
            token = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(token.nextToken());
            }
        }

        //현재 탐색 queue
        queue = new LinkedList<>();
        //추후 부슨쪽 전부터 경로를 넣기 위해
        stack = new Stack<>();

        cast = TRUE;

        queue.offer(START);
        visited[START.row][START.col] = TRUE;

        //BFS & DP
        isEnd = false;
        while (!queue.isEmpty() || !stack.isEmpty()) {
            //더 이상 탐색이 불가할 때 경로 초기화
            if (isEnd) {
                rollback();
            } else if (queue.isEmpty()) {
                //queue 가 비었는데 stack 에 데이터가 있으면 롤백
                rollback();
            }

            Point cur = queue.poll();

            //마법을 쓴 상태
            if (cast == FALSE) {
                stack.push(cur);
            }

            //거리 계산
            int nDis = cur.dis + 1;
            //이미 출구보다 값이 크면 의미 X
            if (dp[END.row][END.col] < nDis) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nRow = cur.row + DR[d];
                int nCol = cur.col + DC[d];

                //범위 밖을 벗어나거나 길이 아니거나 이미 방문한 곳
                if (outRange(nRow, nCol) || board[nRow][nCol] == BROKEN || visited[nRow][nCol] == TRUE) {
                    continue;
                }

                //벽이라면
                if (board[nRow][nCol] == WALL) {
                    if (cast > 0) {
                        //벽 부심 - 마지막 방문 등록
                        LAST = cur;
                        cast = FALSE;
                        board[nRow][nCol] = BROKEN;
                        visited[nRow][nCol] = TRUE;
                    } else {
                        //못 부심
                        continue;
                    }
                } else {
                    //방문한 곳
                    visited[nRow][nCol] = TRUE;
                }


                //다음 목적지
                Point next = new Point(nRow, nCol, nDis);

                //최종 목적지 값 비교
                if (next.isEqual(END)) {
                    dp[nRow][nCol] = Math.min(dp[nRow][nCol], nDis);
                    isEnd = true;
                } else {
                    //최종 목적지가 아니면 dp 저장
                    dp[nRow][nCol] = nDis;
                }

                queue.offer(next);
            }

        } //while 문 종료

        System.out.println(dp[END.row][END.col] == MAX ? -1 : dp[END.row][END.col]);


    } //END of Main Method

    private static void rollback() {
        isEnd = false;
        cast = TRUE;
        while (!stack.isEmpty()) {
            Point tmp = stack.pop();
            //거리 및 방문 초기화
            dp[tmp.row][tmp.col] = 0;
            visited[tmp.row][tmp.col] = FALSE;
        }
        //도착 지점 방문 초기화
        visited[END.row][END.col] = FALSE;
        queue.clear();

        //Last 가 null 이 아니면 다시 시작
        if (LAST != null) {
            queue.offer(LAST);
        }
    }

    public static boolean outRange(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}


