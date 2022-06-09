package dfsbfsplusalgo.no9;

/*
조합 구하기
1부터 N까지 번호가 적힌 구슬이 있습니다. 이 중 M개를 뽑는 방법의 수를 출력하는 프로그
램을 작성하세요.
▣ 입력설명
첫 번째 줄에 자연수 N(3<=N<=10)과 M(2<=M<=N) 이 주어집니다.
▣ 출력설명
첫 번째 줄에 결과를 출력합니다.
출력순서는 사전순으로 오름차순으로 출력합니다.
▣ 입력예제 1
4 2
▣ 출력예제 1
1 2
1 3
1 4
2 3
2 4
3 4
 */

import java.io.*;

public class Main {

    static StringBuilder answer = new StringBuilder();

    static int n = 0; // ~N 까지 자연수
    static int m = 0; // 뽑는 횟수
    static int[] output; //답이 담길 배열

    static void dfs(int level, int sum) {
        if (level == m) {
            for (int i : output) {
                answer.append(i).append(" ");
            }
            answer.append("\n");
        } else {
            for (int i = sum; i <= n; i++) {
                output[level] = i;
                dfs(level + 1, i + 1);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);

        output = new int[m];

        dfs(0, 1);

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }


}


