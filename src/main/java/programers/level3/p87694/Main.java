package programers.level3.p87694;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/87694

너무 김 가서 봐
 */

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    //상 하 좌 우 1시 5시 7시 11시
    static final int[] dR = {-1, 1, 0, 0, 1, -1, -1, 1};
    static final int[] dC = {0, 0, -1, 1, 1, 1, -1, -1};

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static boolean outRange(int row, int col) {
        return row < 0 || row >= 50 || col < 0 || col >= 50;
    }
    //https://velog.io/@ujone/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%95%84%EC%9D%B4%ED%85%9C-%EC%A4%8D%EA%B8%B0-JAVA

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int[][] map = new int[52][52];
        boolean[][] visited = new boolean[52][52];
        int[][] dis = new int[52][52];

        for (int i = 0; i < rectangle.length; i++) {
            int lc = rectangle[i][0];
            int lr = rectangle[i][1];
            int rc = rectangle[i][2];
            int rr = rectangle[i][3];

            //전부 칠하기
            for (int r = lr; r <= rr; r++) {
                for (int c = lc; c <= rc; c++) {
                    map[r][c] += 1;
                }
            }
        }

        Point start = new Point(characterY, characterX);
        Point end = new Point(itemY, itemX);

        Queue<Point> queue = new LinkedList<>();

        queue.offer(start);

        while (!queue.isEmpty()) {
            Point tmp = queue.poll();

            visited[tmp.row][tmp.col] = true;

            //도착했으면
            if (tmp.row == itemY && tmp.col == itemX) {
                answer = dis[tmp.row][tmp.col];
                break;
            }

            //4방향 체크
            for (int d = 0; d < 4; d++) {
                int nRow = tmp.row + dR[d];
                int nCol = tmp.col + dC[d];

                if (outRange(nRow, nCol) || visited[nRow][nCol] || map[nRow][nCol] == 0) {
                    continue;
                }

                //다음 지점이 외곽 인지 확인
                for (int i = 0; i < 8; i++) {
                    int cRow = nRow + dR[i];
                    int cCol = nCol + dC[i];

                    if (outRange(cRow, cCol) || visited[nRow][nCol]) {
                        continue;
                    }

                    //외곽이라면
                    if (map[cRow][cCol] == 0) {
                        queue.offer(new Point(nRow, nCol));
                        dis[nRow][nCol] += dis[tmp.row][tmp.col] + 1;
                        break;
                    }
                }
            }
        }

        return answer;
    }

}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;

        answer = s.solution(new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}}, 1, 3, 7, 8);
        expect = 17;
        System.out.println(answer + " " + expect);

        answer = s.solution(new int[][]{{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}}, 9, 7, 6, 1);
        expect = 11;
        System.out.println(answer + " " + expect);
    }
}
