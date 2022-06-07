package dfsbfsplusalgo.no1;
/*
합이 같은 부분집합(DFS : 아마존 인터뷰)
N개의 원소로 구성된 자연수 집합이 주어지면, 이 집합을 두 개의 부분집합으로 나누었을 때
두 부분집합의 원소의 합이 서로 같은 경우가 존재하면 “YES"를 출력하고, 그렇지 않으면
”NO"를 출력하는 프로그램을 작성하세요.
둘로 나뉘는 두 부분집합은 서로소 집합이며, 두 부분집합을 합하면 입력으로 주어진 원래의
집합이 되어 합니다.
예를 들어 {1, 3, 5, 6, 7, 10}이 입력되면 {1, 3, 5, 7} = {6, 10} 으로 두 부분집합의 합이
16으로 같은 경우가 존재하는 것을 알 수 있다.
▣ 입력설명
첫 번째 줄에 자연수 N(1<=N<=10)이 주어집니다.
두 번째 줄에 집합의 원소 N개가 주어진다. 각 원소는 중복되지 않는다.
▣ 출력설명
첫 번째 줄에 “YES" 또는 ”NO"를 출력한다.
▣ 입력예제 1
6
1 3 5 6 7 10
▣ 출력예제 1
YES
 */

import java.io.*;
import java.util.Arrays;

public class Main {

    public static int total = 0;
    public static String answer = "NO";

    public static void dfs(int level, int sum, int n, int[] arr) {

        if(answer.equals("YES")){
            return;
        }
        
        if(sum > (total / 2)){ //sum 은 절대 total 의 반 이상이 될 수 없으므로
            return;
        }

        if (level == n) { /* 배열 크기와 동일해지면 부분집합 완성 */
            if((total - sum) == sum) { //부분 집합이 total 의 절반 이라면?
                answer = "YES";
            }
        } else {
            dfs(level + 1, sum + arr[level], n, arr);
            dfs(level + 1, sum, n, arr);
        }
    }

    public static String solution(int n, int[] arr) {
        total = Arrays.stream(arr).sum();
        dfs(0, 0, n, arr);

        return answer;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String[] tmp = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tmp[i]);
        }

        bw.write(solution(n, arr));
        bw.flush();
        bw.close();
    }

}
