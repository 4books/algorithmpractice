package backjoon.gold5.p1107;


import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int solution(int target, boolean[] breaks) {
        int minCnt = Math.abs(target - 100);//+ - 만 눌러 target 까지 가는 경우
        //채널은 50만까지 주어지니 해당 자리수 중 최대값까지 검사
        for (int i = 0; i <= 999_999; i++) {
            //숫자 버튼을 누르는 횟수 체크
            int count = checkPress(i, breaks);

            //숫자 버튼을 누르는 경우가 있다면
            if (count > 0) {
                int press = Math.abs(target - i);//+ - 누르는 횟수
                minCnt = Math.min(minCnt, count + press);
            }
        }

        return minCnt;
    }

    private static int checkPress(int n, boolean[] breaks) {
        //0일때 예외
        if (n == 0) {
            if (breaks[0]) {
                return 0;
            }
            return 1;
        }

        int count = 0;
        //while 문을 돌며 한자리 씩 해당 숫자가 고장나지 않았을 경우 count 를 추가
        while (n > 0) {
            //해당 숫자가 고장 났을 경우
            if (breaks[n % 10]) {
                return 0;
            }
            count++;
            n /= 10;
        }

        return count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[] breaks = new boolean[10];

        if (m != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                breaks[Integer.parseInt(st.nextToken())] = true;
            }
        }
        if (target == 100) {
            System.out.println(0);
        } else {
            System.out.println(solution(target, breaks));
        }
    }
}


