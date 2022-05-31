package hashalgo.no4;
/*
4. 모든 아나그램 찾기
설명

S문자열에서 T문자열과 아나그램이 되는 S의 부분문자열의 개수를 구하는 프로그램을 작성하세요.

아나그램 판별시 대소문자가 구분됩니다. 부분문자열은 연속된 문자열이어야 합니다.


입력
첫 줄에 첫 번째 S문자열이 입력되고, 두 번째 줄에 T문자열이 입력됩니다.

S문자열의 길이는 10,000을 넘지 않으며, T문자열은 S문자열보다 길이가 작거나 같습니다.


출력
S단어에 T문자열과 아나그램이 되는 부분문자열의 개수를 출력합니다.


예시 입력 1

bacaAacba
abc
예시 출력 1

3
힌트


 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static int solution(String t, String s) {
        int answer = 0;
        Map<Character, Integer> sMap = new HashMap<>();

        //S String
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }
        int sLen = s.length() - 1;

        //T String - S String 하나 전 까지만 설정
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < sLen; i++) {
            Character c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        for (int i = sLen; i < t.length(); i++) {
            Character c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
            if (sMap.equals(tMap)) {
                answer++;
            }
            //맨 왼쪽 index
            Character x = t.charAt(left);
            tMap.put(x, tMap.get(x) - 1);
            if(tMap.get(x) == 0){
                tMap.remove(x);
            }
            left++;
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String t = br.readLine();
        String s = br.readLine();

        bw.write(String.valueOf(solution(t, s)));
        bw.flush();
        bw.close();
    }
}
