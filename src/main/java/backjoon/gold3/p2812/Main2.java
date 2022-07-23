package backjoon.gold3.p2812;
/*
https://www.acmicpc.net/problem/2812

문제
N자리 숫자가 주어졌을 때, 여기서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ K < N ≤ 500,000)

둘째 줄에 N자리 숫자가 주어진다. 이 수는 0으로 시작하지 않는다.

출력
입력으로 주어진 숫자에서 K개를 지웠을 때 얻을 수 있는 가장 큰 수를 출력한다

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] num = new int[N];

        int numIdx = 0;
        for (char c : br.readLine().toCharArray()) {
            num[numIdx++] = Character.getNumericValue(c);
        }

        int total = N - K;
        int start = 0;

        while(start < num.length && ans.length() != total){
            //N 10 K 4일 경우 오른쪽의 5자리를 남기고 앞에 5자리를 비교
            //이 다음은 오른쪽의 4자리를 남김
            int left = K + ans.length() + 1;
            int max = 0;
            //41772 52841 에서 오른쪽 41772 중 가장 큰 숫자를 찾음
            //여기서 첫번째 7이기 때문에 7를 append 하고 나머지 417을 버린다.
            //725 2841
            for (int i = start; i < left; i++) {
                if (max < num[i]) {
                    max = num[i];
                    start = i + 1;
                }
            }
            ans.append(max);
        }
        System.out.println(ans);

    } //END of Main Method
}


