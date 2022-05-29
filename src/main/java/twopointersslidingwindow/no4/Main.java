package twopointersslidingwindow.no4;
/*
4. 연속 부분수열
설명

N개의 수로 이루어진 수열이 주어집니다.

이 수열에서 연속부분수열의 합이 특정숫자 M이 되는 경우가 몇 번 있는지 구하는 프로그램을 작성하세요.

만약 N=8, M=6이고 수열이 다음과 같다면

1 2 1 3 1 1 1 2

합이 6이 되는 연속부분수열은 {2, 1, 3}, {1, 3, 1, 1}, {3, 1, 1, 1}로 총 3가지입니다.


입력
첫째 줄에 N(1≤N≤100,000), M(1≤M≤100,000,000)이 주어진다.

수열의 원소값은 1,000을 넘지 않는 자연수이다.


출력
첫째 줄에 경우의 수를 출력한다.


예시 입력 1

8 6
1 2 1 3 1 1 1 2
예시 출력 1

3
 */

import java.io.*;

public class Main {
    public static int solution(int n, int m, int[] arr) {
        int answer = 0;
        int p1 = 0, p2 = 0; //p1 시작지점, p2 종료지점
        int sum = 0;

        while (p2 < n) {
            sum += arr[p2++];

            if (sum == m) {
                answer++;
            }

            while(sum >= m) {
                sum -= arr[p1++];
                if (sum == m) {
                    answer++;
                }
            }
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);//N 개 배열
        int m = Integer.parseInt(strings[1]);//M 특정 합의 수

        String[] arrTmp = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrTmp[i]);
        }


        bw.write(String.valueOf(solution(n, m, arr)));
        bw.flush();
        bw.close();
    }
}
