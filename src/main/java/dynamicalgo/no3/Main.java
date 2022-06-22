package dynamicalgo.no3;

/*
3. 최대 부분 증가수열
설명

N개의 자연수로 이루어진 수열이 주어졌을 때, 그 중에서 가장 길게 증가하는(작은 수에서 큰 수로) 원소들의 집합을 찾는 프로그램을 작성하라.

예를 들어, 원소가 2, 7, 5, 8, 6, 4, 7, 12, 3 이면 가장 길게 증가하도록 원소들을 차례대로 뽑아내면 2, 5, 6, 7, 12를 뽑아내어

길이가 5인 최대 부분 증가수열을 만들 수 있다.


입력
첫째 줄은 입력되는 데이터의 수 N(3≤N≤1,000, 자연수)를 의미하고,

둘째 줄은 N개의 입력데이터들이 주어진다.


출력
첫 번째 줄에 부분증가수열의 최대 길이를 출력한다.


예시 입력 1

8
5 3 7 8 6 2 9 4
예시 출력 1

4
 */

import java.io.*;

public class Main {

    static int[] arr; //항들을 저장한 배열

    public static String solution(int[] dy) {
        dy[0] = 1;
        int answer = dy[0];

        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i] && dy[j] > max) {
                    max = dy[j];
                }
            }
            dy[i] = max + 1;
            answer = Math.max(answer, dy[i]);
        }

        return String.valueOf(answer);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String[] tmp = br.readLine().split(" ");

        arr = new int[n];//항들을 저장한 배열
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tmp[i]);
        }

        int[] dy = new int[n];//최대 길이를 저장해놓는 배열

        bw.write(solution(dy));
        bw.flush();
        bw.close();
    }
}


