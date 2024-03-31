package programers.level2.p42839;
/*
한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
 */

import java.util.HashSet;

class Solution {
    HashSet<Integer> numberSet = new HashSet<Integer>();

    public void recursive(String comb, String others){
        //1. 현재 조합을 set에 추가한다.
        if(!comb.isEmpty()){
            numberSet.add(Integer.valueOf(comb));
        }

        //2. 남은 숫자 중 한개를 더 해 새로운 조합을 만든다.
        for (int i = 0; i < others.length(); i++) {
            recursive(comb + others.charAt(i), others.substring(0, i) + others.substring(i + 1));
        }
    }
    public int solution(String numbers) {
        //1. 모든 조합의 숫자를 만든다.
        recursive("", numbers);

        //2. 소수의 개수만 센다.
        int answer = 0;
        for (Integer num : numberSet) {
            if(isPrime(num)){
                answer++;
            }
        }
        //3. 소수의 개수만 반환한다.
        return answer;
    }

    private boolean isPrime(Integer num) {
        if (num < 2) {
            return false;
        }

        int limit = (int)Math.sqrt(num);
        for (int i = 2; i <= limit; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

    }



}
