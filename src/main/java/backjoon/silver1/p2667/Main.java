package backjoon.silver1.p2667;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int n;
    static int[][] board; //보드

    static int[] dx = {-1, 0, 1, 0}; //좌 하 우 상
    static int[] dy = {0, 1, 0, -1};

    static int count = 0; //단지수

    public static void dfs(int x, int y) {
        board[x][y] = 0; //더 이상 체크하지 않도록 0으로 바꿈
        count++; //단지수 추가

        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];
            //탐색 구역이 범위 내 이고 단지라면
            if (nX >= 0 && nX < n && nY >= 0 && nY < n && board[nX][nY] == 1) {
                dfs(nX, nY);
            }

        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());//크기
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        List<Integer> list = new ArrayList<>();//결과를 담을 list

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    count = 0; //단지수 초기화
                    dfs(i, j);
                    list.add(count);//결과에 담음
                }
            }
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (Integer i : list) {
            sb.append(i).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}


