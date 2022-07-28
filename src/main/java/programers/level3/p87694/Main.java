package programers.level3.p87694;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/87694

너무 김 가서 봐
 */


class Solution {

    //상 하 좌 우 1시 5시 7시 11시
    static final int[] dX = {0, 0, -1, 1, 1, 1, -1, -1};
    static final int[] dY = {-1, 1, 0, 0, 1, -1, -1, 1};

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int ans = 99_999_999;
    static boolean[][] map;
    static boolean[][] visited;


    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        //두배로 늘린다 내부에 겹치지 않는 공간으로 가는 경우가 발생하므로
        map = new boolean[102][102];
        visited = new boolean[102][102];

        for (int i = 0; i < rectangle.length; i++) {
            int lx = rectangle[i][0] * 2;
            int ly = rectangle[i][1] * 2;
            int rx = rectangle[i][2] * 2;
            int ry = rectangle[i][3] * 2;

            //전부 칠하기
            for (int x = lx; x <= rx; x++) {
                for (int y = ly; y <= ry; y++) {
                    map[x][y] = true;
                }
            }
        }

        dfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2, 0);

        return ans / 2;
    }

    private void dfs(int cX, int cY, int iX, int iY, int count) {
        //도착
        if (cX == iX && cY == iY) {
            ans = Math.min(ans, count);
            return;
        }
        if (ans <= count || !check(cX, cY)) {
            return;
        }

        visited[cX][cY] = true;
        //이동은 4방향만
        for (int d = 0; d < 4; d++) {
            int nX = cX + dX[d];
            int nY = cY + dY[d];
            dfs(nX, nY, iX, iY, count + 1);
        }
        visited[cX][cY] = false;
    }

    //외곽 체크
    private boolean check(int x, int y) {
        if (visited[x][y] || !map[x][y]) {
            return false;
        }
        //외곽 체크는 8방향 다 진행 해야함
        for (int d = 0; d < 8; d++) {
            int nX = x + dX[d];
            int nY = y + dY[d];

            if (!map[nX][nY]) {
                return true;
            }
        }

        return false;
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
