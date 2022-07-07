package programers.level4.p42897;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/42897

문제 설명
도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.

image.png

각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.

각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.

제한사항
이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.
입출력 예
money	return
[1, 2, 3, 1]	4
 */

class Solution {
    public int solution(int[] money) {
        //첫 집을 털 경우
        int[] getFirst = new int[money.length];
        //두번째 집부커 털 경우
        int[] getSecond = new int[money.length];

        for (int i = 0; i < money.length; i++) {
            getFirst[i] = getSecond[i] = money[i];
        }

        getFirst[1] = -1;
        getFirst[2] += getFirst[0];

        getSecond[0] = -1;

        for (int i = 3; i < money.length; i++) {
            getFirst[i] += Math.max(getFirst[i - 2], getFirst[i - 3]);
            getSecond[i] += Math.max(getSecond[i - 2], getSecond[i - 3]);
        }
        int firstMax = Math.max(getFirst[money.length - 2], getFirst[money.length - 3]);
        int secondMax = Math.max(getSecond[money.length - 1], getFirst[getSecond.length - 2]);

        return Math.max(firstMax, secondMax);
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int[] money = {1, 2, 3, 1};

        int answer = s.solution(money);
        int expect = 4;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
