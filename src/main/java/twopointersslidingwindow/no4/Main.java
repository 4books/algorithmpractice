package twopointersslidingwindow.no4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int N = Integer.parseInt(strings[0]);//N 개 배열
        int M = Integer.parseInt(strings[1]);//M 특정 합의 수

        String[] arrTmp = br.readLine().split(" ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(arrTmp[i]);
        }

        //p1 시작점
        //p2 종료점
        int answer = 0, p1 = 0, p2 = 0, sum = 0;

        //p2 를 옮기면서 sum 이 M 과 같은지 확인한다.
        while (p2 < N) {
            sum += arr[p2++];

            //Sum 이 M 값이 됐다면 ans 추가
            if (sum == M) {
                answer++;
            }

            //M 보다 크다면 값 축소
            while (sum >= M) {
                //p1 증가
                sum -= arr[p1++];
                if (sum == M) {
                    answer++;
                }
            }
        }

        System.out.println(answer);

    } //END of Main Method


}


