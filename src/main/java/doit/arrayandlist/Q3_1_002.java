package doit.arrayandlist;

public class Q3_1_002 {
    public static double solution(int n, int[] scores){

        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, scores[i]);
            sum += scores[i];
        }

        return sum * 100.0 / max / n;
    }
}
