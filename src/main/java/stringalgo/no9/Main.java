package stringalgo.no9;


/*
9. 숫자만 추출
설명

문자와 숫자가 섞여있는 문자열이 주어지면 그 중 숫자만 추출하여 그 순서대로 자연수를 만듭니다.

만약 “tge0a1h205er”에서 숫자만 추출하면 0, 1, 2, 0, 5이고 이것을 자연수를 만들면 1205이 됩니다.

추출하여 만들어지는 자연수는 100,000,000을 넘지 않습니다.


입력
첫 줄에 숫자가 섞인 문자열이 주어집니다. 문자열의 길이는 100을 넘지 않습니다.


출력
첫 줄에 자연수를 출력합니다.


예시 입력 1

g0en2T0s8eSoft
예시 출력 1

208
 */

import java.io.*;

public class Main {

    public int solution(String input) {

        //1번
//        input = input.replaceAll("[^0-9]", "");
//        String output = String.valueOf(Integer.parseInt(input));
//        return output;

        //2번
//        int answer = 0;
//        for (char c : input.toCharArray()) {
//            if (c >= 48 && c <= 57) {
//                answer = answer * 10 + (c - 48);
//            }
//        }
//        String output = String.valueOf(answer);
//        return output;

        //3번
        String answer = "";

        for (char c : input.toCharArray()) {
            if(Character.isDigit(c)){
                answer += c;
            }
        }

        return Integer.parseInt(answer);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(m.solution(str)));

        bw.flush();
    }
}
