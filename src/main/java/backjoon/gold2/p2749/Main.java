package backjoon.gold2.p2749;
/*
https://www.acmicpc.net/problem/2749
https://qlyh8.tistory.com/162

문제
피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.

이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n ≥ 2)가 된다.

n=17일때 까지 피보나치 수를 써보면 다음과 같다.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 n이 주어진다. n은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 n번째 피보나치 수를 1,000,000으로 나눈 나머지를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        //피보나치 수를 나눌 수를 K라고 할 때, k = 10의 M 제곱 이면, 피사노 주기는 15*10의 (M-1)제곱이다.
        //즉, k가 10의 6승인 1,000,000이면 피사노 주기는 1,500,000이다.
        //1,500,000번째 까지의 피보나치 수를 1,000,000로 나눈 나머지 값들을 구하면 그 이후의 수는 계산할 필요가 없다
//        int K = 1_000_000;
//        int M = 0; //K 의 는 10의 몇 제곱?
//        while (K > 9) {
//            K /= 10;
//            M++;
//        }
//        피사노 주기
//        double pisano = 15 * Math.pow(10L, M);

        int pisano = 1_500_000;
        long[] arr = new long[pisano];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < pisano && i <= N; i++) {
            arr[i] = (arr[i - 2] + arr[i - 1]) % 1_000_000;
        }
        if (N >= pisano) {
            N %= pisano;
        }
        System.out.println(arr[(int) N]);

    } //END of Main Method


}


