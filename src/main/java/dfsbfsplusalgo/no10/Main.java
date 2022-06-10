package dfsbfsplusalgo.no10;

/*
미로탐색(DFS)
7*7 격자판 미로를 탈출하는 경로의 가지수를 출력하는 프로그램을 작성하세요. 출발점은 격
자의 (1, 1) 좌표이고, 탈출 도착점은 (7, 7)좌표이다. 격자판의 1은 벽이고, 0은 통로이다. 격
자판의 움직임은 상하좌우로만 움직인다. 미로가 다음과 같다면
출발 0 0 0 0 0 0
  0 1 1 1 1 1 0
  0 0 0 1 0 0 0
  1 1 0 1 0 1 1
  1 1 0 0 0 0 1
  1 1 0 1 1 0 0
  1 0 0 0 0 0 도착
위의 지도에서 출발점에서 도착점까지 갈 수 있는 방법의 수는 8가지이다.
▣ 입력설명
7*7 격자판의 정보가 주어집니다.
▣ 출력설명
첫 번째 줄에 경로의 가지수를 출력한다.
▣ 입력예제 1
0 0 0 0 0 0 0
0 1 1 1 1 1 0
0 0 0 1 0 0 0
1 1 0 1 0 1 1
1 1 0 0 0 0 1
1 1 0 1 1 0 0
1 0 0 0 0 0 0
▣ 출력예제 1
8
 */

import java.io.*;

public class Main {

    static int answer = 0;
    static int[][] board; //미로
    static int[] dx = {-1, 0, 1, 0};//차례대로 좌 하 우 상
    static int[] dy = {0, 1, 0, -1};

    public static void dfs(int x, int y) {

        if (x == 7 && y == 7) { //도착 했으면 정답 추가
            answer++;
        } else {
            for (int i = 0; i < 4; i++) {
                int nX = x + dx[i];
                int nY = y + dy[i];
                if (nX > 0 && nX < 8 && nY > 0 && nY < 8 && board[nX][nY] == 0) {
                    board[nX][nY] = 1; //이미 지나온 길이라 체크
                    dfs(nX, nY);
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
        board[1][1] = 1;
        dfs(1, 1);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}


