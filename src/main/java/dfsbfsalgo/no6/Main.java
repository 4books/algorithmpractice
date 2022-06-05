package dfsbfsalgo.no6;
/*
부분집합 구하기(DFS)
자연수 N이 주어지면 1부터 N까지의 원소를 갖는 집합의 부분집합을 모두 출력하는 프로그램
을 작성하세요.
▣ 입력설명
첫 번째 줄에 자연수 N(1<=N<=10)이 주어집니다.
▣ 출력설명
첫 번째 줄부터 각 줄에 하나씩 부분집합을 아래와 출력예제와 같은 순서로 출력한다.
단 공집합은 출력하지 않습니다.
▣ 입력예제 1
3
▣ 출력예제 1
1 2 3
1 2
1 3
1
2 3
2
3
 */

import java.io.*;

public class Main {

    public static void dfs(int l, int n, int[] arr, StringBuilder answer) {
        if(l != n + 1){
            arr[l] = 1;
            dfs(l + 1, n, arr, answer);
            arr[l] = 0;
            dfs(l + 1, n, arr, answer);
        } else { //L 이 입력된 자연수를 초과할 때 스탑 하고 현재 arr 를 뒤져서 값을 출력
            StringBuilder tmp = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if(arr[i] == 1){ //1인 것만 가져온다.
                    tmp.append(i).append(" ");
                }
            }
            if(tmp.length() > 0){ //0이면 공집합 출력하지 않음
                answer.append(tmp).append("\n");
            }
        }

    }

    public static String solution(int n, int[] arr) {
        StringBuilder answer = new StringBuilder();
        dfs(1, n, arr, answer);

        return answer.toString();
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];//0번 제외

        bw.write(solution(n, arr));
        bw.flush();
        bw.close();
    }

}
