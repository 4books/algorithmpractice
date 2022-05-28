package arrayalgo.no10;

/*
10. 봉우리
설명

지도 정보가 N*N 격자판에 주어집니다. 각 격자에는 그 지역의 높이가 쓰여있습니다.

각 격자판의 숫자 중 자신의 상하좌우 숫자보다 큰 숫자는 봉우리 지역입니다. 봉우리 지역이 몇 개 있는 지 알아내는 프로그램을 작성하세요.

격자의 가장자리는 0으로 초기화 되었다고 가정한다.

만약 N=5 이고, 격자판의 숫자가 다음과 같다면 봉우리의 개수는 10개입니다.

https://cote.inflearn.com/contest/10/problem/02-10


입력
첫 줄에 자연수 N이 주어진다.(2<=N<=50)

두 번째 줄부터 N줄에 걸쳐 각 줄에 N개의 자연수가 주어진다. 각 자연수는 100을 넘지 않는다.


출력
봉우리의 개수를 출력하세요.


예시 입력 1

5
5 3 7 2 3
3 7 1 6 1
7 2 5 3 4
4 3 6 4 1
8 7 3 5 2
예시 출력 1

10
 */

import java.io.*;

public class Main {
    
    //1번
    public String solution(int n, int[][] grid) {
        int answer = 0;

        int left, right, up, down, center;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                center = grid[i][j];

                left = grid[i][j - 1];
                right = grid[i][j + 1];
                up = grid[i - 1][j];
                down = grid[i + 1][j];

                if (center > left
                        && center > right
                        && center > up
                        && center > down) {
                    answer++;
                }
            }
        }

        return String.valueOf(answer);
    }

    //2번
    public String solution2(int n, int[][] grid) {
        int answer = 0;
        int[] dx = {-1, 0, 1, 0}; //좌 상 우 하
        int[] dy = {0, 1, 0, -1}; //좌 상 우 하

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                boolean flag = true;
                int center = grid[i][j];
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    // x,y 좌표가 0이상 이여야 확인함
                    // center 가 체크 대상 중 하나라도 같거나 작으면 봉우리가 아님
                    if (x >= 0 && x < n && y >= 0 && y < n
                            && center <= grid[x][y]) { 
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    answer++;
                }

            }
        }


        return String.valueOf(answer);
    }


    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());


        //1번 내가 푼 방식 - 처음부터 외곽을 0으로 초기화 된 (size 가 2 큰 2차원 배열로 품)
//        int[][] grid = new int[n + 2][n + 2];
//        for (int i = 0; i < n; i++) {
//            String[] s = br.readLine().split(" ");
//            for (int j = 0; j < s.length; j++) {
//                grid[i + 1][j + 1] = Integer.parseInt(s[j]);
//            }
//        }
//        bw.write(m.solution(n, grid));



        //2번 그리드는 동일하게 가져감 - 탐색 좌표가 0 미만이면 체크를 하지 않는 방식
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(s[j]);
            }
        }
        bw.write(m.solution2(n, grid));


        bw.flush();
        bw.close();
    }
}
