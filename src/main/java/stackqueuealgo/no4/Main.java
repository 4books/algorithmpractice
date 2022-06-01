package stackqueuealgo.no4;
/*
4. 후위식 연산(postfix)
설명

후위연산식이 주어지면 연산한 결과를 출력하는 프로그램을 작성하세요.

만약 3*(5+2)-9 을 후위연산식으로 표현하면 352+*9- 로 표현되며 그 결과는 12입니다.


입력
첫 줄에 후위연산식이 주어집니다. 연산식의 길이는 50을 넘지 않습니다.

식은 1~9의 숫자와 +, -, *, / 연산자로만 이루어진다.


출력
연산한 결과를 출력합니다.


예시 입력 1

352+*9-
예시 출력 1

12
 */

import java.io.*;
import java.util.Stack;

public class Main {

    public static String solution(String input) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c - 48);
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();

                if (c == '+') {
                    stack.push(num2 + num1);
                } else if (c == '-') {
                    stack.push(num2 - num1);
                } else if (c == '*') {
                    stack.push(num2 * num1);
                } else if (c == '/') {
                    stack.push(num2 / num1);
                }
            }
        }

        return String.valueOf(stack.get(0));
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();


        bw.write(solution(s));
        bw.flush();
        bw.close();
    }
}
