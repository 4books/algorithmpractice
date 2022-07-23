package backjoon.gold3.p2812;
/*
https://www.acmicpc.net/problem/2812

문제
N자리 숫자가 주어졌을 때, 여기서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ K < N ≤ 500,000)

둘째 줄에 N자리 숫자가 주어진다. 이 수는 0으로 시작하지 않는다.

출력
입력으로 주어진 숫자에서 K개를 지웠을 때 얻을 수 있는 가장 큰 수를 출력한다

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String num = br.readLine();
        int count = 0; //지운 갯수
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            //숫자로 변경
            int n = num.charAt(i) - '0';

            //지운 숫자가 K 보다 작고, 가장 높은 값이 현재 n 보다 작으면 pop
            while(!stack.isEmpty() && count < K && stack.peek() < n) {
                stack.pop();
                count++;
            }
            stack.push(n);
        }

        for (int i = 0; i < N - K; i++) {
            answer.append(stack.get(i));
        }
        System.out.println(answer);

    } //END of Main Method
}


