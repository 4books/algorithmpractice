package programers.level2.p86971;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/86971

n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 2 이상 100 이하인 자연수입니다.
wires는 길이가 n-1인 정수형 2차원 배열입니다.
wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
1 ≤ v1 < v2 ≤ n 입니다.
전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
 */

class Solution {


    static int[] unf;

    int find(int v) {
        if (v == unf[v]) {
            //집합 번호와 값이 같다면 집합 번호 값 리턴
            return unf[v];
        } else {
            //집합 번호가 다르다면 집합 번호를 같은 것을 찾은 후 리턴
            unf[v] = find(unf[v]);
            return unf[v];
        }
    }

    void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

//        집합의 번호가 다르면 fa 집합과 fb 집합을 하나로 묶는다
        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        //i 끊는 전선
        for (int i = 0; i < n - 1; i++) {
            //초기화
            unf = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                unf[j] = j;
            }

            //j 전선 연결
            for (int j = 0; j < n - 1; j++) {
                if (i == j) { //끊긴 전선
                    continue;
                }

                int n1 = wires[j][0];
                int n2 = wires[j][1];
                union(n1, n2);
            }

            //집합 번호 통일
            for (int v = 1; v < unf.length; v++) {
                find(v); //그냥 찾으면 find method 를 통해 집합번호가 통일 된다.
            }

            int e1 = unf[1];
            int cnt1 = 1;
            int cnt2 = 0;
            for (int j = 2; j <= n; j++) {
                if(unf[j] == e1){
                    cnt1++;
                } else {
                    cnt2++;
                }
            }

            answer = Math.min(answer, Math.abs(cnt1 - cnt2));
        }

        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;

        answer = s.solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}});
        expect = 3;
        System.out.println("result = " + answer + " " + "expect = " + expect);

        answer = s.solution(6, new int[][]{{1, 4}, {6, 3}, {2, 5}, {5, 1}, {5, 3}});
        expect = 2;
        System.out.println("result = " + answer + " " + "expect = " + expect);

    }
}
