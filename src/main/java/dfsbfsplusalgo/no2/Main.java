package dfsbfsplusalgo.no2;

/*
2. 바둑이 승차(DFS)
설명

철수는 그의 바둑이들을 데리고 시장에 가려고 한다. 그런데 그의 트럭은 C킬로그램 넘게 태울수가 없다.

철수는 C를 넘지 않으면서 그의 바둑이들을 가장 무겁게 태우고 싶다.

N마리의 바둑이와 각 바둑이의 무게 W가 주어지면, 철수가 트럭에 태울 수 있는 가장 무거운 무게를 구하는 프로그램을 작성하세요.


입력
첫 번째 줄에 자연수 C(1<=C<=100,000,000)와 N(1<=N<=30)이 주어집니다.

둘째 줄부터 N마리 바둑이의 무게가 주어진다.


출력
첫 번째 줄에 가장 무거운 무게를 출력한다.


예시 입력 1

259 5
81
58
42
33
61
예시 출력 1

242
 */

import java.io.*;

public class Main {

    public static int max = Integer.MIN_VALUE;
    public static int total = Integer.MIN_VALUE;

    public static void dfs(int level, int n, int sum, int[] arr) {
        if (sum > total) { //트럭 무게를 초과하면 리턴
            return;
        }

        //부분 집합 완성
        if (level == n) {
            //넘어온 sum 이 현재 max 보다 크면 max 교체
            max = Math.max(max, sum);
        }else{
            dfs(level + 1, n, sum + arr[level], arr); //다음 바둑이를 태운다고 가정
            dfs(level + 1, n, sum, arr); //다음 바둑이를 태우지 않는다고 가정
        }
    }

    public static String solution(int n, int[] arr) {
        dfs(0, n, 0, arr);
        return String.valueOf(max);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");

        total = Integer.parseInt(tmp[0]); //수용 가능 무게
        int n = Integer.parseInt(tmp[1]); //바둑이 수

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        bw.write(solution(n, arr));
        bw.flush();
        bw.close();
    }
}
