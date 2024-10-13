package ajd.practice1;
/*

 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(int[][] v) {
        int[] answer = new int[2];

        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();
        for (int[] ints : v) {
            int x = ints[0];
            int y = ints[1];

            xMap.put(x, xMap.getOrDefault(x, 0) + 1);
            yMap.put(y, yMap.getOrDefault(y, 0) + 1);
        }

        for(int x : xMap.keySet()) {
            if(xMap.get(x) == 1){
                answer[0] = x;
                break;
            }
        }

        for(int y : yMap.keySet()) {
            if(yMap.get(y) == 1){
                answer[1] = y;
                break;
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

    }



}
