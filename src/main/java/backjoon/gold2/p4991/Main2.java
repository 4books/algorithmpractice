package backjoon.gold2.p4991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static final int[] dR = {0, 0, -1, 1};
    static final int[] dC = {1, -1, 0, 0};
    static int R, C;

    static char ROBOT = 'o', CLEAN = '.', DIRTY = '*', WALL = 'x';
    static char[][] room;
    static boolean[][] visited;
    static int[][] dp, sum;
    static int[] distance;

    static class Point {
        int row, col, dis, number;

        public Point(int row, int col, int dis) {
            this.row = row;
            this.col = col;
            this.dis = dis;
            this.number = 99;
        }

        public Point(int row, int col, int dis, int number) {
            this.row = row;
            this.col = col;
            this.dis = dis;
            this.number = number;
        }
    }

    /* 메모리 초과 */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            if (R == 0 && C == 0) {
                break;
            }
            room = new char[R][C];
            sum = new int[R][C];


            Queue<Point> now = new LinkedList<>();
            List<Point> startList = new LinkedList<>();

            int startNumber = 0;
            for (int i = 0; i < R; i++) {
                char[] row = br.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    char c = row[j];
                    if (c == ROBOT || c == DIRTY) {
                        startList.add(new Point(i, j, 0, startNumber++));
                    }
                    room[i][j] = c;
                }
            }

            int total = startList.size();

            //청소 시작
            for (int i = 0; i < total; i++) {
                distance = new int[total];
                visited = new boolean[R][C];
                dp = new int[R][C];

                //시작 지점
                Point s = startList.get(i);
                room[s.row][s.col] = CLEAN;
                now.offer(new Point(s.row, s.col, 0));

                //bfs 탐색 시작
                while (!now.isEmpty()) {
                    Point tmp = now.poll();

                    visited[tmp.row][tmp.col] = true;

                    for (int d = 0; d < 4; d++) {
                        int nRow = tmp.row + dR[d];
                        int nCol = tmp.col + dC[d];

                        if (isOutRange(nRow, nCol) || room[nRow][nCol] == WALL) {
                            continue;
                        }

                        if (!visited[nRow][nCol]) {
                            dp[nRow][nCol] = tmp.dis + 1;

                            //더러운 곳을 만나면
                            if (room[nRow][nCol] == DIRTY) {
                                for (Point p : startList) {
                                    if (p.row == nRow && p.col == nCol) {
                                        distance[p.number] = dp[nRow][nCol];
                                        break;
                                    }
                                }
                            }
                            now.offer(new Point(nRow, nCol, dp[nRow][nCol]));
                        }
                    }
                } //탐색 종료

                for (int j = 0; j < distance.length; j++) {
                    Point p = startList.get(j);
                    //로봇 있는 지점은 체크 X
                    if (j != 0) {
                        //다시 더러운 곳으로 초기화
                        room[p.row][p.col] = DIRTY;
                    }
                    p.dis += distance[j];
                }
            } //다음 더러운 곳으로

            //로봇과 더러운 지점을 들러서 나온 것이므로 횟수에서 제외함
            int min = startList.get(1).dis - total;
            sb.append(min <= 0 ? -1 : min).append("\n");

        } //테스트 케이스 종료
        System.out.print(sb);

    } //END of Main Method

    static boolean isOutRange(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}
