package programers.level1.p86051;

public class Solution {
    public static void main(String[] args) {
        int answer = 45;
        int[] numbers = {1, 2, 3, 4, 6, 7, 8, 0};

        for (int number : numbers) {
            answer -= number;
        }

        System.out.println("answer = " + answer);
    }
}
