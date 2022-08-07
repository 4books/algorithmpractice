package backjoon.silver1.p1932;
/*
https://www.acmicpc.net/problem/1932
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int H = Integer.parseInt(br.readLine());
        int[][] tri = new int[H][];

        for (int i = 0; i < H; i++) {
            tri[i] = new int[i + 1];

            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < tmp.length; j++) {
                tri[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        for (int i = 1; i < H; i++) {
            //앞 수
            tri[i][0] += tri[i - 1][0];
            //뒷 수
            tri[i][i] += tri[i - 1][i - 1];
            
            //중간수들
            for (int j = 1; j < i; j++) {
                tri[i][j] += Math.max(tri[i - 1][j - 1], tri[i - 1][j]);
            }
        }

        int answer = 0;
        int[] result = tri[H - 1];
        for (int i = 0; i < result.length; i++) {
            answer = Math.max(answer, result[i]);
        }


        System.out.println(answer);



    } //END of Main Method


}


