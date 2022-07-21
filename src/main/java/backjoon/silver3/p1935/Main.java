package backjoon.silver3.p1935;
/*
https://www.acmicpc.net/problem/1935

문제
후위 표기식과 각 피연산자에 대응하는 값들이 주어져 있을 때, 그 식을 계산하는 프로그램을 작성하시오.

입력
첫째 줄에 피연산자의 개수(1 ≤ N ≤ 26) 가 주어진다. 그리고 둘째 줄에는 후위 표기식이 주어진다. (여기서 피연산자는 A~Z의 영대문자이며, A부터 순서대로 N개의 영대문자만이 사용되며, 길이는 100을 넘지 않는다) 그리고 셋째 줄부터 N+2번째 줄까지는 각 피연산자에 대응하는 값이 주어진다. 3번째 줄에는 A에 해당하는 값, 4번째 줄에는 B에 해당하는값 , 5번째 줄에는 C ...이 주어진다, 그리고 피연산자에 대응 하는 값은 100보다 작거나 같은 자연수이다.

후위 표기식을 앞에서부터 계산했을 때, 식의 결과와 중간 결과가 -20억보다 크거나 같고, 20억보다 작거나 같은 입력만 주어진다.

출력
계산 결과를 소숫점 둘째 자리까지 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] exp = br.readLine().toCharArray();

        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < exp.length; i++) {
            queue.offer(exp[i]);
        }

        Map<Character, Double> replaceMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            replaceMap.put(alphabet.charAt(i), Double.parseDouble(br.readLine()));
        }

        Stack<Double> stack = new Stack<>();

        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (c >= 65 && c <= 90) {
                stack.push(replaceMap.get(c));
            } else {
                double num2 = stack.pop();
                double num1 = stack.pop();
                double result = cal(num1, c, num2);
                stack.push(result);
            }
        }

        System.out.println(String.format("%.2f", stack.pop()));
    }

    private static double cal(double num1, char c, double num2) {
        double r = 0;
        switch (c) {
            case '+':
                r = num1 + num2;
                break;
            case '-':
                r = num1 - num2;
                break;
            case '*':
                r = num1 * num2;
                break;
            default:
                if (num2 != 0) {
                    r = num1 / num2;
                }
                break;
        }
        return r;
    }
}


