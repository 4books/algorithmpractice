package backjoon.sliver2.p1541;
/*
https://www.acmicpc.net/problem/1541
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    //당연히 최솟값을 만들기 위해서는 최대한 '큰 수'를 빼주어야 한다. 즉, 덧셈(+)으로 이루어진 부분을 먼저 계산한 뒤 빼주는 것이다.
    //황내권이는 수학이 부족해
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = Integer.MAX_VALUE;

        //빼기 기준으로 나눔
        StringTokenizer subToken = new StringTokenizer(br.readLine(), "-");

        while (subToken.hasMoreTokens()) {
            int tmp = 0;

            //덧셈 단위로 분리
            StringTokenizer addToken = new StringTokenizer(subToken.nextToken(), "+");

            //10+20+30 진행
            while (addToken.hasMoreTokens()) {
                tmp += Integer.parseInt(addToken.nextToken());
            }

            if(answer == Integer.MAX_VALUE){
                answer = tmp;
            } else {
                answer -= tmp;
            }
        }

        System.out.println(answer);

    } //END of Main Method


}


