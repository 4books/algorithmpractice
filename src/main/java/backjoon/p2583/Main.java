package backjoon.p2583;

/*
문제
<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.



입력
첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

출력
첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.

예제 입력 1
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
예제 출력 1
3
7
8
9
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int n;
    static int m;
    static int k; //영역의 갯수

    static int[][] board; //보드

    static int[] dx = {-1, 0, 1, 0}; //좌 하 우 상
    static int[] dy = {0, 1, 0, -1};

    static int count = 0;

    public static void dfs(int x, int y) {
        board[x][y] = 1;//이미 방문한 곳이라 표시
        count++;//넓이를 위한 카운터 추가

        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];
            if (nX >= 0 && nX < m && nY >= 0 && nY < n && board[nX][nY] == 0) {
                dfs(nX, nY);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");
        m = Integer.parseInt(tmp[0]);//높이
        n = Integer.parseInt(tmp[1]);//너비
        k = Integer.parseInt(tmp[2]);//칠해지는 영역 갯수

        board = new int[m][n];

        for (int i = 0; i < k; i++) {
            tmp = br.readLine().split(" ");
            int x1 = Integer.parseInt(tmp[0]);
            int y1 = Integer.parseInt(tmp[1]);
            int x2 = Integer.parseInt(tmp[2]);
            int y2 = Integer.parseInt(tmp[3]);

            //영역 칠하기
            for (int a = y1; a < y2; a++) {
                for (int b = x1; b < x2; b++) {
                    board[a][b] = 1;
                }
            }
        }

        List<Integer> list = new ArrayList<>();//결과를 담을 객체

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) { //새로운 영역이 발견되면
                    count = 0; //넓이를 초기화 하고
                    dfs(i, j);  //탐색 시작
                    list.add(count); //결과에 담는다.
                }
            }
        }

        Collections.sort(list);//오름차순 표시를 위해

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n"); //영역의 갯수 == list 의 사이즈

        for (Integer integer : list) {
            sb.append(integer).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}


