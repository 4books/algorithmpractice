package dfsbfsalgo.no1;
/*
재귀함수
자연수 N이 입력되면 재귀함수를 이용하여 1부터 N까지를 출력하는 프로그램을 작성하세요.
▣ 입력설명
첫 번째 줄은 정수 N(3<=N<=10)이 입력된다.
▣ 출력설명
첫째 줄에 출력한다.
▣ 입력예제 1
3
▣ 출력예제 1
1 2 3
 */

import java.io.*;

public class Main {

    //1번
//    public static StringBuilder recursive(int n, StringBuilder answer) {
//        if(n > 0){
//            answer.append(n).append(" ");
//            return recursive(--n, answer);
//        }
//        return answer;
//    }
//
//    public static String solution(int n){
//        StringBuilder answer = recursive(n, new StringBuilder());
//        return answer.reverse().toString().trim();
//    }

    //2번
    public static void dfs(int n) {
        if (n == 0) {
            return;
        } else {
            dfs(n - 1);
            System.out.print(n + " ");
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        dfs(n);
//        bw.write(solution(n));
//        bw.flush();
//        bw.close();
    }

}
