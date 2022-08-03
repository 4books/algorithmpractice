package leetcode.string.valid_anagram;
/*
https://leetcode.com/problems/valid-anagram/
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isAnagram(String s, String t) {

        Map<Character, Integer> map = new HashMap<>();

        //해당 문자의 갯수가 몇개인제 map 에 담는다.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            //키가 없다면 아나그램 아님
            if(!map.containsKey(c)){
                return false;
            }
            //한개씩 뺀다.
            Integer cnt = map.get(c) - 1;
            map.put(c, cnt);
        }

        //0이 아닌 것이 있다면 아나그램이 아님
        for (Character key : map.keySet()) {
            if(map.get(key) != 0){
                return false;
            }
        }

        return true;
    }

    //다른 사람 소스
    public boolean isAnagram2(String s, String t) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }
        for (int cnt : count) {
            if(cnt != 0){
                return false;
            }
        }
        return true;
    }

}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        boolean anagram = s.isAnagram("rat", "car");
        System.out.println("anagram = " + anagram);

        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
