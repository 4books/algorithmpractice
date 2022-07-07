package programers.level3.p42895;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/42895

문제 설명
아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

제한사항
N은 1 이상 9 이하입니다.
number는 1 이상 32,000 이하입니다.
수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
최솟값이 8보다 크면 -1을 return 합니다.
입출력 예
N	number	return
5	12	4
2	11	3
입출력 예 설명
예제 #1
문제에 나온 예와 같습니다.

예제 #2
11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.

출처

※ 공지 - 2020년 9월 3일 테스트케이스가 추가되었습니다.
 */

import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) {//같으면 바로 리턴
            return 1;
        }

        List<Set<Integer>> setList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            setList.add(new HashSet<>());
        }
        setList.get(1).add(N); //하나만 쓰는 경우는 N 밖에 없음

        for (int i = 2; i < 9; i++) {
            //계산 후 값을 가져와 저장할 set
            //2부터 차례대로
            Set<Integer> currSet = setList.get(i);

            //계산 진행
            for (int j = 1; j <= i; j++) {
                Set<Integer> sourceSet = setList.get(j);
                Set<Integer> targetSet = setList.get(i - j);
                for (Integer sourceNum : sourceSet) {
                    for (Integer targetNum : targetSet) {
                        //반대로도 진행해야 된다.
                        //5 * (5 + 5) = 50 과 5 + 5 * 5 = 30 은 다르다
                        doCalculate(currSet, sourceNum, targetNum);
                        doCalculate(currSet, targetNum, sourceNum);
                    }
                }
            }

            //순수 숫자만 넣는 경우 추가한 경우
            //예로 i = 3 이고 555
            String strN = String.valueOf(N);
            currSet.add(Integer.parseInt(strN.repeat(i)));

            if(currSet.contains(number)){
                return i;
            }
        }

        //최소값이 8보다 크면 -1 리턴
        return -1;
    }

    private void doCalculate(Set<Integer> currSet, Integer sourceNum, Integer targetNum) {
        currSet.add(sourceNum + targetNum);
        currSet.add(sourceNum - targetNum);
        currSet.add(sourceNum * targetNum);

        if(targetNum != 0) {
            currSet.add(sourceNum / targetNum);
        }
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int n = 5;
        int number = 12;

        int answer = s.solution(n, number);
        int expect = 4;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);

        n = 2;
        number = 11;

        answer = s.solution(n, number);
        expect = 3;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
