package backjoon.gold4.p2580;

/*

 */

import java.io.*;

public class Main {

    static StringBuilder answer = new StringBuilder();
    static int[][] board; //보드

    static void dfs(int row, int col) {
        if (col == 9) { //해당 row 의 끝까지 탐색 완료하면 다음 row 로
            dfs(row + 1, 0);
            return;
        }

        if (row == 9) { //모든 탐색이 끝나면 출력
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer.append(board[i][j]).append(" ");
                }
                answer.append("\n");
            }

            System.out.println(answer);
            System.exit(0);
        }

        //0 이면 가능한 숫자 있는지 확인
        if (board[row][col] == 0) {
            for (int i = 1; i <= 9; i++) { //값 중복 확인
                if (isPossible(row, col, i)) {
                    board[row][col] = i;
                    dfs(row, col + 1); //다음 열 검색
                }
            }

            //스도쿠가 입력이 잘못되어 있는 경우
            //즉, 맞지 않는 자리에 숫자가 들어간 경우 다시 처음부터 체크한다.
            board[row][col] = 0;

        } else { //이미 값이 있으면 다음 열로
            dfs(row, col + 1);
        }
    }

    static boolean isPossible(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            //같은 행이나 열에 똑같은 값이 있다면 false
            if (board[row][i] == value || board[i][col] == value) {
                return false;
            }
        }

        //3*3 칸의 첫 행열 위치
        int setRow = (row / 3) * 3;
        int setCol = (col / 3) * 3;
        for (int i = setRow; i < setRow + 3; i++) {
            for (int j = setCol; j < setCol + 3; j++) {
                if(board[i][j] == value) {
                    return false;
                }
            }
        }

        return true; //중복 없음
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        dfs(0, 0);

    }
}


