package backjoon.gold1.p1113;


import java.io.*;
import java.util.*;

public class Main {

    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static int M;
    static int N;

    static class Point {
        public int row;
        public int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int solution(int value, int y, int x, int[][] board, boolean[][] visited) {
        int result = 1;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(y, x));
        
        //만약 한 곳이라도 땅으로 흐른다면 수영장이 될수 없음
        boolean canPool = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nR = p.row + dR[i];
                int nC = p.col + dC[i];

                //블록 범위를 벗어나면 수영장이 될 수 없음
                if(nR < 0 || nR >= M || nC < 0 || nC >= N){
                    canPool = false;
                    continue;

                //방문 하지 않고 높이보다 높다면 수영장이 될 수 있는 후보
                } else if (board[nR][nC] < value && !visited[nR][nC]){
                    visited[nR][nC] = true;
                    queue.add(new Point(nR, nC));
                    result++;
                }
            }
        }
        if(!canPool){
            return 0;
        }

        return result;
    }

    //방문 초기화
    private static void visitedInit(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            boolean[] arr = visited[i];
            Arrays.fill(arr, false);
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        M = Integer.parseInt(tmp[0]);
        N = Integer.parseInt(tmp[1]);

        int[][] board = new int[M][N];
        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                int number = Integer.parseInt(split[j]);
                board[i][j] = number;
                max = Math.max(max, number);
                min = Math.min(min, number);
            }
        }

        int answer = 0;
        for (int i = min; i <= max; i++) { //최소 높이부터 최대 높이까지
            visitedInit(visited); //방문 초기화
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (board[j][k] < i && !visited[j][k]) {
                        visited[j][k] = true;
                        answer += solution(i, j, k, board, visited);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}


