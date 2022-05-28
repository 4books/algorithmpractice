package arrayalgo.no5;

/*
5. 소수(에라토스테네스 체)
설명

자연수 N이 입력되면 1부터 N까지의 소수의 개수를 출력하는 프로그램을 작성하세요.

만약 20이 입력되면 1부터 20까지의 소수는 2, 3, 5, 7, 11, 13, 17, 19로 총 8개입니다.


입력
첫 줄에 자연수의 개수 N(2<=N<=200,000)이 주어집니다.


출력
첫 줄에 소수의 개수를 출력합니다.


예시 입력 1

20
예시 출력 1

8
 */

import java.io.*;

public class Main {

    public int solution(int count) {
        int answer = 0;

        boolean[] bls = new boolean[count + 1];

        //index 를 소수를 확인할 숫자로 본다.
        for (int i = 2; i < count; i++) {
            //현재 i 가 false 이면 소수
            if (!bls[i]) {
                answer++;
                //i 의 배수들을 전부 true 로 바꿔 소수가 아님을 표시
                for (int j = i; j < count; j += i) {
                    bls[j] = true;
                }
            }
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(m.solution(count)));
        bw.flush();
        bw.close();
    }


}
