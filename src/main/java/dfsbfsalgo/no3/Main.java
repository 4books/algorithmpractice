package dfsbfsalgo.no3;
/*
팩토리얼
자연수 N이 입력되면 N!를 구하는 프로그램을 작성하세요.
예를 들어 5! = 5*4*3*2*1=120입니다.
▣ 입력설명
첫 번째 줄에 자연수 N(1<=N<=100)이 주어집니다.
▣ 출력설명
첫 번째 줄에 N팩토리얼 값을 출력합니다.
▣ 입력예제 1
5
▣ 출력예제 1
120
 */
import java.io.*;

public class Main {

    public static int dfsMyVer(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * dfsMyVer(n - 1);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        //1번
        StringBuilder sb = new StringBuilder();
        sb.append(dfsMyVer(n)).append("\n");

        //2번 - while
        int tmp = n;
        int answer = 1;
        while (tmp > 1) {
            answer *= tmp--;
        }
        sb.append(answer).append("\n");

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

}
