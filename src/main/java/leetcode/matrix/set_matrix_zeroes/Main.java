package leetcode.matrix.set_matrix_zeroes;
/*
https://leetcode.com/problems/set-matrix-zeroes/
 */

import java.util.ArrayList;
import java.util.List;

class Point {
    int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
class Solution {
    public void setZeroes(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;

        List<Point> zeroList = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(matrix[i][j] == 0){
                    zeroList.add(new Point(i, j));
                }
            }
        }
        for (Point point : zeroList) {
            int row = point.row;
            int col = point.col;

            for (int r = 0; r < R; r++) {
                matrix[r][col] = 0;
            }
            for (int c = 0; c < C; c++) {
                matrix[row][c] = 0;
            }
        }
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
