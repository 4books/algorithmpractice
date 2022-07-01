package programers.level1.p77884;

public class Solution {

    public static void main(String[] args) {
        int left = 13;
        int right = 17;
        int answer = 0;

        for (int i = left; i <= right; i++) {
            //제곱수인 경우 약수의 개수가 홀수
            if (i % Math.sqrt(i) == 0) {
                answer -= i;
            }
            //제곱수가 아닌 경우 약수의 개수가 짝수
            else {
                answer += i;
            }
        }
        int expect = 43;

        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
