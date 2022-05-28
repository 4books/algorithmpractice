package arrayalgo.no5.no3;

/*
3. 가위 바위 보
설명

A, B 두 사람이 가위바위보 게임을 합니다. 총 N번의 게임을 하여 A가 이기면 A를 출력하고, B가 이기면 B를 출력합니다. 비길 경우에는 D를 출력합니다.

가위, 바위, 보의 정보는 1:가위, 2:바위, 3:보로 정하겠습니다.

예를 들어 N=5이면

https://cote.inflearn.com/contest/10/problem/02-03

두 사람의 각 회의 가위, 바위, 보 정보가 주어지면 각 회를 누가 이겼는지 출력하는 프로그램을 작성하세요.


입력
첫 번째 줄에 게임 횟수인 자연수 N(1<=N<=100)이 주어집니다.

두 번째 줄에는 A가 낸 가위, 바위, 보 정보가 N개 주어집니다.

세 번째 줄에는 B가 낸 가위, 바위, 보 정보가 N개 주어집니다.


출력
각 줄에 각 회의 승자를 출력합니다. 비겼을 경우는 D를 출력합니다.


예시 입력 1

5
2 3 3 1 3
1 1 2 2 3
예시 출력 1

A
B
A
B
D
 */

import java.io.*;

public class Main {


    public String solution(int count, String[] p1, String[] p2) {

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < count; i++) {
            int a = Integer.parseInt(p1[i]);
            int b = Integer.parseInt(p2[i]);

            if (a == b) {
                answer.append("D\n");
            } else if ((a == 1 && b == 3)
                    || (a == 2 && b == 1)
                    || (a == 3 && b == 2)
            ) {
                answer.append("A\n");
            } else {
                answer.append("B\n");
            }
        }

        return answer.toString();
    }


    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());
        String player1 = br.readLine();
        String player2 = br.readLine();

        String[] p1 = player1.split(" ");
        String[] p2 = player2.split(" ");

        bw.write(m.solution(count, p1, p2));

        bw.flush();
        bw.close();
    }
}
