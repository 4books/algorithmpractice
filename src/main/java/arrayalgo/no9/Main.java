package arrayalgo.no9;

/*
9. 격자판 최대합
설명

5*5 격자판에 아래롸 같이 숫자가 적혀있습니다.

https://cote.inflearn.com/contest/10/problem/02-09

N*N의 격자판이 주어지면 각 행의 합, 각 열의 합, 두 대각선의 합 중 가 장 큰 합을 출력합니다.


입력
첫 줄에 자연수 N이 주어진다.(2<=N<=50)

두 번째 줄부터 N줄에 걸쳐 각 줄에 N개의 자연수가 주어진다. 각 자연수는 100을 넘지 않는다.


출력
최대합을 출력합니다.


예시 입력 1

5
10 13 10 12 15
12 39 30 23 11
11 25 50 53 15
19 27 29 37 27
19 13 30 13 19
예시 출력 1

155

 */

import java.io.*;

public class Main {

    public String solution(int len, String[][] grid) {
        StringBuilder answer = new StringBuilder();
        int max = Integer.MIN_VALUE;
        int sum1, sum2;

        //각 ROW 와 col 의 값들의 합
        for (int i = 0; i < len; i++) {
            sum1 = sum2 = 0; //row sum 값, col sum 값
            for (int j = 0; j < len; j++) {
                sum1 += Integer.parseInt(grid[i][j]);
                sum2 += Integer.parseInt(grid[j][i]);
            }

            max = Math.max(max, sum1);
            max = Math.max(max, sum2);
        }

        //대각선
        sum1 = sum2 = 0;
        for (int i = 0; i < len; i++) {
            sum1 += Integer.parseInt(grid[i][i]);
            sum2 += Integer.parseInt(grid[i][len - 1 - i]);
        }
        max = Math.max(max, sum1);
        max = Math.max(max, sum2);

        return answer.append(max).toString();
    }


    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String[][] grid = new String[n][n];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().split(" ");
        }

        bw.write(m.solution(n, grid));
        bw.flush();
        bw.close();
    }
}
