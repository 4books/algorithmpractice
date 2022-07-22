package backjoon.gold2.p4991;
/*
https://www.acmicpc.net/problem/4991

문제
오늘은 직사각형 모양의 방을 로봇 청소기를 이용해 청소하려고 한다. 이 로봇 청소기는 유저가 직접 경로를 설정할 수 있다.

방은 크기가 1×1인 정사각형 칸으로 나누어져 있으며, 로봇 청소기의 크기도 1×1이다. 칸은 깨끗한 칸과 더러운 칸으로 나누어져 있으며, 로봇 청소기는 더러운 칸을 방문해서 깨끗한 칸으로 바꿀 수 있다.

일부 칸에는 가구가 놓여져 있고, 가구의 크기도 1×1이다. 로봇 청소기는 가구가 놓여진 칸으로 이동할 수 없다.

로봇은 한 번 움직일 때, 인접한 칸으로 이동할 수 있다. 또, 로봇은 같은 칸을 여러 번 방문할 수 있다.

방의 정보가 주어졌을 때, 더러운 칸을 모두 깨끗한 칸으로 만드는데 필요한 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트케이스로 이루어져 있다.

각 테스트 케이스의 첫째 줄에는 방의 가로 크기 w와 세로 크기 h가 주어진다. (1 ≤ w, h ≤ 20) 둘째 줄부터 h개의 줄에는 방의 정보가 주어진다. 방의 정보는 4가지 문자로만 이루어져 있으며, 각 문자의 의미는 다음과 같다.

.: 깨끗한 칸
*: 더러운 칸
x: 가구
o: 로봇 청소기의 시작 위치
더러운 칸의 개수는 10개를 넘지 않으며, 로봇 청소기의 개수는 항상 하나이다.

입력의 마지막 줄에는 0이 두 개 주어진다.

출력
각각의 테스트 케이스마다 더러운 칸을 모두 깨끗한 칸으로 바꾸는 이동 횟수의 최솟값을 한 줄에 하나씩 출력한다. 만약, 방문할 수 없는 더러운 칸이 존재하는 경우에는 -1을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main3 {

    static final int[] dR = {0, 0, -1, 1};
    static final int[] dC = {1, -1, 0, 0};
    static int R, C, ans;

    static char ROBOT = 'o', CLEAN = '.', DIRTY = '*', WALL = 'x';
    static char[][] room;
    static LinkedList<Point> pointList;
    static int[][] dp;
    static boolean[] isUse;
    static boolean[][] visited;

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {

        /* 시간 초과... */
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

            pointList = new LinkedList<>();

            for (int i = 0; i < R; i++) {
                char[] row = br.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    char c = row[j];
                    if (c == ROBOT) {
                        c = CLEAN;
                        pointList.addFirst(new Point(i, j));
                    } else if (c == DIRTY) {
                        pointList.add(new Point(i, j));
                    }
                    room[i][j] = c;
                }
            }

            //초기화
            LinkedList<Point> root = new LinkedList<>();
            root.add(pointList.get(0));

            //for DFS
            isUse = new boolean[pointList.size()];
            isUse[0] = true;
            ans = Integer.MAX_VALUE;

            //이동 경로 설정 로봇 -> 1 -> 2 -> 3...
            setRoot(pointList.size(), 1, root);
            sb.append(ans == 0 ? -1 : ans).append("\n");


        } //테스트 케이스 종료
        System.out.print(sb);

    } //END of Main Method

    private static void setRoot(int size, int level, LinkedList<Point> root) {

        //조합 완성시
        if (size == level) {
            ans = Math.min(ans, searchMinDis(root));
            return;
        }

        for (int i = 1; i < size; i++) {
            if (isUse[i]) {
                continue;
            }

            isUse[i] = true;
            root.add(pointList.get(i));

            setRoot(size, level + 1, root);

            isUse[i] = false;
            root.removeLast();
        }
    }

    private static int searchMinDis(LinkedList<Point> root) {
        LinkedList<Point> queue = new LinkedList<>();

        int result = 0;

        //1 -> 2 갈때, 값 설정
        for (int i = 1; i < root.size(); i++) {
            dp = new int[R][C];
            visited = new boolean[R][C];

            Point start = root.get(i - 1);
            Point end = root.get(i);

            visited[start.row][start.col] = true;

            boolean findEnd = setFirst(start, end, queue);
            if (findEnd) {
                result += 1;
                continue;
            }

            findEnd = false;
            while (!queue.isEmpty()) {
                Point tmp = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nRow = tmp.row + dR[d];
                    int nCol = tmp.col + dC[d];

                    if (isOutRange(nRow, nCol) || room[nRow][nCol] == WALL || visited[nRow][nCol]) {
                        continue;
                    }
                    //end 지점을 찾았을 때
                    if (nRow == end.row && nCol == end.col) {
                        result += dp[tmp.row][tmp.col] + 1;
                        findEnd = true;
                        break;
                    }

                    visited[nRow][nCol] = true;
                    dp[nRow][nCol] = dp[tmp.row][tmp.col] + 1;

                    queue.offer(new Point(nRow, nCol));
                }
                if (findEnd) {
                    queue.clear();
                }
            }


        }
        //이미 정답보다 높으면 의미 없음 bfs 종료
        if (result > ans) {
            return Integer.MAX_VALUE;
        }
        return result;
    }

    private static boolean setFirst(Point start, Point end, LinkedList<Point> queue) {
        for (int d = 0; d < 4; d++) {
            int nRow = start.row + dR[d];
            int nCol = start.col + dC[d];
            if (isOutRange(nRow, nCol) || room[nRow][nCol] == WALL) {
                continue;
            }
            if (nRow == end.row && nCol == end.col) {
                return true;
            }
            visited[nRow][nCol] = true;
            dp[nRow][nCol] = 1;
            queue.offer(new Point(nRow, nCol));
        }
        return false;
    }

    static boolean isOutRange(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}


