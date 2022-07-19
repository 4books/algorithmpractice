package backjoon.gold4.p16637;
/*
https://www.acmicpc.net/problem/16637

문제
길이가 N인 수식이 있다. 수식은 0보다 크거나 같고, 9보다 작거나 같은 정수와 연산자(+, -, ×)로 이루어져 있다. 연산자 우선순위는 모두 동일하기 때문에, 수식을 계산할 때는 왼쪽에서부터 순서대로 계산해야 한다. 예를 들어, 3+8×7-9×2의 결과는 136이다.

수식에 괄호를 추가하면, 괄호 안에 들어있는 식은 먼저 계산해야 한다. 단, 괄호 안에는 연산자가 하나만 들어 있어야 한다. 예를 들어, 3+8×7-9×2에 괄호를 3+(8×7)-(9×2)와 같이 추가했으면, 식의 결과는 41이 된다. 하지만, 중첩된 괄호는 사용할 수 없다. 즉, 3+((8×7)-9)×2, 3+((8×7)-(9×2))은 모두 괄호 안에 괄호가 있기 때문에, 올바른 식이 아니다.

수식이 주어졌을 때, 괄호를 적절히 추가해 만들 수 있는 식의 결과의 최댓값을 구하는 프로그램을 작성하시오. 추가하는 괄호 개수의 제한은 없으며, 추가하지 않아도 된다.

입력
첫째 줄에 수식의 길이 N(1 ≤ N ≤ 19)가 주어진다. 둘째 줄에는 수식이 주어진다. 수식에 포함된 정수는 모두 0보다 크거나 같고, 9보다 작거나 같다. 문자열은 정수로 시작하고, 연산자와 정수가 번갈아가면서 나온다. 연산자는 +, -, * 중 하나이다. 여기서 *는 곱하기 연산을 나타내는 × 연산이다. 항상 올바른 수식만 주어지기 때문에, N은 홀수이다.

출력
첫째 줄에 괄호를 적절히 추가해서 얻을 수 있는 결과의 최댓값을 출력한다. 정답은 231보다 작고, -231보다 크다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int dpSize = N / 2 - 1;
        String exps = br.readLine();
        char[] expArr = exps.toCharArray();

        //Integer 계산 값
        //int[2] index lastIndex
        Map<Integer, int[]>[] dp = new Map[dpSize];
        for (int i = 0; i < dpSize; i++) {
            dp[i] = new TreeMap<>();
        }

        //숫자 2개로만 이루어진 것
        dp[0] = dpZeroCreate(expArr);

        //TODO BFS 로 모든 수식 가능 조건 DP 에 저장하기
        //숫자 3, 4,...개로 이루어진 것
        //
        for (int i = 1; i < dpSize; i++) {
            //

        }


        System.out.println();

    }

    private static Map<Integer, int[]> dpZeroCreate(char[] expArr) {

        Map<Integer, int[]> dp = new TreeMap<>();

        //숫자 두개로만 계산
        for (int i = 1; i < expArr.length; i += 2) {
            int num1 = Character.getNumericValue(expArr[i - 1]);
            char opr = expArr[i];
            int num2 = Character.getNumericValue(expArr[i + 1]);

            dp.put(calculate(num1, opr, num2), new int[]{i - 1, i + 1});
        }
        return dp;
    }

    private static int calculate(int num1, char opr, int num2) {
        if (opr == '+') {
            return num1 + num2;
        } else if (opr == '-') {
            return num1 - num2;
        }
        return num1 * num2;
    }


}


