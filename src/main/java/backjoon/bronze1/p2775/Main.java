package backjoon.bronze1.p2775;

/*
https://www.acmicpc.net/problem/2775
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            List<List<Integer>> ap = new ArrayList<>();
            ap.add(new ArrayList<>());

            //층
            int K = Integer.parseInt(br.readLine());
            //호
            int N = Integer.parseInt(br.readLine());

            //호 초기화
            for (int i = 1; i <= N; i++) {
                ap.get(0).add(i);
            }

            //층
            for (int i = 1; i <= K; i++) {
                //층 추가
                ap.add(new ArrayList<>());
                //1호는 무조건 1
                ap.get(i).add(1);

                List<Integer> down = ap.get(i - 1);
                List<Integer> curr = ap.get(i);

                //호수
                for (int j = 1; j < N; j++) {
                    int num = curr.get(j - 1) + down.get(j);
                    curr.add(num);
                }
            }
            sb.append(ap.get(K).get(N - 1) + "\n");
        }
        System.out.println(sb);
    } //END of Main Method


}


