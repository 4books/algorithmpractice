package programers.level2.p42583;
/*
https://programmers.co.kr/learn/courses/30/lessons/42583

문제 설명
트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

경과 시간	다리를 지난 트럭	다리를 건너는 트럭	대기 트럭
0	[]	[]	[7,4,5,6]
1~2	[]	[7]	[4,5,6]
3	[7]	[4]	[5,6]
4	[7]	[4,5]	[6]
5	[7,4]	[5]	[6]
6~7	[7,4,5]	[6]	[]
8	[7,4,5,6]	[]	[]
따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

제한 조건
bridge_length는 1 이상 10,000 이하입니다.
weight는 1 이상 10,000 이하입니다.
truck_weights의 길이는 1 이상 10,000 이하입니다.
모든 트럭의 무게는 1 이상 weight 이하입니다.
입출력 예
bridge_length	weight	truck_weights	return
2	10	[7,4,5,6]	8
100	100	[10]	101
100	100	[10,10,10,10,10,10,10,10,10,10]	110
출처

※ 공지 - 2020년 4월 06일 테스트케이스가 추가되었습니다.
 */


import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Solution {

        public class Truck {
            public int weight;
            public int time;

            public Truck(int weight, int time) {
                this.weight = weight;
                this.time = time;
            }
        }

        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;

            //트럭 큐
            Queue<Truck> trucks = new LinkedList<>();
            for (int i = 0; i < truck_weights.length; i++) {
                trucks.offer(new Truck(truck_weights[i], 0));
            }

            //다리에 있는 트럭들의 무게
            int sum = 0;
            //다리 큐
            Queue<Truck> bridge = new LinkedList<>();
            while (!trucks.isEmpty() || !bridge.isEmpty()) {
                answer++;//시간이 1초 흐름

                //다리 위에 트럭 체크
                if (!bridge.isEmpty()) {
                    //bridge 시간 추가 및 가장 앞에 있는 거 시간 지나가면 삭제
                    for (Truck truck : bridge) {
                        truck.time++;
                    }
                    if (bridge.peek().time > bridge_length) {
                        Truck truck = bridge.poll();
                        sum -= truck.weight;
                    }
                }

                if (!trucks.isEmpty()) {
                    //다리가 버틸 수 있는 무개를 초과하면 트럭이 다리를 지나가지 않음
                    if (sum + trucks.peek().weight <= weight) {
                        //트럭이 지나갈 수 있음
                        Truck truck = trucks.poll();
                        sum += truck.weight;
                        truck.time++;
                        bridge.offer(truck);
                    }
                }
            }

            return answer;
        }

    }

    public static void main(String[] args) {

        Solution s = new Solution();

        int[] truck_weights = {7, 4, 5, 6};


        int answer = s.solution(2, 10, truck_weights);
        int expect = 8;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);

        int[] truck_weights2 = {10};
        answer = s.solution(100, 100, truck_weights2);
        expect = 101;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);

        int[] truck_weights3 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        answer = s.solution(100, 100, truck_weights3);
        expect = 110;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);

    }
}
