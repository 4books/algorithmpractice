package programers.level2.p42746;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/42746

가장 큰 수
문제 설명
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

제한 사항
numbers의 길이는 1 이상 100,000 이하입니다.
numbers의 원소는 0 이상 1,000 이하입니다.
정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
입출력 예
numbers	return
[6, 10, 2]	"6210"
[3, 30, 34, 5, 9]	"9534330"
 */

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String[] strArr = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            strArr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strArr, new Comparator<String>() {
            
            //내림차순
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        //앞자리가 0인 것은 그냥 0 반환
        if (strArr[0].equals("0"))
            return "0";

        return String.join("", strArr);
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int[] numbers = {6, 10, 2};

        String answer = s.solution(numbers);
        String expect = "6210";
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);

        numbers = new int[]{3, 30, 34, 5, 9};

        answer = s.solution(numbers);
        expect = "9534330";
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
