package dfsbfsplusalgo.no7;

/*
7. 조합의 경우수(메모이제이션)
설명
조합이란 서로 다른 n개중에 r개를 선택하는 경우의 수를 의미합니다. (순서 상관 없음)
2C0 = 1
1C1 = 1

Image1.jpg로 계산합니다.

하지만 여러분은 이 공식을 쓰지않고 다음 공식을 사용하여 재귀를 이용해 조합수를 구해주는 프로그램을 작성하세요.

Image1.jpg


입력
첫째 줄에 자연수 n(3<=n<=33)과 r(0<=r<=n)이 입력됩니다.


출력
첫째 줄에 조합수를 출력합니다.


예시 입력 1

5 3
예시 출력 1

10
예시 입력 2

33 19
예시 출력 2

818809200
 */

import java.io.*;

public class Main {

    public static int answer = 0;

    //1번
    public static void dfs(int n, int r) {
        if (n > 0 && r == 0) {
            answer++;
        } else if (n == r) {
            answer++;
        } else {
            dfs(n - 1, r - 1);
            dfs(n - 1, r);
        }
    }

    public static int dfs2(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        } else {
            return dfs2(n - 1, r - 1) + dfs2(n - 1, r);
        }
    }

    //메모이제이션 - 이중 배열로 이미 해당 위치에 값을 넣은 뒤 꺼내오는 방법
    public static int[][] dy;

    public static int dfs3(int n, int r) {
        if(dy[n][r] > 0){
            return dy[n][r];
        }
        if (n == r || r == 0) {
            return 1;
        } else {
            return dy[n][r] = dfs3(n - 1, r - 1) + dfs3(n - 1, r);
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]); // 총 자연수 : 5
        int r = Integer.parseInt(tmp[1]); // 뽑는 갯수 : 3

//        dfs(n, r);
//        answer = dfs2(n, r);

        //메모이제이션
        dy = new int[n + 1][n + 1];
        answer = dfs3(n, r);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}


