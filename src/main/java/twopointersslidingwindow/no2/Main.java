package twopointersslidingwindow.no2;
/*
2. 공통원소 구하기
설명

A, B 두 개의 집합이 주어지면 두 집합의 공통 원소를 추출하여 오름차순으로 출력하는 프로그램을 작성하세요.


입력
첫 번째 줄에 집합 A의 크기 N(1<=N<=30,000)이 주어집니다.

두 번째 줄에 N개의 원소가 주어집니다. 원소가 중복되어 주어지지 않습니다.

세 번째 줄에 집합 B의 크기 M(1<=M<=30,000)이 주어집니다.

네 번째 줄에 M개의 원소가 주어집니다. 원소가 중복되어 주어지지 않습니다.

각 집합의 원소는 1,000,000,000이하의 자연수입니다.


출력
두 집합의 공통원소를 오름차순 정렬하여 출력합니다.


예시 입력 1

5
1 3 9 5 2
5
3 2 5 7 8
예시 출력 1

2 3 5
 */

import java.io.*;
import java.util.*;

public class Main {

    public static List<Integer> solution(int n, int[] a, int m, int[] b) {

        /*
          p1
        a [1 2 3 5 9]
          p2
        b [2 3 5 7 8]

        둘다 0을 탐색했을 때, 작은 pointer 를 옮긴다.
        큰 pointer 를 옮기면 절대 같은 것을 찾을 수 없다.
         */
        List<Integer> answer = new ArrayList<>();
        int p1 = 0, p2 = 0;

        Arrays.sort(a);
        Arrays.sort(b);

        while (p1 < n && p2 < m) {
            int v1 = a[p1];
            int v2 = b[p2];
            if (v1 == v2) {
                answer.add(v1);
                p1++;
                p2++;
            } else if (v1 > v2) {
                p2++;
            } else {//v1 < v2
                p1++;
            }
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

        List<Integer> answer = solution(n, a, m, b);

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
