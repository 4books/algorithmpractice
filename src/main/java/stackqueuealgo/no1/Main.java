package stackqueuealgo.no1;
/*
1. 올바른 괄호
설명

괄호가 입력되면 올바른 괄호이면 “YES", 올바르지 않으면 ”NO"를 출력합니다.

(())() 이것은 괄호의 쌍이 올바르게 위치하는 거지만, (()()))은 올바른 괄호가 아니다.


입력
첫 번째 줄에 괄호 문자열이 입력됩니다. 문자열의 최대 길이는 30이다.


출력
첫 번째 줄에 YES, NO를 출력한다.


예시 입력 1

(()(()))(()
예시 출력 1

NO
 */

import java.io.*;
import java.util.Stack;

public class Main {
    public static String solution(String input) {
        String answer = "YES";

        //1번 - Stack 을 활용하지 못함
//        Stack<Character> leftStack = new Stack<>();
//        Stack<Character> rightStack = new Stack<>();
//
//        for (int i = 0; i < input.length(); i++) {
//            Character c = input.charAt(i);
//            if ((i == 0 && c != '(') || (i == input.length() - 1 && c != ')')) {
//                answer = "NO";
//                break;
//            }
//            if (c == '(') {
//                leftStack.push(c);
//            } else {
//                rightStack.push(c);
//            }
//        }
//        if (leftStack.size() != rightStack.size()) {
//            answer = "NO";
//        }

        //2번
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if(stack.isEmpty()){
                    answer = "NO";
                    break;
                }
                stack.pop();
            }
        }

        if (!stack.isEmpty()) {
            answer = "NO";
        }


        return answer;
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
