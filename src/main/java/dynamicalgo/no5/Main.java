package dynamicalgo.no5;

/*
5. 동전교환(냅색 알고리즘)
설명

다음과 같이 여러 단위의 동전들이 주어져 있을때 거스름돈을 가장 적은 수의 동전으로 교환해주려면 어떻게 주면 되는가?

각 단위의 동전은 무한정 쓸 수 있다.


입력
첫 번째 줄에는 동전의 종류개수 N(1<=N<=50)이 주어진다.

두 번째 줄에는 N개의 동전의 종류가 주어지고, 그 다음줄에 거슬러 줄 금액 M(1<=M<=500)이 주어진다.

각 동전의 종류는 100원을 넘지 않는다.


출력
첫 번째 줄에 거슬러 줄 동전의 최소개수를 출력한다.


예시 입력 1

3
1 2 5
15
예시 출력 1

3
힌트

출력설명 : 5 5 5 동전 3개로 거슬러 줄 수 있다.
 */



import java.io.*;
import java.util.Arrays;

public class Main {

    static int n; //동전 종류 수
    
    static int[] coin; //동전 종류
    static int[] dy; //다이나믹 테이블 i 라는 금액을 만드는 드는 최소 동전 갯수
    static int change; //거슬러 줄 금액

    public static String solution() {

        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= change; j++) {
                dy[j] = Math.min(dy[j], dy[j - coin[i]] + 1);
            }
        }
        
        return String.valueOf(dy[change]);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        coin = new int[n];

        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(tmp[i]);
        }

        change = Integer.parseInt(br.readLine());
        dy = new int[change + 1];
        Arrays.fill(dy, Integer.MAX_VALUE);
        dy[0] = 0;

        bw.write(solution());
        bw.flush();
        bw.close();
    }


}


