package leetcode.graph.pacific_atlantic_water_flow;
/*
https://leetcode.com/problems/pacific-atlantic-water-flow/
 */

import java.util.*;

class Point {
    int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {

    static final int[] DR = {1, -1, 0, 0};
    static final int[] DC = {0, 0, 1, -1};
    static int R, C;
    static List<List<Integer>> answer;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        answer = new ArrayList<>();

        R = heights.length;
        C = heights[0].length;

        //size 가 0, 1인 경우 제외
        if (R * C == 0) {
            return answer;
        } else if (R * C == 1) {
            answer.add(new ArrayList<>(Arrays.asList(0, 0)));
            return answer;
        }

        //각 지역 방문 여부
        boolean[][] pV = new boolean[R][C];
        boolean[][] aV = new boolean[R][C];

        //각 바다 Queue
        Queue<Point> pQ = new LinkedList<>();
        Queue<Point> aQ = new LinkedList<>();

        //상하 queue
        for (int c = 0; c < C; c++) {
            //상은 항상 태평양, 하는 항상 대서양
            pQ.offer(new Point(0, c));
            aQ.offer(new Point(R - 1, c));

            //방문했다 체크
            pV[0][c] = true;
            aV[R - 1][c] = true;
        }

        //좌우 queue
        for (int r = 0; r < R; r++) {
            pQ.offer(new Point(r, 0));
            aQ.offer(new Point(r, C - 1));

            pV[r][0] = true;
            aV[r][C - 1] = true;
        }

        //bfs 탐색 시작
        bfs(heights, pV, pQ);
        bfs(heights, aV, aQ);
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (pV[r][c] && aV[r][c]) {
                    answer.add(new ArrayList<>(Arrays.asList(r, c)));
                }
            }
        }

        return answer;
    }

    private void bfs(int[][] heights, boolean[][] visited, Queue<Point> q) {

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int r = cur.row;
            int c = cur.col;

            for (int d = 0; d < 4; d++) {
                int nRow = r + DR[d];
                int nCol = c + DC[d];

                if (nRow < 0 || nRow >= R || nCol < 0 || nCol >= C
                        || heights[nRow][nCol] < heights[r][c] //안에서 바깥으로 흐를 수 있는지 체크
                        || visited[nRow][nCol]) {
                    continue;
                }

                visited[nRow][nCol] = true;

                q.offer(new Point(nRow, nCol));
            }
        }
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;

        s.pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
