package dfsbfsplusalgo.no5;
/*
5. 동전교환
설명

다음과 같이 여러 단위의 동전들이 주어져 있을때 거스름돈을 가장 적은 수의 동전으로 교환해주려면 어떻게 주면 되는가?

각 단위의 동전은 무한정 쓸 수 있다.


입력
첫 번째 줄에는 동전의 종류개수 N(1<=N<=12)이 주어진다. 두 번째 줄에는 N개의 동전의 종류가 주어지고,

그 다음줄에 거슬러 줄 금액 M(1<=M<=500)이 주어진다.각 동전의 종류는 100원을 넘지 않는다.


출력
첫 번째 줄에 거슬러 줄 동전의 최소개수를 출력한다.


예시 입력 1

3
1 2 5
15
예시 출력 1

3
힌트

출력 설명 : 5 5 5 동전 3개로 거슬러 줄 수 있다.
 */

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static int min = Integer.MAX_VALUE; //최소 동전 갯수
    public static int n = 0; //동전 종류 수 : 3
    public static Integer[] arr; //동전의 종류를 담은 배열
    public static int change = 0; //거슬러줄 금액 : 15

    //1번
    //count 현재 동전 갯수, sum 현재 거스름돈 총합
    public static void dfs(int count, int sum) {
        if (sum == change) { //현재 거스름돈과 거슬러줄 금액이 동일하면 count 와 min 비교
            min = Math.min(min, count);
        } else if (count > min || sum > change){ //최소 갯수를 넘거나 이미 거스름돈을 초과 했으면 return
            return;
        } else {
            for (Integer i : arr) {
                dfs(count + 1, sum + i);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine()); // 동전의 종류 수
        String[] tmp = br.readLine().split(" ");
        arr = new Integer[n]; //동전 1원, 2원, 5원,... 등
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tmp[i]);
        }
        //동전이 큰 금액일 수록 거슬러 줄 동전 갯수가 줄어듬
        Arrays.sort(arr, Collections.reverseOrder());
        change = Integer.parseInt(br.readLine()); //거슬러줄 금액

        dfs(0, 0);

        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }
}


