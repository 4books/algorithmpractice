package programers.level3.p42628;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/42628

문제 설명
이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.

명령어	수신 탑(높이)
I 숫자	큐에 주어진 숫자를 삽입합니다.
D 1	큐에서 최댓값을 삭제합니다.
D -1	큐에서 최솟값을 삭제합니다.
이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.

제한사항
operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
operations의 원소는 큐가 수행할 연산을 나타냅니다.
원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
입출력 예
operations	return
["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]	[0,0]
["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]	[333, -45]
입출력 예 설명
입출력 예 #1

16과 -5643을 삽입합니다.
최솟값을 삭제합니다. -5643이 삭제되고 16이 남아있습니다.
최댓값을 삭제합니다. 16이 삭제되고 이중 우선순위 큐는 비어있습니다.
우선순위 큐가 비어있으므로 최댓값 삭제 연산이 무시됩니다.
123을 삽입합니다.
최솟값을 삭제합니다. 123이 삭제되고 이중 우선순위 큐는 비어있습니다.
따라서 [0, 0]을 반환합니다.

입출력 예 #2

-45와 653을 삽입후 최댓값(653)을 삭제합니다. -45가 남아있습니다.
-642, 45, 97을 삽입 후 최댓값(97), 최솟값(-642)을 삭제합니다. -45와 45가 남아있습니다.
333을 삽입합니다.
이중 우선순위 큐에 -45, 45, 333이 남아있으므로, [333, -45]를 반환합니다.
 */

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {

    private final String INSERT = "I";
    private final String MAX_DELETE = "D 1";
    private final String MIN_DELETE = "D -1";


    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < operations.length; i++) {
            String command = operations[i];
            
            if(command.startsWith(INSERT)){//insert 일 경우
                Integer number = Integer.parseInt(command.split(" ")[1]);
                minHeap.offer(number);
                maxHeap.offer(number);
            } else if(command.equals(MAX_DELETE)){ //최대값 삭제일 경우
                if(!maxHeap.isEmpty() && !minHeap.isEmpty()){
                    Integer rmNumber = maxHeap.poll();
                    minHeap.remove(rmNumber);
                }
            } else if(command.equals(MIN_DELETE)){ //최소값 삭제일 경우
                if(!maxHeap.isEmpty() && !minHeap.isEmpty()){
                    Integer rmNumber = minHeap.poll();
                    maxHeap.remove(rmNumber);
                }
            }
        }

        if(!maxHeap.isEmpty() && !minHeap.isEmpty()){
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }

        return answer;
    }

}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};

        int[] answer = s.solution(operations);

        String expect = "0 0";
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
        System.out.println("expect = " + expect);

        operations = new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        answer = s.solution(operations);
        expect = "333 -45";
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
        System.out.println("expect = " + expect);
    }
}
