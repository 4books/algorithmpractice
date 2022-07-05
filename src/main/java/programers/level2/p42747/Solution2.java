package programers.level2.p42747;

import java.util.Arrays;

public class Solution2 {

    public static void main(String[] args) {

        int[] citations = {3, 0, 6, 1, 5};

        Arrays.sort(citations);

        int max = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            int min = Math.min(citations[i], citations.length - i);
            if (max < min) max = min;
        }

        System.out.println("max = " + max);
    }

}
