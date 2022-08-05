package programers.practice.test20220805_1.n3;
/*

 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;

        Queue<Integer> productQ = new LinkedList<>();
        Stack<Integer> subStack = new Stack<>();
        for (int i = 0; i < order.length; i++) {
            productQ.offer(i + 1);
        }

        for (int i = 0; i < order.length; i++) {

            int max = productQ.size();
            for (int j = 0; j < max; j++) {

                if (!productQ.isEmpty() && order[i] == productQ.peek()) {
                    //주 컨테이너에 해당 주문 물건이 있다면 해당 주문 끝
                    productQ.poll();
                    answer++;
                    break;
                } else {
                    //없다면 보조 컨테이너로
                    subStack.push(productQ.poll());
                }
            }

            while (!subStack.isEmpty() && subStack.peek() == order[i]) {
                answer++;
                subStack.pop();
            }

        }

        return answer;
    }

}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;

        answer = s.solution(new int[]{4, 3, 1, 2, 5});
        System.out.println("answer = " + answer);
        answer = s.solution(new int[]{1, 2, 3, 4, 5});
        System.out.println("answer = " + answer);
        answer = s.solution(new int[]{5, 4, 3, 2, 1});
        System.out.println("answer = " + answer);

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
