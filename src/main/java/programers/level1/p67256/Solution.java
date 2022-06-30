package programers.level1.p67256;

public class Solution {

    //0부터 9까지 좌표 {y,x}
    int[][] numpadPos = {
            {3, 1}, //0
            {0, 0}, //1
            {0, 1}, //2
            {0, 2}, //3
            {1, 0}, //4
            {1, 1}, //5
            {1, 2}, //6
            {2, 0}, //7
            {2, 1}, //8
            {2, 2}  //9
    };
    //초기 위치
    int[] leftPos = {3, 0};
    int[] rightPos = {3, 2};
    String hand;

    public String solution(int[] numbers, String hand) {
        this.hand = (hand.equals("right")) ? "R" : "L";

        StringBuilder answer = new StringBuilder();
        for (int num : numbers) {
            String umji = pushNumber(num);
            answer.append(umji);

            if (umji.equals("L")) {
                leftPos = numpadPos[num];
            } else {
                rightPos = numpadPos[num];
            }
        }
        return answer.toString();
    }

    //num버튼을 누를 때 어디 손을 사용하는가
    private String pushNumber(int num) {
        if (num == 1 || num == 4 || num == 7) return "L";
        if (num == 3 || num == 6 || num == 9) return "R";

        // 2,5,8,0 일때 어디 손가락이 가까운가
        if (getDist(leftPos, num) > getDist(rightPos, num)) return "R";
        if (getDist(leftPos, num) < getDist(rightPos, num)) return "L";

        //같으면 손잡이
        return this.hand;
    }

    //해당 위치와 번호 위치의 거리
    private int getDist(int[] pos, int num) {
        return Math.abs(pos[0] - numpadPos[num][0]) + Math.abs(pos[1] - numpadPos[num][1]);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        String answer = s.solution(numbers, hand);
        System.out.println("answer = " + answer);
    }
}
