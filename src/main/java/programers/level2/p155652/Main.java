package programers.level2.p155652;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/155652?language=java


 */

class Solution {
    public String solution(String s, String skip, int index) {
        char[] words = s.toCharArray();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < index; j++) {

                do {
                    words[i]++;
                    if (words[i] > 'z') {
                        words[i] -= 26;
                    }
                } while (skip.contains(String.valueOf(words[i])));
            }
        }
        return String.valueOf(words);
    }

}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        String solution = s.solution("aukks", "wbqd", 5);
        System.out.println("solution = " + solution);
    }

}
