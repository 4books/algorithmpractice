package dfsbfsplusalgo.no4;

/*
중복순열 구하기
1부터 N까지 번호가 적힌 구슬이 있습니다. 이 중 중복을 허락하여 M번을 뽑아 일렬로 나열
하는 방법을 모두 출력합니다.
▣ 입력설명
첫 번째 줄에 자연수 N(3<=N<=10)과 M(2<=M<=N) 이 주어집니다.
▣ 출력설명
첫 번째 줄에 결과를 출력합니다.
출력순서는 사전순으로 오름차순으로 출력합니다.
▣ 입력예제 1
3 2
▣ 출력예제 1
1 1
1 2
1 3
2 1
2 2
2 3
3 1
3 2
3 3
 */

import java.io.*;

public class Main {

    public static int n = 0; //최대 자연수
    public static int max = 0;// 뽑는 최대 횟수
    public static StringBuilder answer = new StringBuilder();

    public static void dfs(int level, int[] output) {
        //level 깊이
        //n arr 총 size
        //output 결과를 담은 array
        if (level == max) { //level 과 max 가 같다면 결과 출력하고 가장 마지막 숫자는 0으로 초기화
            for (int i : output) {
                answer.append(i).append(" ");
            }
            answer.append("\n");
            //output[max - 1] = 0; //굳이 초기화 불필요
        } else {
            for (int i = 1; i <= n; i++) {
                output[level] = i;
                dfs(level + 1, output);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");

        n = Integer.parseInt(tmp[0]); //최대 자연수
        max = Integer.parseInt(tmp[1]); //뽑는 횟수

        int[] output = new int[max];

        dfs(0, output);

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }


}


