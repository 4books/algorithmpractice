package stackqueuealgo.no2;
/*
2. 괄호문자제거
설명

입력된 문자열에서 소괄호 ( ) 사이에 존재하는 모든 문자를 제거하고 남은 문자만 출력하는 프로그램을 작성하세요.


입력
첫 줄에 문자열이 주어진다. 문자열의 길이는 100을 넘지 않는다.


출력
남은 문자만 출력한다.


예시 입력 1

(A(BC)D)EF(G(H)(IJ)K)LM(N)
예시 출력 1

EFLM
 */

import java.io.*;
import java.util.Stack;

public class Main {
    public static String solution(String s) {

        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        //1번
//        for (char c : s.toCharArray()) {
//
//            if (c == '(') {
//                stack.push(c);
//            } else if (c == ')') {
//                stack.pop();
//            } else if (stack.isEmpty()) {
//                answer.append(c);
//            }
//        }

        //2번
        for (char c : s.toCharArray()) {
            if(c == ')'){
                while(stack.pop() != '(');
            } else {
                stack.push(c);
            }
        }

        for (int i = 0; i < stack.size(); i++) {
            answer.append(stack.get(i));
        }

        return answer.toString();
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
