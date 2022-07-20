package backjoon.gold2.p1918;
/*
https://www.acmicpc.net/problem/1918

수식은 일반적으로 3가지 표기법으로 표현할 수 있다. 연산자가 피연산자 가운데 위치하는 중위 표기법(일반적으로 우리가 쓰는 방법이다), 연산자가 피연산자 앞에 위치하는 전위 표기법(prefix notation), 연산자가 피연산자 뒤에 위치하는 후위 표기법(postfix notation)이 그것이다. 예를 들어 중위 표기법으로 표현된 a+b는 전위 표기법으로는 +ab이고, 후위 표기법으로는 ab+가 된다.

이 문제에서 우리가 다룰 표기법은 후위 표기법이다. 후위 표기법은 위에서 말한 법과 같이 연산자가 피연산자 뒤에 위치하는 방법이다. 이 방법의 장점은 다음과 같다. 우리가 흔히 쓰는 중위 표기식 같은 경우에는 덧셈과 곱셈의 우선순위에 차이가 있어 왼쪽부터 차례로 계산할 수 없지만 후위 표기식을 사용하면 순서를 적절히 조절하여 순서를 정해줄 수 있다. 또한 같은 방법으로 괄호 등도 필요 없게 된다. 예를 들어 a+b*c를 후위 표기식으로 바꾸면 abc*+가 된다.

중위 표기식을 후위 표기식으로 바꾸는 방법을 간단히 설명하면 이렇다. 우선 주어진 중위 표기식을 연산자의 우선순위에 따라 괄호로 묶어준다. 그런 다음에 괄호 안의 연산자를 괄호의 오른쪽으로 옮겨주면 된다.

예를 들어 a+b*c는 (a+(b*c))의 식과 같게 된다. 그 다음에 안에 있는 괄호의 연산자 *를 괄호 밖으로 꺼내게 되면 (a+bc*)가 된다. 마지막으로 또 +를 괄호의 오른쪽으로 고치면 abc*+가 되게 된다.

다른 예를 들어 그림으로 표현하면 A+B*C-D/E를 완전하게 괄호로 묶고 연산자를 이동시킬 장소를 표시하면 다음과 같이 된다.



결과: ABC*+DE/-

이러한 사실을 알고 중위 표기식이 주어졌을 때 후위 표기식으로 고치는 프로그램을 작성하시오

입력
첫째 줄에 중위 표기식이 주어진다. 단 이 수식의 피연산자는 알파벳 대문자로 이루어지며 수식에서 한 번씩만 등장한다. 그리고 -A+B와 같이 -가 가장 앞에 오거나 AB와 같이 *가 생략되는 등의 수식은 주어지지 않는다. 표기식은 알파벳 대문자와 +, -, *, /, (, )로만 이루어져 있으며, 길이는 100을 넘지 않는다.

출력
첫째 줄에 후위 표기식으로 바뀐 식을 출력하시오
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static final int BRACKET_ORDER = 0;
    static final int PLUS_MINUS_ORDER = 1;
    static final int MULTIPLE_DIVIDE_ORDER = 2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ori = br.readLine().toCharArray();
        System.out.println(changePostfix(ori));
    }

    private static String changePostfix(char[] ori) {
        StringBuilder sb = new StringBuilder();

        //연산자 스택
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < ori.length; i++) {
            char temp = ori[i];
            if (temp == '+' || temp == '-' || temp == '*' || temp == '/') {
                // 연산자를 만나면 스택 상단의 연산자와 우선순위 비교
                // 지금 넣으려는 연산자의 우선 순위가 더 크면 해당 연산자를 스택에 삽입
                // 작거나 같으면 스택 상단의 연산자를 변환 큐에 추가하고 다시 반복
                // stack peek 이 +, 이번이 * => stack 에 * 로 push (stack = +, *)
                // stack peek 이 *, 이번이 + => * pop 하고, +를 push
                while (!stack.isEmpty() && getOrder(temp) <= getOrder(stack.peek())) {
                    sb.append(stack.pop());
                }
                stack.push(temp);

            } else if (temp == '(') {
                // 왼쪽 괄호가 나오면 스택에 삽입
                stack.push(temp);

            } else if (temp == ')') {
                // 오른쪽 괄호가 나오면 스택에서 왼쪽 괄호가 나올때까지
                // 모든 연산자를 큐에 추가하고 pop. 왼쪽 괄호는 삭제
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();//왼쪽 괄호 pop
            } else { //피연산자
                sb.append(temp);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private static int getOrder(char c) {
        switch (c) {
            case '*':
            case '/':
                return MULTIPLE_DIVIDE_ORDER;
            case '+':
            case '-':
                return PLUS_MINUS_ORDER;
            case '(':
            case ')':
                return BRACKET_ORDER;
            default:
                return -1;
        }
    }
}


