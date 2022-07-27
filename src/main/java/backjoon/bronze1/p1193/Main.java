package backjoon.bronze1.p1193;
/*
https://www.acmicpc.net/problem/1193

문제
무한히 큰 배열에 다음과 같이 분수들이 적혀있다.

1/1	1/2	1/3	1/4	1/5	…
2/1	2/2	2/3	2/4	…	…
3/1	3/2	3/3	…	…	…
4/1	4/2	…	…	…	…
5/1	…	…	…	…	…
…	…	…	…	…	…
이와 같이 나열된 분수들을 1/1 → 1/2 → 2/1 → 3/1 → 2/2 → … 과 같은 지그재그 순서로 차례대로 1번, 2번, 3번, 4번, 5번, … 분수라고 하자.

X가 주어졌을 때, X번째 분수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 X(1 ≤ X ≤ 10,000,000)가 주어진다.

출력
첫째 줄에 분수를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());

        if(X == 1){
            System.out.println("1/1");
            System.exit(0);
        }

        int f = 0; //수열 값
        int number = 0; //분자 또는 분모
        while (number + f < X) {
            f += number++;
        }

        int son, mother, target = X - f - 1;
        if(number % 2 == 0){
            //짝수 이면 분자 증가
            son = 1;
            mother = number;

            son += target;
            mother -= target;
        } else {
            //홀수 이면 분자 감소
            son = number;
            mother = 1;

            son -= target;
            mother += target;
        }

        System.out.println(son + "/" + mother);

    } //END of Main Method

}


