package backjoon.gold1.p1450;
/*
냅색문제

문제
세준이는 N개의 물건을 가지고 있고, 최대 C만큼의 무게를 넣을 수 있는 가방을 하나 가지고 있다.

N개의 물건을 가방에 넣는 방법의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 C가 주어진다. N은 30보다 작거나 같은 자연수, C는 109보다 작거나 같은 음이 아닌 정수이다. 둘째 줄에 물건의 무게가 주어진다. 무게도 109보다 작거나 같은 자연수이다.
*/



import java.util.*;
import java.io.*;

class Main {
    static int N, C;

    public static int binarySearch(ArrayList<Integer> sum, int target) {
        int left = 0, right = sum.size() - 1, mid, answer = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (sum.get(mid) <= target) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }


    public static void dfs(int idx, int sum, ArrayList<Integer> weight, ArrayList<Integer> answer) {
        // 3. 탈출 조건
        if (sum > C) {
            return;
        }
        if (idx == weight.size()) {
            answer.add(sum);
            return;
        }

        // 1. 물건을 넣는 경우
        dfs(idx + 1, sum + weight.get(idx), weight, answer);
        // 2. 물건을 안 넣는 경우
        dfs(idx + 1, sum, weight, answer);
    }

    public static void main(String[] args) throws IOException {
        // 0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ArrayList<Integer> weight1 = new ArrayList<>();
        ArrayList<Integer> weight2 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (i < N / 2) {
                weight1.add(Integer.parseInt((st.nextToken())));
            } else {
                weight2.add(Integer.parseInt((st.nextToken())));
            }
        }

        // 1. DFS로 sum1 sum2 만들기
        ArrayList<Integer> sum1 = new ArrayList<>();
        ArrayList<Integer> sum2 = new ArrayList<>();

        dfs(0, 0, weight1, sum1);
        dfs(0, 0, weight2, sum2);

        // 2. sort 및 binary search
        Collections.sort(sum2);
        int answer = 0;
        for (int i = 0; i < sum1.size(); i++) {
            int searchValue = C - sum1.get(i);
            answer += binarySearch(sum2, searchValue) + 1;
        }
        bw.write(String.valueOf(answer));
        bw.flush();

        bw.close();
        br.close();
    }
}
