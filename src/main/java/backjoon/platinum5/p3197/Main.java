package backjoon.platinum5.p3197;
/*
https://www.acmicpc.net/problem/3197

문제
두 마리의 백조가 호수에서 살고 있었다. 그렇지만 두 마리는 호수를 덮고 있는 빙판으로 만나지 못한다.

호수는 행이 R개, 열이 C개인 직사각형 모양이다. 어떤 칸은 얼음으로 덮여있다.

호수는 차례로 녹는데, 매일 물 공간과 접촉한 모든 빙판 공간은 녹는다. 두 개의 공간이 접촉하려면 가로나 세로로 닿아 있는 것만 (대각선은 고려하지 않는다) 생각한다.

아래에는 세 가지 예가 있다.

...XXXXXX..XX.XXX ....XXXX.......XX .....XX..........
....XXXXXXXXX.XXX .....XXXX..X..... ......X..........
...XXXXXXXXXXXX.. ....XXX..XXXX.... .....X.....X.....
..XXXXX..XXXXXX.. ...XXX....XXXX... ....X......XX....
.XXXXXX..XXXXXX.. ..XXXX....XXXX... ...XX......XX....
XXXXXXX...XXXX... ..XXXX.....XX.... ....X............
..XXXXX...XXX.... ....XX.....X..... .................
....XXXXX.XXX.... .....XX....X..... .................
      처음               첫째 날             둘째 날
백조는 오직 물 공간에서 세로나 가로로만(대각선은 제외한다) 움직일 수 있다.

며칠이 지나야 백조들이 만날 수 있는 지 계산하는 프로그램을 작성하시오.

입력
입력의 첫째 줄에는 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1500.

다음 R개의 줄에는 각각 길이 C의 문자열이 하나씩 주어진다. '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간으로 나타낸다.

출력
첫째 줄에 문제에서 주어진 걸리는 날을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static final int[] dR = {-1, 1, 0, 0};//좌 우 상 하
    static final int[] dC = {0, 0, -1, 1};
    static int R, C, day;
    static char SWAN = 'L', WATER = '.', ICE = 'X';
    static Point[] swanPoint;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] lake = new char[R][C];

        //방문 여부
        boolean[][] visted = new boolean[R][C];

        int swanNum = 2;
        swanPoint = new Point[2];

        //물 모음
        Queue<Point> water = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                lake[i][j] = s.charAt(j);
                if (lake[i][j] == SWAN) {
                    Point swan = new Point(i, j);
                    swanPoint[--swanNum] = swan;
                    water.offer(swan);
                } else if (lake[i][j] != ICE) {
                    water.offer(new Point(i, j));
                }
            }
        }

        //백조가 갈 수 있는 곳 탐색
        Queue<Point> nowFind = new LinkedList<>();
        nowFind.offer(swanPoint[0]); //출발 백조
        visted[swanPoint[0].row][swanPoint[0].col] = true;

        //제한된 BFS
        day = 0;
        while (true) {
            //다음날 녹는 얼음들
            Queue<Point> nextFind = new LinkedList<>();

            //1. 탐색
            while (!nowFind.isEmpty()) {
                Point temp = nowFind.poll();

                for (int i = 0; i < 4; i++) {
                    int nRow = temp.row + dR[i];
                    int nCol = temp.col + dC[i];
                    //방문하지 않은 곳
                    if (inRange(nRow, nCol) && !visted[nRow][nCol]) {
                        //다음 백조를 만났으면 종료
                        if (lake[nRow][nCol] == SWAN && swanPoint[1].row == nRow && swanPoint[1].col == nCol) {
                            System.out.println(day);
                            System.exit(0);
                        }
                        visted[nRow][nCol] = true;

                        //물에 닿아 다음날 녹을 ICE
                        //탐색할 수 없으니 다음으로
                        if (lake[nRow][nCol] == ICE) {
                            nextFind.offer(new Point(nRow, nCol));
                            continue;
                        }
                        nowFind.offer(new Point(nRow, nCol));
                    }
                }
            }

            //2. 찾지 못했으면 다음날 queue 를 지금 queue 로 바꾼다
            nowFind = nextFind;

            //3. 물 녹이기
            //시간 초과 이슈 - BFS 를 While 이 아닌 물 size 만큼만 돌린다 == 오늘 녹을 물만
            //queue 이기 때문에 남은 데이터는 다음날 처리 된다.
            int size = water.size();
            for (int i = 0; i < size; i++) {
                Point temp = water.poll();

                //4방향으로 얼음을 녹인다.
                for (int j = 0; j < 4; j++) {
                    int nRow = temp.row + dR[j];
                    int nCol = temp.col + dC[j];

                    if (inRange(nRow, nCol) && lake[nRow][nCol] == ICE) {
                        lake[nRow][nCol] = WATER;
                        water.offer(new Point(nRow, nCol));
                    }
                }
            }

            //하루 경과
            day++;
        }


    }

    static boolean inRange(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }


}


