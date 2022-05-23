package stringalgo.no3;

/*
3. 문장 속 단어
설명

한 개의 문장이 주어지면 그 문장 속에서 가장 긴 단어를 출력하는 프로그램을 작성하세요.

문장속의 각 단어는 공백으로 구분됩니다.


입력
첫 줄에 길이가 100을 넘지 않는 한 개의 문장이 주어집니다. 문장은 영어 알파벳으로만 구성되어 있습니다.


출력
첫 줄에 가장 긴 단어를 출력한다. 가장 길이가 긴 단어가 여러개일 경우 문장속에서 가장 앞쪽에 위치한

단어를 답으로 합니다.


예시 입력 1

it is time to study
예시 출력 1

study

 */

import java.io.*;

public class Main {

    public String solution(String str){

        String answer = "";

        //Split 활용
//        String[] array = str.split(" ");
//        int m = Integer.MIN_VALUE;
//
//        for (String s : array) {
//            int len = s.length();
//            if(len > m){
//                answer = s;
//                m = len;
//            }
//        }

        //indexOf 활용
        //it is time to study
        int m = Integer.MIN_VALUE, pos;
        while((pos = str.indexOf(' ')) != -1){
            String tmp = str.substring(0, pos); //it
            int len = tmp.length();             //2
            if(len > m){                         
                m = len;                        //m = 2
                answer = tmp;                   //it
            }
            str = str.substring(pos + 1); //is time to study
        }
        if(str.length() > m){ //가장 마지막 문장이 제일 클경우
            answer = str;
        }
        return answer;
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
