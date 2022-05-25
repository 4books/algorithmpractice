package stringalgo.no7;



/*
7. 회문 문자열
설명

앞에서 읽을 때나 뒤에서 읽을 때나 같은 문자열을 회문 문자열이라고 합니다.

문자열이 입력되면 해당 문자열이 회문 문자열이면 "YES", 회문 문자열이 아니면 “NO"를 출력하는 프로그램을 작성하세요.

단 회문을 검사할 때 대소문자를 구분하지 않습니다.


입력
첫 줄에 길이 100을 넘지 않는 공백이 없는 문자열이 주어집니다.


출력
첫 번째 줄에 회문 문자열인지의 결과를 YES 또는 NO로 출력합니다.


예시 입력 1

gooG
예시 출력 1

YES
 */

import java.io.*;

public class Main {

    public String solution(String str) {

        //1번
        str = str.toLowerCase();
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if(str.charAt(i) != str.charAt(len - 1 - i)){
                return "NO";
            }
        }
        return "YES";

        //2번
//        String tmp = new StringBuilder(str).reverse().toString();
//        if (str.equalsIgnoreCase(tmp)) {
//            return "YES";
//        } else {
//            return "NO";
//        }

        //3번
//        int left = 0, right = str.length() - 1;
//        str = str.toLowerCase();
//        while(left < right){
//            if(str.charAt(left) != str.charAt(right)){
//                return "NO";
//            }
//            left++;
//            right--;
//        }
//        return "YES";

    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(m.solution(str));

        bw.flush();
        bw.close();
    }
}
