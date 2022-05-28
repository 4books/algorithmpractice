package arrayalgo.no4;
/*
4. 피보나치 수열
설명

1) 피보나키 수열을 출력한다. 피보나치 수열이란 앞의 2개의 수를 합하여 다음 숫자가 되는 수열이다.

2) 입력은 피보나치 수열의 총 항의 수 이다. 만약 7이 입력되면 1 1 2 3 5 8 13을 출력하면 된다.


입력
첫 줄에 총 항수 N(3<=N<=45)이 입력된다.


출력
첫 줄에 피보나치 수열을 출력합니다.


예시 입력 1

10
예시 출력 1

1 1 2 3 5 8 13 21 34 55
 */
import java.io.*;

public class Main {

    public String solution(int count){
        StringBuilder answer = new StringBuilder();

        //1번
//        int num1 = 1;
//        int num2 = 1;
//        answer.append(num1).append(" ")
//                .append(num2).append(" ");
//        for (int i = 2; i < count; i++) {
//            int sum = num1 + num2;
//            answer.append(sum).append(" ");
//            num1 = num2;
//            num2 = sum;
//        }

        //2번 배열로
        int[] ints = new int[count];
        ints[0] = 1;
        ints[1] = 1;
        for (int i = 2; i < count; i++) {
            ints[i] = ints[i - 2] + ints[i - 1];
        }
        for (int anInt : ints) {
            answer.append(anInt).append(" ");
        }


        return answer.toString();
    }


    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());

        bw.write(m.solution(count));
        bw.flush();
        bw.close();
    }
}
