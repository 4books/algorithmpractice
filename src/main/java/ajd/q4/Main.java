package ajd.q4;
/*
어디서든 시작가능.. 알파벳 순서대로.. 연결해야 하나.. 딱 한번 역행할 수 있음...
 */

class Solution {

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int answer = 0;

    void dfs(int x, int y, int count, boolean useChance, boolean[][] visited, String[][] board){

        answer = Math.max(answer, count);
        char curr = board[x][y].charAt(0);

        for(int i=0; i<4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];

            if(newX >= 0 && newX < visited.length && newY >= 0 && newY < visited[0].length && !visited[newX][newY]){
                char next = board[newX][newY].charAt(0);
                if(curr < next){
                    visited[newX][newY] = true;
                    dfs(newX, newY, count+1, useChance, visited, board);
                    visited[newX][newY] = false;
                } else {
                    if(!useChance && curr != next){ //딱 한번 앞에 오는 알파벳으로 이동 가능하다
                        visited[newX][newY] = true;
                        dfs(newX, newY, count+1, true, visited, board);
                        visited[newX][newY] = false;
                    }
                }
            }

        }
    }
    public int solution(String[][] board) {

        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                visited[i][j] = true;
                dfs(i, j, 1, false, visited, board);
                visited[i][j] = false;
            }
        }
        return answer;
    }
}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

    }



}
