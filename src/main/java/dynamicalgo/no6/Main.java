package dynamicalgo.no6;

/*
6. 최대점수 구하기(냅색 알고리즘)
설명

이번 정보올림피아드대회에서 좋은 성적을 내기 위하여 현수는 선생님이 주신 N개의 문제를 풀려고 합니다.

각 문제는 그것을 풀었을 때 얻는 점수와 푸는데 걸리는 시간이 주어지게 됩니다.

제한시간 M안에 N개의 문제 중 최대점수를 얻을 수 있도록 해야 합니다.

(해당문제는 해당시간이 걸리면 푸는 걸로 간주한다, 한 유형당 한개만 풀 수 있습니다.)


입력
첫 번째 줄에 문제의 개수N(1<=N<=50)과 제한 시간 M(10<=M<=300)이 주어집니다.

두 번째 줄부터 N줄에 걸쳐 문제를 풀었을 때의 점수와 푸는데 걸리는 시간이 주어집니다.


출력
첫 번째 줄에 제한 시간안에 얻을 수 있는 최대 점수를 출력합니다.


예시 입력 1

5 20
10 5
25 12
15 8
6 3
7 4
예시 출력 1

41
 */


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);//문제 갯수
        int m = Integer.parseInt(tmp[1]);//제한 시간

        int[] dy = new int[m + 1];//제한 시간 배열 i 값이 시간이다.
        for (int i = 0; i < n; i++) {
            tmp = br.readLine().split(" ");
            int ps = Integer.parseInt(tmp[0]); //풀었을때 점수
            int pt = Integer.parseInt(tmp[1]); //풀기 위한 시간

            //중복이 가능할때는 시작부터..
            //지금 처럼 중복이 불가 할땐 뒤에서 부터..
            for (int j = m; j >= pt; j--) {
                dy[j] = Math.max(dy[j], dy[j - pt] + ps);
            }
        }
        bw.write(String.valueOf(dy[m]));

        bw.flush();
        bw.close();
    }


}


