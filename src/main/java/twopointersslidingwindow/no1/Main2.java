package twopointersslidingwindow.no1;

/*
1. 두 배열 합치기
설명

오름차순으로 정렬이 된 두 배열이 주어지면 두 배열을 오름차순으로 합쳐 출력하는 프로그램을 작성하세요.


입력
첫 번째 줄에 첫 번째 배열의 크기 N(1<=N<=100)이 주어집니다.

두 번째 줄에 N개의 배열 원소가 오름차순으로 주어집니다.

세 번째 줄에 두 번째 배열의 크기 M(1<=M<=100)이 주어집니다.

네 번째 줄에 M개의 배열 원소가 오름차순으로 주어집니다.

각 리스트의 원소는 int형 변수의 크기를 넘지 않습니다.


출력
오름차순으로 정렬된 배열을 출력합니다.


예시 입력 1

3
1 3 5
5
2 3 6 7 9
예시 출력 1

1 2 3 3 5 6 7 9
 */

import java.io.*;

public class Main2 {

    public static int[] solution(int n, int[] a, int m, int[] b) {
        int length = n + m;
        int[] answer = new int[length];

        int p1 = 0, p2 = 0, i = 0;
        while (p1 < n && p2 < m) {
            if (a[p1] < b[p2]) {
                answer[i++] = a[p1++];
            } else {
                answer[i++] = b[p2++];
            }
        }
        while (p1 < n) {
            answer[i++] = a[p1++];
        }

        while (p2 < m) {
            answer[i++] = b[p2++];
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] aS = br.readLine().split(" ");

        int[] a = new int[n];
        for (int i = 0; i < aS.length; i++) {
            a[i] = Integer.parseInt(aS[i]);
        }

        int m = Integer.parseInt(br.readLine());
        String[] bS = br.readLine().split(" ");
        int[] b = new int[m];
        for (int i = 0; i < bS.length; i++) {
            b[i] = Integer.parseInt(bS[i]);
        }

        int[] answer = solution(n, a, m, b);

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
