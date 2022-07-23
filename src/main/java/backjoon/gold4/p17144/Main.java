package backjoon.gold4.p17144;
/*
https://www.acmicpc.net/problem/17144

미세먼지를 제거하기 위해 구사과는 공기청정기를 설치하려고 한다. 공기청정기의 성능을 테스트하기 위해 구사과는 집을 크기가 R×C인 격자판으로 나타냈고, 1×1 크기의 칸으로 나눴다. 구사과는 뛰어난 코딩 실력을 이용해 각 칸 (r, c)에 있는 미세먼지의 양을 실시간으로 모니터링하는 시스템을 개발했다. (r, c)는 r행 c열을 의미한다.



공기청정기는 항상 1번 열에 설치되어 있고, 크기는 두 행을 차지한다. 공기청정기가 설치되어 있지 않은 칸에는 미세먼지가 있고, (r, c)에 있는 미세먼지의 양은 Ar,c이다.

1초 동안 아래 적힌 일이 순서대로 일어난다.

미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
(r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
확산되는 양은 Ar,c/5이고 소수점은 버린다.
(r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
공기청정기가 작동한다.
공기청정기에서는 바람이 나온다.
위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
다음은 확산의 예시이다.



왼쪽과 오른쪽에 칸이 없기 때문에, 두 방향으로만 확산이 일어났다.



인접한 네 방향으로 모두 확산이 일어난다.



공기청정기가 있는 칸으로는 확산이 일어나지 않는다.

공기청정기의 바람은 다음과 같은 방향으로 순환한다.



방의 정보가 주어졌을 때, T초가 지난 후 구사과의 방에 남아있는 미세먼지의 양을 구해보자.

입력
첫째 줄에 R, C, T (6 ≤ R, C ≤ 50, 1 ≤ T ≤ 1,000) 가 주어진다.

둘째 줄부터 R개의 줄에 Ar,c (-1 ≤ Ar,c ≤ 1,000)가 주어진다. 공기청정기가 설치된 곳은 Ar,c가 -1이고, 나머지 값은 미세먼지의 양이다. -1은 2번 위아래로 붙어져 있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.

출력
첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력한다.

예제 입력 1
7 8 1
0 0 0 0 0 0 0 9
0 0 0 0 3 0 0 8
-1 0 5 0 0 0 22 0
-1 8 0 0 0 0 0 0
0 0 0 0 0 10 43 0
0 0 5 0 15 0 0 0
0 0 40 0 0 0 20 0
예제 출력 1
188
미세먼지의 확산이 일어나면 다음과 같은 상태가 된다.



공기청정기가 작동한 이후 상태는 아래와 같다.


 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};
    static int[][] room;
    static int R, C;

    static class Dust {
        int row, col, vol;

        public Dust(int row, int col, int vol) {
            this.row = row;
            this.col = col;
            this.vol = vol;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        //최종적으로 아래쪽 청정기 row 가 저장
        int cleaner = 0;

        room = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                int p = Integer.parseInt(st.nextToken());
                room[r][c] = p;
                if (p == -1) {
                    cleaner = r;
                }
            }
        }

        int sec = 0;
        while (sec++ < T) {

            //1. 확산될 먼지
            Queue<Dust> dustQ = new LinkedList<>();
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (room[r][c] <= 0) {
                        continue;
                    }
                    dustQ.offer(new Dust(r, c, room[r][c]));
                }
            }

            //2. 확산
            while (!dustQ.isEmpty()) {
                Dust now = dustQ.poll();

                if (now.vol < 5) {
                    continue;
                }

                int expand = now.vol / 5;
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nRow = now.row + dR[d];
                    int nCol = now.col + dC[d];

                    if (outRange(nRow, nCol)) {
                        continue;
                    }

                    room[nRow][nCol] += expand;
                    cnt++;
                }
                room[now.row][now.col] -= expand * cnt;
            }

            //2. 위쪽 공기 청정기
            int row = cleaner - 1;
            //하
            for (int r = row - 1; r > 0; r--) {
                room[r][0] = room[r - 1][0];
            }
            //좌
            for (int c = 0; c < C - 1; c++) {
                room[0][c] = room[0][c + 1];
            }
            //상
            for (int r = 0; r < row; r++) {
                room[r][C - 1] = room[r + 1][C - 1];
            }
            //우
            for (int c = C - 1; c > 1; c--) {
                room[row][c] = room[row][c - 1];
            }

            //나가는 바람은 정화되어서
            room[row][1] = 0;

            //3. 아래쪽 청정기
            row = cleaner;
            //상
            for (int r = row + 1; r < R - 1; r++) {
                room[r][0] = room[r + 1][0];
            }
            //좌
            for (int c = 0; c < C - 1; c++) {
                room[R - 1][c] = room[R - 1][c + 1];
            }
            //하
            for (int r = R - 1; r > row; r--) {
                room[r][C - 1] = room[r - 1][C - 1];
            }
            //우
            for (int c = C - 1; c  > 1; c--) {
                room[row][c] = room[row][c - 1];
            }
            room[row][1] = 0;
        }

        //결과 출력
        int sum = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (room[r][c] > 0) {
                    sum += room[r][c];
                }
            }
        }
        System.out.println(sum);

    } //END of Main Method

    private static boolean outRange(int nRow, int nCol) {
        //1. 칸이 있고
        //2. 공기 청정기 없을 때 확산
        return nRow < 0 || nRow >= R || nCol < 0 || nCol >= C
                || room[nRow][nCol] == -1;
    }

}


