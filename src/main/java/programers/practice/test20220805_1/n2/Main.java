package programers.practice.test20220805_1.n2;
/*

 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int total = 0;
        Map<String, Integer> userMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            userMap.put(want[i], number[i]);
            total += number[i];
        }

        int start = 0;
        for (int end = 0; end < discount.length; end++) {

            if (userMap.containsKey(discount[end])) {
                userMap.put(discount[end], userMap.get(discount[end]) - 1);
                total--;
            } else {
                start++;
            }

            //원하는걸 다 샀다면
            if (total == 0) {
                boolean isJoin = true;
                for (String prd : userMap.keySet()) {
                    if (userMap.get(prd) > 0) {
                        isJoin = false;
                        break;
                    }
                }
                if (isJoin) {
                    answer++;
                }
            }
            //가입날 옮기기
            while (start <= end && end - start == 10 || total == 0) {
                if (userMap.containsKey(discount[start])) {
                    userMap.put(discount[start], userMap.get(discount[start]) + 1);
                    total++;
                }
                start++;
            }

        }
        return answer;
    }


}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer;

        answer = s.solution(new String[]{"banana", "apple", "rice", "pork", "pot"}, new int[]{3, 2, 2, 2, 1}, new String[]{
                "chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"
        });
        System.out.println("answer = " + answer);
        answer = s.solution(new String[]{"banana"}, new int[]{3, 2, 2, 2, 1}, new String[]{
                "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple",
        });
        System.out.println("answer = " + answer);

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
