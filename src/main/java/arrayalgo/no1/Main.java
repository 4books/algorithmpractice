package arrayalgo.no1;
/*
1. 큰 수 출력하기
설명

N개의 정수를 입력받아, 자신의 바로 앞 수보다 큰 수만 출력하는 프로그램을 작성하세요.

(첫 번째 수는 무조건 출력한다)


입력
첫 줄에 자연수 N(1<=N<=100)이 주어지고, 그 다음 줄에 N개의 정수가 입력된다.


출력
자신의 바로 앞 수보다 큰 수만 한 줄로 출력한다.


예시 입력 1

6
7 3 9 5 6 12
예시 출력 1

7 9 6 12
 */
import java.io.*;

public class Main {

    public String solution(int len, String str) {

        String[] s = str.split(" ");

        StringBuilder answer = new StringBuilder();
        answer.append(s[0]).append(" ");

        for (int i = 1; i < s.length; i++) {
            int s1 = Integer.parseInt(s[i - 1]);
            int s2 = Integer.parseInt(s[i]);
            if(s1 < s2){
                answer.append(s[i]).append(" ");
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());
        String str = br.readLine();


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(m.solution(len, str));

        bw.flush();
        bw.close();

    }
}
