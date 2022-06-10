package dfsbfsplusalgo.no11;

/*
11. 미로의 최단거리 통로(BFS)
설명

7*7 격자판 미로를 탈출하는 최단경로의 길이를 출력하는 프로그램을 작성하세요.

경로의 길이는 출발점에서 도착점까지 가는데 이동한 횟수를 의미한다.

출발점은 격자의 (1, 1) 좌표이고, 탈출 도착점은 (7, 7)좌표이다. 격자판의 1은 벽이고, 0은 도로이다.

격자판의 움직임은 상하좌우로만 움직인다. 미로가 다음과 같다면

Image1.jpg

위와 같은 경로가 최단 경로의 길이는 12이다.


입력
첫 번째 줄부터 7*7 격자의 정보가 주어집니다.


출력
첫 번째 줄에 최단으로 움직인 칸의 수를 출력한다. 도착할 수 없으면 -1를 출력한다.


예시 입력 1

0 0 0 0 0 0 0
0 1 1 1 1 1 0
0 0 0 1 0 0 0
1 1 0 1 0 1 1
1 1 0 1 0 0 0
1 0 0 0 1 0 0
1 0 1 0 0 0 0
예시 출력 1

12
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int[][] board; //미로
    static int[] dx = {-1, 0, 1, 0};//차례대로 좌 하 우 상
    static int[] dy = {0, 1, 0, -1};

    //BFS
    static int[][] dis; //1,1 에서 갈 수 있는 거리를 저장해 놓는 곳

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();

        queue.offer(new Point(x, y));

        board[x][y] = 1;

        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nX = tmp.x + dx[i];
                int nY = tmp.y + dy[i];
                if (nX > 0 && nX < 8 && nY > 0 && nY < 8 && board[nX][nY] == 0) {
                    board[nX][nY] = 1; //이미 지나온 길이라 체크
                    queue.offer(new Point(nX, nY));
                    dis[nX][nY] = dis[tmp.x][tmp.y] + 1;
                }
            }
        }
    }

    //DFS
    public static void dfs(int count, int x, int y) {
        if (count >= answer) {
            return;
        }
        if (x == 7 && y == 7) { //도착 했으면 정답 추가
            answer = count;
        } else {
            for (int i = 0; i < 4; i++) {
                int nX = x + dx[i];
                int nY = y + dy[i];
                if (nX > 0 && nX < 8 && nY > 0 && nY < 8 && board[nX][nY] == 0) {
                    board[nX][nY] = 1; //이미 지나온 길이라 체크
                    dfs(count + 1, nX, nY);
                    board[nX][nY] = 0; //뒤돌아 올때 초기화
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        board = new int[8][8];

        for (int i = 1; i <= 7; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 1; j <= 7; j++) {
                board[i][j] = Integer.parseInt(tmp[j - 1]);
            }
        }
        //dfs
//        board[1][1] = 1;
//        dfs(0, 1, 1);
//        if (answer == Integer.MAX_VALUE) {
//            answer = -1;
//        }
//        bw.write(String.valueOf(answer));

        //bfs
        dis = new int[8][8];

        bfs(1, 1);

        if (dis[7][7] == 0) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(dis[7][7]));
        }

        bw.flush();
        bw.close();
    }
}


