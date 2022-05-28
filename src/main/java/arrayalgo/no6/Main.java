package arrayalgo.no6;

/*
6. 뒤집은 소수
설명

N개의 자연수가 입력되면 각 자연수를 뒤집은 후 그 뒤집은 수가 소수이면 그 소수를 출력하는 프로그램을 작성하세요.

예를 들어 32를 뒤집으면 23이고, 23은 소수이다. 그러면 23을 출력한다. 단 910를 뒤집으면 19로 숫자화 해야 한다.

첫 자리부터의 연속된 0은 무시한다.


입력
첫 줄에 자연수의 개수 N(3<=N<=100)이 주어지고, 그 다음 줄에 N개의 자연수가 주어진다.

각 자연수의 크기는 100,000를 넘지 않는다.


출력
첫 줄에 뒤집은 소수를 출력합니다. 출력순서는 입력된 순서대로 출력합니다.


예시 입력 1

9
32 55 62 20 250 370 200 30 100
예시 출력 1

23 2 73 2 3
 */

import java.io.*;

public class Main {

    public boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public String solution(int count, String[] input) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int tmp = Integer.parseInt(input[i]);
            //숫자 뒤집기
            //123이면
            //1번째 t = 3, res = 0 * 10 + 3 (res = 3)
            //2번째 t = 2, res = 3 * 10 + 2 (res = 32)
            //3번째 t = 1, res = 32 * 10 + 1 (res = 321)
            int res = 0;
            while (tmp > 0) {
                int t = tmp % 10;
                res = res * 10 + t;
                tmp /= 10;
            }

            //뒤집은 숫자가 소수인가?
            if (isPrime(res)) {
                answer.append(res).append(" ");
            }
        }

        return answer.toString();
    }


    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        String[] input = s.split(" ");

        bw.write(m.solution(n, input));
        bw.flush();
        bw.close();
    }
}
