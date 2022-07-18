package backjoon.silver1.p16918;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] board = new char[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            for (int j = 0; j < C; j++) {
                board[i][j] = t.charAt(j);
            }
        }

        //1. 1초동안 가만히
        for (int i = 0; i <= N; i++) {

        }


        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


}


