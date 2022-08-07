package backjoon.bronze2.p2292;
/*
https://www.acmicpc.net/problem/2292
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //겹 수
        int cnt = 1;
        int layer = 2;
        while (layer <= N) {
            //벌집 겹수
            //겹수가 곧 거리 지그재그로 가면 바로 갈 수 있음
            layer += cnt * 6;
            cnt++;
        }
        System.out.println(cnt);


    } //END of Main Method


}


