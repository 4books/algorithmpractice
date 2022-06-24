package programers.p77484;

import java.util.HashMap;
import java.util.Map;

public class Solution {



    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};

        Map<Integer, Boolean> map = new HashMap<>();
        int zeroCount = 0;
        for (int i : lottos) {
            if(i == 0){
                zeroCount++;
                continue;
            }
            map.put(i, true);
        }

        int sameCount = 0;
        for (int winNum : win_nums) {
            if (map.containsKey(winNum)) {
                sameCount++;
            }
        }

        int maxRank = 7 - (sameCount + zeroCount);
        int minRank = 7 - sameCount;
        if(maxRank > 6) {
            maxRank = 6;
        }
        if(minRank > 6) {
            minRank = 6;
        }
        System.out.println("maxRank = " + maxRank);
        System.out.println("minRank = " + minRank);


    }
}
