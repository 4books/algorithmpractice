package backjoon.silver1.p1303;
/*
https://www.acmicpc.net/problem/1303
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int w = 0;
    static int b = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(tk.nextToken());
        int col = Integer.parseInt(tk.nextToken());
        char[][] map = new char[row][col];


        for (int i = 0; i < row; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }





        StringBuilder answer = new StringBuilder();
//        int n = Integer.parseInt(br.readLine());
//        String tmp = br.readLine();



    } //END of Main Method


}


