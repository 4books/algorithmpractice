package programers.level2.p12951;
/*

 */


import java.util.Locale;

class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String[] letter = s.toLowerCase().split("");

        boolean nextFlag = true;
        for (int i = 0; i < letter.length; i++) {
            if(nextFlag){
                answer.append(letter[i].toUpperCase());
            } else {
                answer.append(letter[i]);
            }
            nextFlag = letter[i].equals(" ");
        }

        return answer.toString();
    }

}

public class Main {

    Solution s = new Solution();


}
