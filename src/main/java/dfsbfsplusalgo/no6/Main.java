package dfsbfsplusalgo.no6;

/*
순열 구하기
10이하의 N개의 자연수가 주어지면 이 중 M개를 뽑아 일렬로 나열하는 방법을 모두 출력합
니다.
▣ 입력설명
첫 번째 줄에 자연수 N(3<=N<=10)과 M(2<=M<=N) 이 주어집니다.
두 번째 줄에 N개의 자연수가 오름차순으로 주어집니다.
▣ 출력설명
첫 번째 줄에 결과를 출력합니다.
출력순서는 사전순으로 오름차순으로 출력합니다.
▣ 입력예제 1
3 2
3 6 9
▣ 출력예제 1
3 6
3 9
6 3
6 9
9 3
9 6
 */

import java.io.*;

public class Main {

    public static int n = 0; //자연수 갯수 : 3
    public static int m = 0; //뽑는 갯수 : 2
    public static int[] arr; //주어진 자연수
    public static int[] output; //뽑은 수가 들어있는 배열
    public static boolean[] checks; //이미 뽑힌 수 인지 확인
    public static StringBuilder answer = new StringBuilder();

    public static void dfs(int level) {

        if (level == m) {
            for (int i = 0; i < m; i++) {
                answer.append(output[i]).append(" ");
            }
            answer.append("\n");
        } else {
            for (int i = 0; i < n; i++) {
                if (!checks[i]) {
                    checks[i] = true;
                    output[level] = arr[i];
                    dfs(level + 1);
                    checks[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        arr = new int[n];

        tmp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tmp[i]);
        }
        output = new int[m];
        checks = new boolean[n];

        dfs(0);

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }


}


