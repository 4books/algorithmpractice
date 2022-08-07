package backjoon.silver3.p2579;
/*
https://www.acmicpc.net/problem/2579
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        //계단 오르기 규칙때문에 + 3해줌
        int[] stair = new int[N + 3];
        int[] point = new int[N + 3];
        for (int i = 1; i <= N; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        if (N <= 1) {
            answer = stair[N];
        } else {
            //3번째 계단까지
            point[1] = stair[1];
            point[2] = stair[1] + stair[2];
            point[3] = Math.max(stair[1], stair[2]) + stair[3];
            for (int i = 4; i <= N; i++) {
                //3번째 계단 전과 1번째 계단 전에 오는 경우
                //2번째 계단에서 오는 경우
                point[i] = Math.max(point[i - 3] + stair[i - 1], point[i - 2]) + stair[i];
            }
            answer = point[N];
        }
        System.out.println(answer);


    } //END of Main Method
}


