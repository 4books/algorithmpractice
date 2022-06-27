package programers.p72411;
/*

 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    static class Solution {

        int maxLevel = 0;
        List<String> list = new ArrayList<>();
        public String[] solution(String[] orders, int[] course) {

            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < orders.length; i++) {
                char[] chars = orders[i].toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    Character order = chars[j];
                    map.put(order, map.getOrDefault(order, 0) + 1);
                }
            }

            for (int i = 0; i < course.length; i++) {
                maxLevel = course[i];
                dfs(0, "");
            }

            return null;
        }

        public void dfs(int level, String value){
            if (level == maxLevel) {

            } else {

            }
        }

    }

    public static void main(String[] args) {

        Solution s = new Solution();

        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        String[] answer = s.solution(orders, course);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}
