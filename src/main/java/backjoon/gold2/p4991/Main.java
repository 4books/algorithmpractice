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
import java.util.*;

public class Main {

    static final int[] dR = {0, 0, -1, 1};
    static final int[] dC = {1, -1, 0, 0};
    static int R, C, ans;

    static char ROBOT = 'o', CLEAN = '.', DIRTY = '*', WALL = 'x';
    static char[][] room;
    static LinkedList<Point> pointList;
    static LinkedList<List<Point>> dp;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] isUse;

    static class Point implements Comparable<Point> {
        int row, col, end, dis;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
            this.end = 0;
            this.dis = 0;
        }

        public Point(int row, int col, int end) {
            this.row = row;
            this.col = col;
            this.end = end;
            this.dis = 0;
        }

        @Override
        public int compareTo(Point o) {
            if (this.end != o.end) {
                return this.end - o.end;
            }
            return this.dis - o.dis;
        }
    }

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

            //dp 초기화
            dp = new LinkedList<>();
            for (int i = 0; i < pointList.size(); i++) {
                dp.add(new ArrayList<>());
            }

            //로봇 초기화
            for (int i = 1; i < pointList.size(); i++) {
                Point tmp = pointList.get(i);
                dp.get(0).add(new Point(tmp.row, tmp.col, i));
            }

            //각 Dirty 지점 초기화
            for (int i = 1; i < pointList.size(); i++) {
                for (int j = 1; j < pointList.size(); j++) {
                    //자기 자신 제외
                    if (i == j) {
                        continue;
                    }
                    Point end = pointList.get(j);

                    //시작 지점에 도착 지점 저장
                    dp.get(i).add(new Point(end.row, end.col, j));
                }
            }


            //출발 지점에서 도착지점 거리 구하기
            for (int i = 0; i < pointList.size() - 1; i++) {

                Point start = pointList.get(i);
                List<Point> targets = dp.get(i);

                for (Point target : targets) {

                    //도착 지점에 이미 값이 저장되어 있는지 확인하여 가져옴
                    if (target.dis != 0) {
                        start.dis = target.dis;
                        continue;
                    }

                    //시작 지점 초기화
                    Queue<Point> queue = setStartNode(start);

                    //탐색 시작
                    searchMinDis(i, target, queue);
                }

                Collections.sort(targets);
            }

            //경로 조합으로 최단거리 구하기
            ans = Integer.MAX_VALUE;
            int[] course = new int[pointList.size()];
            isUse = new boolean[pointList.size()];
            isUse[0] = true;

            setCourse(pointList.size(), 1, course);
            sb.append(ans).append("\n");


        } //테스트 케이스 종료
        System.out.print(sb);

    } //END of Main Method

    private static void setCourse(int size, int level, int[] course) {
        //조합 완성시
        if (size == level) {
            int result = 0;
            boolean flag = true;
            for (int i = 0; i < size - 1; i++) {
                int source = course[i];
                int target = course[i + 1];
                // 0 2 1 3 일 경우
                //0 -> 2로 가는 dis 를 가져옴
                for (Point tmp : dp.get(source)) {
                    if (tmp.end == target) {
                        //노드는 존재하는데 dis 가 0 이면 연결 불가능하다는 것
                        if (tmp.dis == 0) {
                            flag = false;
                        }
                        result += tmp.dis;
                        break;
                    }
                }
                //만약 flag 가 false 인 상태로 오면 연결이 불가능한 것
                if (!flag) {
                    break;
                }
            }
            if (flag) {
                ans = Math.min(ans, result);
            } else {
                ans = -1;
            }
            return;
        }

        //i 노드 번호
        for (int i = 1; i < size; i++) {
            if (isUse[i]) {
                continue;
            }

            isUse[i] = true;
            course[level] = i;

            setCourse(size, level + 1, course);

            isUse[i] = false;
            course[level] = -1;
        }
    }

    private static Queue<Point> setStartNode(Point start) {

        visited = new boolean[R][C];
        visited[start.row][start.col] = true;
        map = new int[R][C];
        Queue<Point> queue = new LinkedList<>();

        for (int d = 0; d < 4; d++) {
            int nRow = start.row + dR[d];
            int nCol = start.col + dC[d];

            if (isOutRange(nRow, nCol) || room[nRow][nCol] == WALL || visited[nRow][nCol]) {
                continue;
            }

            map[nRow][nCol] = 1;
            visited[nRow][nCol] = true;

            queue.offer(new Point(nRow, nCol));
        }
        return queue;
    }

    private static void searchMinDis(int i, Point target, Queue<Point> queue) {

        while (!queue.isEmpty()) {
            Point tmp = queue.poll();

            if (findEnd(tmp.row, tmp.col, target.row, target.col)) {
                //출발 지점 -> 도착 지점 거리 저장
                target.dis = map[tmp.row][tmp.col];

                //로봇이 아니면 반대로도 넣어줘야 함
                if (i != 0) {
                    for (Point reverse : dp.get(target.end)) {
                        //도착 지점 -> 출발 지점 거리 저장
                        if (reverse.end == i) {
                            reverse.dis = target.dis;
                            break;
                        }
                    }
                }
            }

            for (int d = 0; d < 4; d++) {
                int nRow = tmp.row + dR[d];
                int nCol = tmp.col + dC[d];

                if (isOutRange(nRow, nCol) || room[nRow][nCol] == WALL || visited[nRow][nCol]) {
                    continue;
                }

                map[nRow][nCol] = map[tmp.row][tmp.col] + 1;
                visited[nRow][nCol] = true;

                queue.offer(new Point(nRow, nCol));
            }
        }
    }

    static boolean findEnd(int preRow, int preCol, int newRow, int newCol) {
        return preRow == newRow && preCol == newCol;
    }

    static boolean isOutRange(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}
