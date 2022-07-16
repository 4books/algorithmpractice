package backjoon.gold1.p1113;
/*
https://www.acmicpc.net/problem/1113

지민이는 수영장을 만들려고 한다. 수영장을 만들 곳의 크기는 N*M이고, 각 칸은 직육면체이다. 따라서, 각 칸의 직육면체의 높이가 쓰여 있는 다음과 같은 땅을 생각할 수 있다.

16661
61116
16661
이 수영장은 15만큼의 물이 들어있는 수영장을 만들 수 있다. 가운데 3개의 칸에 5만큼 물을 채우면 되기 때문이다.

자 이제 가운데 물을 더 추가했다고 생각하면, 벽(높이가 6인 직육면체)을 넘어서 밖으로 나갈 것이다. 물은 항상 높이가 더 낮은 곳으로만 흐르고, 직육면체 위의 표면에는 물이 없다. 그리고, 땅의 높이는 0이고, 땅은 물을 무한대로 흡수 할 수 있다.

땅의 모양이 주어질 때, 수영장에 물이 얼마만큼 있을 수 있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 땅의 높이가 주어진다. 높이는 1보다 크거나 같고, 9보다 작거나 같은 자연수이다.

출력
첫째 줄에 문제의 정답을 출력한다.
 */

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


