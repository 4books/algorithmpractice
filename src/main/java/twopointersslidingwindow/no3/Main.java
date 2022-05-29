package twopointersslidingwindow.no3;

/*
3. 최대 매출
설명

현수의 아빠는 제과점을 운영합니다. 현수 아빠는 현수에게 N일 동안의 매출기록을 주고 연속된 K일 동안의 최대 매출액이 얼마인지 구하라고 했습니다.

만약 N=10이고 10일 간의 매출기록이 아래와 같습니다. 이때 K=3이면

12 15[11 20 25]10 20 19 13 15

연속된 3일간의 최대 매출액은 11+20+25=56만원입니다.

여러분이 현수를 도와주세요.


입력
첫 줄에 N(5<=N<=100,000)과 K(2<=K<=N)가 주어집니다.

두 번째 줄에 N개의 숫자열이 주어집니다. 각 숫자는 500이하의 음이 아닌 정수입니다.


출력
첫 줄에 최대 매출액을 출력합니다.


예시 입력 1

10 3
12 15 11 20 25 10 20 19 13 15
예시 출력 1

56
 */
import java.io.*;

public class Main {

    public static int solution(int days, int con, int[] sale) {

        int answer = 0;

        //1번 이중 for 문 n2이 됨
        //첫번째 for 문 days + 1 - con; 에서 +1 안해주어도 통과가 됨
//        for (int i = 0; i < days + 1 - con; i++) {
//            int tmp = 0;
//            for (int j = i; j < i + con; j++) {
//                tmp += sale[j];
//            }
//            if (tmp > answer) {
//                answer = tmp;
//            }
//        }


        int sum = 0;
        for (int i = 0; i < con; i++) {
            sum += sale[i];
        }
        //첫날부터 con 까지 값

        answer = sum;
        for (int i = con; i < days; i++) {
            sum += sale[i];
            sum -= sale[i - con];
            if(sum > answer){
                answer = sum;
            }
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strings = br.readLine().split(" ");
        int days = Integer.parseInt(strings[0]);
        int con = Integer.parseInt(strings[1]);

        String[] bS = br.readLine().split(" ");
        int[] sale = new int[days];
        for (int i = 0; i < bS.length; i++) {
            sale[i] = Integer.parseInt(bS[i]);
        }


        bw.write(String.valueOf(solution(days, con, sale)));
        bw.flush();
        bw.close();
    }
}
