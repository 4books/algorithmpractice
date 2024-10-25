package ajd.q1;
/*
    k번 확장 한다..
    한번 확장할때 두번 복사하는데
    한번은 오른쪽으로 그 다음은 아래로 복사한다..
    첫번째 오른쪽으로는 좌우 반대로 값이 복사되어야 되고..
    두번째 아래로 복사될 때는 상하로 반대로 값이 복사되어야 한다...
 */

class Solution {
    public int[][] solution(int[][] square, int k) {

        int[][] newSquare = null;

        for (int o = 0; o < k; o++) {

            int n = square.length;
            int m = square[0].length;
            //1번 확장하면 두배로 커짐
            newSquare = new int[n * 2][m * 2];
            
            //원래 있던 값들 복사
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    newSquare[i][j] = square[i][j];
                }
            }

            // 오른쪽으로 복사 (좌우 반전)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    newSquare[i][m + j] = square[i][m - j - 1];
                }
            }

            // 아래로 복사 (상하 반전)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2 * m; j++) {
                    newSquare[n + i][j] = newSquare[n - i - 1][j];
                }
            }

            //배열 바뀜
            square = newSquare;
        }
        return newSquare;
    }
}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] input = {{1, 0,0}, {0,1,1}, {1,0,1}};
        s.solution(input, 2);
    }



}
