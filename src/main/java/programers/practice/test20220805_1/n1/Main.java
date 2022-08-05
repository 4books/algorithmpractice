package programers.practice.test20220805_1.n1;
/*

 */

import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();

        Map<Character, Integer> xMap = new HashMap<>();
        Map<Character, Integer> yMap = new HashMap<>();
        Map<Character, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());


        for (int i = 0; i < X.length(); i++) {
            char key = X.charAt(i);
            xMap.put(key, xMap.getOrDefault(key, 0) + 1);
        }

        for (int i = 0; i < Y.length(); i++) {
            char key = Y.charAt(i);
            yMap.put(key, yMap.getOrDefault(key, 0) + 1);
        }

        for (Character key : xMap.keySet()) {
            Integer xCnt = xMap.get(key);
            Integer yCnt = yMap.get(key);
            if(xCnt != null && xCnt > 0 && yCnt != null && yCnt > 0){
                treeMap.put(key, Math.min(xCnt, yCnt));
            }
        }

        if(treeMap.isEmpty()){
            answer.append("-1");
        } else if(treeMap.size() == 1 && treeMap.get('0') != null){
            answer.append("0");
        } else {

            for (Character key : treeMap.keySet()) {
                int count = treeMap.get(key);
                for (int i = 0; i < count; i++) {
                    answer.append(key);
                }
            }
        }

        return answer.toString();
    }

}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        String answer = "";
        answer  = s.solution("100", "2345");
        System.out.println("answer = " + answer);
        answer  = s.solution("100", "203045");
        System.out.println("answer = " + answer);
        answer  = s.solution("100", "123450");
        System.out.println("answer = " + answer);
        answer  = s.solution("12321", "42531");
        System.out.println("answer = " + answer);
        answer  = s.solution("5525", "1255");
        System.out.println("answer = " + answer);


//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
