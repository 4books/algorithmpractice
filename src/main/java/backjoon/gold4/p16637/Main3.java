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

public class Main3 {

    static final int BRACKET_ORDER = 0;
    static final int PLUS_MINUS_ORDER = 1;
    static final int MULTIPLE_ORDER = 2;
    static int max = Integer.MIN_VALUE;
    static Map<String, Integer> dp;
    static Queue<String> expQueue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] ori = br.readLine().toCharArray();

        //숫자 갯수
        int numCnt = N / 2;
        //연산자 갯수
        int oprCnt = numCnt - 1;

        //문자 사이 괄호를 넣기 위해
        char[] expArr = new char[N * 2 + 1];
        int index = 1;
        for (int i = 0; i < N; i++) {
            expArr[index] = ori[i];
            index += 2;
        }

        //dp
        dp = new HashMap<>();

        //괄호쌍 갯수
        int bracketCnt = (oprCnt / 2) + (oprCnt % 2);

        //괄호 추가
        for (int i = 0; i < bracketCnt; i++) {
            //TODO 괄호 어떻게 넣지
        }
        System.out.println(max);
    }

    private static void dfs(int cnt, int bracketCnt, char[] expArr, int index){
        //TODO
        //종료 조건 : 모든 괄호 사용
        if(cnt == bracketCnt){
            //후위 표현식으로 변환
            //수식 queue
            expQueue = changePostfix(expArr);

            //후위 표현식 계산 및 최대 값
            max = Math.max(max, calculatePostFix(dp, expQueue));
        }
        
    }

    private static Queue<String> changePostfix(char[] expArr) {
        //return 수식 큐
        Queue<String> queue = new LinkedList<>();

        //연산자 스택
        Stack<Character> oprStack = new Stack<>();

        for (int i = 0; i < expArr.length; i++) {
            char temp = expArr[i];
            if (temp != 0) {
                if (temp == '+' || temp == '-' || temp == '*') {
                    // 연산자를 만나면 스택 상단의 연산자와 우선순위 비교
                    // 지금 넣으려는 연산자의 우선 순위가 더 크면 해당 연산자를 스택에 삽입
                    // 작거나 같으면 스택 상단의 연산자를 변환 큐에 추가하고 다시 반복
                    // stack peek 이 +, 이번이 * => stack 에 * 로 push (stack = +, *)
                    // stack peek 이 *, 이번이 + => * pop 하고, +를 push
                    while (!oprStack.isEmpty() && getOrder(temp) <= getOrder(oprStack.peek())) {
                        queue.offer(String.valueOf(oprStack.pop()));
                    }
                    oprStack.push(temp);

                } else if (temp == '(') {
                    // 왼쪽 괄호가 나오면 스택에 삽입
                    oprStack.push(temp);

                } else if (temp == ')') {
                    // 오른쪽 괄호가 나오면 스택에서 왼쪽 괄호가 나올때까지
                    // 모든 연산자를 큐에 추가하고 pop. 왼쪽 괄호는 삭제
                    while (!oprStack.isEmpty() && oprStack.peek() != '(') {
                        queue.offer(String.valueOf(oprStack.pop()));
                    }
                    oprStack.pop();//왼쪽 괄호 pop
                } else { //숫자
                    queue.offer(String.valueOf(temp));
                }
            }
        }
        while (!oprStack.isEmpty()) {
            queue.offer(String.valueOf(oprStack.pop()));
        }

        return queue;
    }

    private static int calculatePostFix(Map<String, Integer> dp, Queue<String> expQueue) {
        Stack<Integer> expStack = new Stack<>();

        while (!expQueue.isEmpty()) {
            String temp = expQueue.poll();

            if (temp.chars().allMatch(Character::isDigit)) {
                expStack.push(Integer.parseInt(temp));
            } else {
                int num1 = expStack.pop();
                int num2 = expStack.pop();

                //dp key
                String key = num1 + temp + num2;

                int result;
                if (dp.get(key) != null) {
                    result = dp.get(key);
                } else {
                    result = cal(num1, num2, temp);
                    dp.put(key, result);
                    if (!temp.equals("-")) {
                        key = num2 + temp + num1;
                        dp.put(key, result);
                    }
                }
                expStack.push(result);
            }
        }
        return expStack.pop();
    }

    private static int getOrder(char c) {
        if (c == '*') {
            return MULTIPLE_ORDER;
        } else if (c == '+' || c == '-') {
            return PLUS_MINUS_ORDER;
        }
        return BRACKET_ORDER;
    }

    private static int cal(int num1, int num2, String opr) {
        if (opr.equals("+")) {
            return num1 + num2;
        } else if (opr.equals("-")) {
            return num1 - num2;
        }
        return num1 * num2;
    }
}


