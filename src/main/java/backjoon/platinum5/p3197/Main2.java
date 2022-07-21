package backjoon.platinum5.p3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

    static final int[] dR = {-1, 1, 0, 0};//좌 우 상 하
    static final int[] dC = {0, 0, -1, 1};
    static int R, C, day;
    static char SWAN = 'L', WATER = '.', ICE = 'X', MELT = 'M';
    static int[] unf; //집합

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    //v 의 집합 번호를 리턴한다
    static int find(int v) {
        if (v == unf[v]) {
            //집합 번호와 값이 같다면 집합 번호 값 리턴
            return unf[v];
        } else {
            //집합 번호가 다르다면 집합 번호를 같은 것을 찾은 후 리턴
            unf[v] = find(unf[v]);
            return unf[v];
        }
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        
        //집합의 번호가 다르면 fa 집합과 fb 집합을 하나로 묶는다
        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] lake = new char[R][C];
        unf = new int[R * C];

        Queue<Point> water = new LinkedList<>();
        int[] swan = new int[2];

        int swanCnt = 0, value = 0;
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                lake[i][j] = s.charAt(j);

                if (lake[i][j] != ICE) {
                    if (lake[i][j] == SWAN) {
                        //백조를 물로 변경 후 따로 데이터 저장
                        lake[i][j] = WATER;
                        swan[swanCnt++] = value;
                    }
                    water.offer(new Point(i, j));
                }
                //집합을 초기화
                unf[value] = value++;
            }
        }

        //Union & Find
        int day = 0;
        while (!water.isEmpty()) {
            //오늘 녹는 물만
            int size = water.size();

            for (int i = 0; i < size; i++) {
                Point w = water.poll();
                lake[w.row][w.col] = WATER;

                for (int d = 0; d < 4; d++) {
                    int nRow = w.row + dR[d];
                    int nCol = w.col + dC[d];

                    //범위를 벗어난 경우
                    if (outRange(nRow, nCol)) {
                        continue;
                    }
                    
                    if (lake[nRow][nCol] == ICE) {
                        //다음 날에 녹을 얼음
                        lake[nRow][nCol] = MELT;
                        water.offer(new Point(nRow, nCol));
                    } else if (lake[nRow][nCol] == WATER) {
                        //지금 물과 새로운 물을 하나의 집합으로 묶는다
                        union(w.row * C + w.col, nRow * C + nCol);
                    }
                }
            }

            //두 개의 백조가 하나의 집합으로 묶이면 while 문 종료
            if (find(swan[0]) == find(swan[1])) {
                break;
            }

            day++;
        }

        System.out.println(day);

    }

    static boolean outRange(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}
