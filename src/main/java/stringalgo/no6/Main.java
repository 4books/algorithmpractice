package stringalgo.no6;

/*
6. 중복문자제거
설명

소문자로 된 한개의 문자열이 입력되면 중복된 문자를 제거하고 출력하는 프로그램을 작성하세요.

중복이 제거된 문자열의 각 문자는 원래 문자열의 순서를 유지합니다.


입력
첫 줄에 문자열이 입력됩니다. 문자열의 길이는 100을 넘지 않는다.


출력
첫 줄에 중복문자가 제거된 문자열을 출력합니다.


예시 입력 1

ksekkset
예시 출력 1

kset
 */

import java.io.*;

public class Main {
    public String solution(String input) {
        String answer = "";

        for (int i = 0; i < input.length(); i++) {
//            System.out.println("char : "
//                    + input.charAt(i)
//                    + ", indexOf : " + input.indexOf(input.charAt(i))
//                    + ", current index : " + i
//            );


            if (input.indexOf(input.charAt(i)) == i) {
                answer += input.charAt(i);
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(m.solution(input));

        bw.flush();
        bw.close();

    }
}
