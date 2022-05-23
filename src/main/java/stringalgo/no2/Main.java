package stringalgo.no2;

/*

2. 대소문자 변환
설명

대문자와 소문자가 같이 존재하는 문자열을 입력받아 대문자는 소문자로 소문자는 대문자로 변환하여 출력하는 프로그램을 작성하세요.


입력
첫 줄에 문자열이 입력된다. 문자열의 길이는 100을 넘지 않습니다.

문자열은 영어 알파벳으로만 구성되어 있습니다.


출력
첫 줄에 대문자는 소문자로, 소문자는 대문자로 변환된 문자열을 출력합니다.


예시 입력 1

StuDY
예시 출력 1

sTUdy

 */


import java.io.*;

public class Main {

    public String solution(String str) {

        char[] upperAndLower = new char[str.toCharArray().length];
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            //65 - 90 대문자
            //97 - 122 소문자
            char c = charArray[i];
            if (c >= 65 && c <= 90) {
                upperAndLower[i] = (char) (c + 32);
            } else {
                upperAndLower[i] = (char) (c - 32);
            }
        }

        return String.valueOf(upperAndLower);
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
