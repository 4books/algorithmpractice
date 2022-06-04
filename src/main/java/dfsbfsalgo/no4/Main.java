package dfsbfsalgo.no4;
/*
피보나치 수열
1) 피보나키 수열을 출력한다. 피보나치 수열이란 앞의 2개의 수를 합하여 다음 숫자가 되는
수열이다.
2) 입력은 피보나치 수열의 총 항의 수 이다. 만약 7이 입력되면 1 1 2 3 5 8 13을 출력하면
된다.
▣ 입력설명
첫 줄에 총 항수 N(3<=N<=45)이 입력된다.
▣ 출력설명
첫 줄에 피보나치 수열을 출력합니다.
▣ 입력예제 1
10
▣ 출력예제 1
1 1 2 3 5 8 13 21 34 55
 */

import java.io.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void dfsMyVer(int n, int[] arr) {
        if (arr.length == n + 2) {
            return;
        }
        arr[n + 2] = arr[n + 1] + arr[n];
        dfsMyVer(n + 1, arr);
    }

    public static int dfs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return dfs(n - 2) + dfs(n - 1);
        }
    }

    public static int dfs2(int n, int[] fibo) {
        if (fibo[n] > 0){
            return fibo[n];
        }
        if (n <= 2) {
            return fibo[n] = 1;
        } else {
            return fibo[n] = dfs2(n - 2, fibo) + dfs2(n - 1, fibo);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        LocalTime start = LocalTime.now();
        //1번
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        dfsMyVer(0, arr);

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }

        LocalTime end = LocalTime.now();
        sb.append("\n").append(ChronoUnit.MILLIS.between(start, end)).append("밀리초\n");

        //2번
        start = LocalTime.now();
        for (int i = 1; i <= n; i++) {
            sb.append(dfs(i)).append(" ");
        }
        end = LocalTime.now();
        sb.append("\n").append(ChronoUnit.MILLIS.between(start, end)).append("밀리초\n");

        //3번
        start = LocalTime.now();
        int[] fibo = new int[n + 1];
        dfs2(n, fibo);
        for (int i = 1; i < n + 1; i++) {
            sb.append(fibo[i]).append(" ");
        }
        end = LocalTime.now();
        sb.append("\n").append(ChronoUnit.MILLIS.between(start, end)).append("밀리초\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
