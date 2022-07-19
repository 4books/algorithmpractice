package backjoon.silver1.p16918;
/*
https://www.acmicpc.net/problem/16918

봄버맨은 크기가 R×C인 직사각형 격자판 위에서 살고 있다. 격자의 각 칸은 비어있거나 폭탄이 들어있다.

폭탄이 있는 칸은 3초가 지난 후에 폭발하고, 폭탄이 폭발한 이후에는 폭탄이 있던 칸이 파괴되어 빈 칸이 되며, 인접한 네 칸도 함께 파괴된다. 즉, 폭탄이 있던 칸이 (i, j)인 경우에 (i+1, j), (i-1, j), (i, j+1), (i, j-1)도 함께 파괴된다. 만약, 폭탄이 폭발했을 때, 인접한 칸에 폭탄이 있는 경우에는 인접한 폭탄은 폭발 없이 파괴된다. 따라서, 연쇄 반응은 없다.

봄버맨은 폭탄에 면역력을 가지고 있어서, 격자판의 모든 칸을 자유롭게 이동할 수 있다. 봄버맨은 다음과 같이 행동한다.

가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
다음 1초 동안 봄버맨은 아무것도 하지 않는다.
다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
3과 4를 반복한다.
폭탄을 설치해놓은 초기 상태가 주어졌을 때, N초가 흐른 후의 격자판 상태를 구하려고 한다.

예를 들어, 초기 상태가 아래와 같은 경우를 보자.

...
.O.
...
1초가 지난 후에는 아무 일도 벌어지지 않기 때문에, 위와 같다고 볼 수 있다. 1초가 더 흐른 후에 격자판의 상태는 아래와 같아진다.

OOO
OOO
OOO
1초가 지난 후엔 가운데에 있는 폭탄이 폭발해 가운데 칸과 인접한 네 칸이 빈 칸이 된다.

O.O
...
O.O
입력
첫째 줄에 R, C, N (1 ≤ R, C, N ≤ 200)이 주어진다. 둘째 줄부터 R개의 줄에 격자판의 초기 상태가 주어진다. 빈 칸은 '.'로, 폭탄은 'O'로 주어진다.

출력
총 R개의 줄에 N초가 지난 후의 격자판 상태를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dR = {-1, 1, 0, 0}; //상 하 좌 우
    static int[] dC = {0, 0, -1, 1};
    static int BOMB_TIME = 3; //3초 뒤 터짐

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] board = new char[R][C];
        int[][] bombTime = new int[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = tmp.charAt(j);
                if (board[i][j] == 'O') {
                    bombTime[i][j] = BOMB_TIME;
                }
            }
        }
        //0초에 폭탄 설치 됨
        //1초동안 가만히
        for (int sec = 1; sec <= N; sec++) {
            //짝수 초에 폭탄 설치
            if (sec % 2 == 0) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (board[i][j] == '.') {
                            board[i][j] = 'O';
                            bombTime[i][j] = BOMB_TIME + sec;
                        }
                    }
                }
                continue;
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (bombTime[i][j] == sec) {

                        board[i][j] = '.';

                        for (int k = 0; k < 4; k++) {
                            int row = i + dR[k];
                            int col = j + dC[k];

                            if (row < 0 || row >= R || col < 0 || col >= C) {
                                continue;
                            }

                            //현재 시간에 터져야할 폭탄인데 같이 터져버리면 안됨
                            //따라서 시간도 체크 같이함
                            if (board[row][col] == 'O' && bombTime[row][col] != sec) {
                                board[row][col] = '.';
                                bombTime[row][col] = 0;
                            }
                        }
                    }
                }
            }
        }


        //출력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}


