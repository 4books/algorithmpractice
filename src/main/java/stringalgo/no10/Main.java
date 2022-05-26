package stringalgo.no10;

/*
10. 가장 짧은 문자거리
설명

한 개의 문자열 s와 문자 t가 주어지면 문자열 s의 각 문자가 문자 t와 떨어진 최소거리를 출력하는 프로그램을 작성하세요.


입력
첫 번째 줄에 문자열 s와 문자 t가 주어진다. 문자열과 문자는 소문자로만 주어집니다.

문자열의 길이는 100을 넘지 않는다.


출력
첫 번째 줄에 각 문자열 s의 각 문자가 문자 t와 떨어진 거리를 순서대로 출력한다.


예시 입력 1

teachermode e
예시 출력 1

1 0 1 2 1 0 1 2 2 1 0
 */
import java.io.*;

public class Main {

    public int[] solution(String input){

        int index = input.indexOf(" ");
        char target = input.substring(index + 1, input.length()).charAt(0);
        input = input.substring(0, index);

        int[] answer = new int[input.length()];

        int p = 100;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == target){
                p = 0;
                answer[i] = p;
            } else {
                p++;
                answer[i] = p;
            }
        }

        p = 100;
        for (int i = input.length() - 1; i >= 0 ; i--) {
            if(input.charAt(i) == target){
                p = 0;
                answer[i] = p;
            } else {
                p++;
                answer[i] = Math.min(answer[i], p);
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String anwser = "";
        for(int i : m.solution(input)){
            anwser += i + " ";
        }

        bw.write(anwser);
        bw.flush();

    }
}
