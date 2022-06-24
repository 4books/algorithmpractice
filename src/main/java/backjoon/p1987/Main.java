package backjoon.p1987;

/*
알파벳 다국어
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
2 초	256 MB	72738	23226	14269	29.114%
문제
세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.

말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

입력
첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.

출력
첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.

예제 입력 1
2 4
CAAB
ADCB
예제 출력 1
3
예제 입력 2
3 6
HFDFFB
AJHGDH
DGAGEH
예제 출력 2
6
예제 입력 3
5 5
IEFCJ
FHFKC
FFALF
HFGCF
HMCHH
예제 출력 3
10
 */

import java.io.*;

public class Main {

    static int R;//세로
    static int C;//가로

    static int[][] board; //보드
    static boolean[] visited;//방문한 곳

    static int[] dx = {-1, 0, 1, 0}; //좌 하 우 상
    static int[] dy = {0, 1, 0, -1};

    static int answer = 1; //첫번째는 무조건 방문함

    static void dfs(int x, int y, int count) {
        answer = Math.max(answer, count); //최대 이동 횟수 확인

        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];
            if (0 <= nX && nX < C && 0 <= nY && nY < R) {
                if (!visited[board[nY][nX]]) {
                    visited[board[nY][nX]] = true; //해당 지점 방문 표시

                    dfs(nX, nY, count + 1); //탐색

                    visited[board[nY][nX]] = false; //다시 뒤로가서 탐색 위해
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);//높이
        C = Integer.parseInt(tmp[1]);//너비

        board = new int[R][C];//데이터
        visited = new boolean[26];//방문한 곳

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = row.charAt(j) - 65; //대문자만 나오므로
            }
        }

        visited[board[0][0]] = true; //첫번째는 무조건 방문
        dfs(0, 0, 1);//count 는 일단 1

        bw.write(String.valueOf(answer));
        bw.close();
    }
}


