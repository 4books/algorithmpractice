package dfsbfsplusalgo.no13_2;


/*
13. 섬나라 아일랜드
설명

N*N의 섬나라 아일랜드의 지도가 격자판의 정보로 주어집니다.

각 섬은 1로 표시되어 상하좌우와 대각선으로 연결되어 있으며, 0은 바다입니다.

섬나라 아일랜드에 몇 개의 섬이 있는지 구하는 프로그램을 작성하세요.

Image1.jpg

만약 위와 같다면 섬의 개수는 5개입니다.


입력
첫 번째 줄에 자연수 N(3<=N<=20)이 주어집니다.

두 번째 줄부터 격자판 정보가 주어진다.


출력
첫 번째 줄에 섬의 개수를 출력한다.


예시 입력 1

7
1 1 0 0 0 1 0
0 1 1 0 1 1 0
0 1 0 0 0 0 0
0 0 0 1 0 1 1
1 1 0 1 1 0 0
1 0 0 0 1 0 0
1 0 1 0 1 0 0
예시 출력 1

5
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n; //board 사이즈
    static int[][] board; //섬들

    //차례대로 좌 하 우 상 좌상 좌하 우상 우하
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
    static int answer = 0;

    static Queue<Point> queue = new LinkedList<>(); //섬인 곳

    public static void bfs() {
        while(!queue.isEmpty()){
            Point tmp = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nX = tmp.x + dx[i];
                int nY = tmp.y + dy[i];
                if (nX >= 0 && nX < n && nY >= 0 && nY < n && board[nX][nY] == 1) {
                    board[nX][nY] = 0;
                    queue.add(new Point(nX, nY));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        //핵심은 1인 곳이 찾으면 거기는 일단 섬이니 정답 하나 추가
        //그리고 1인 곳을 전부 0으로 지워버린다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //1인 곳이 발견되면 거긴 섬이니 Queue 에 추가
                if (board[i][j] == 1) {
                    queue.add(new Point(i, j));
                    board[i][j] = 0;//더 이상 체크할 필요 없이 0으로 초기화
                    answer++;
                    bfs();
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}


