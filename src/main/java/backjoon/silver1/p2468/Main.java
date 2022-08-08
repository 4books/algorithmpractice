package backjoon.silver1.p2468;
/*
https://www.acmicpc.net/problem/2468
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, rain;
    static boolean[][] visited;
    static int[][] map;
    static int[] DR = {1, -1, 0, 0};
    static int[] DC = {0, 0, 1, -1};

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
                max = Math.max(max, map[i][j]);
            }
        }

        //비가 오지 않는 최소 답은 1
        int answer = 1;
        rain = 1;
        for (int R = 1; R < max; R++) {
            visited = new boolean[N][N];
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= R) {
                        visited[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!visited[i][j]){
                        tmp++;
                        visited[i][j] = true;
                        dfs(new Point(i, j));
                    }
                }
            }
            answer = Math.max(answer, tmp);
        }

        System.out.println(answer);


    } //END of Main Method

    private static void dfs(Point point) {

        for (int i = 0; i < 4; i++) {
            int nRow = point.row + DR[i];
            int nCol = point.col + DC[i];


            if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < N && !visited[nRow][nCol]) {
                visited[nRow][nCol] = true;
                dfs(new Point(nRow, nCol));
            }
        }
    }


}


