package sortingalgo.no3;
/*
3. 삽입 정렬
설명

N개이 숫자가 입력되면 오름차순으로 정렬하여 출력하는 프로그램을 작성하세요.

정렬하는 방법은 삽입정렬입니다.


입력
첫 번째 줄에 자연수 N(1<=N<=100)이 주어집니다.

두 번째 줄에 N개의 자연수가 공백을 사이에 두고 입력됩니다. 각 자연수는 정수형 범위 안에 있습니다.


출력
오름차순으로 정렬된 수열을 출력합니다.


예시 입력 1

6
11 7 5 6 10 9
예시 출력 1

5 6 7 9 10 11
 */

import java.io.*;

public class Main {

    public static String solution(int n, int[] arr) {
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < n; i++) { //2번째 인덱스부터 돈다
            int tmp = arr[i]; //2번째 인덱스를 temp 로
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > tmp) { //arr[j]가 tmp 보다 크면 j + 1 지점에 넣어줌
                    arr[j + 1] = arr[j];
                } else { //작은 수를 만나면 break
                    break;
                }
            }
            //멈춘 지점 바로 다음에 tmp 를 넣어줌
            arr[j + 1] = tmp;

        }

        for (int i : arr) {
            answer.append(i).append(" ");
        }


        return answer.toString();
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }


        bw.write(solution(n, arr));
        bw.flush();
        bw.close();
    }
}
