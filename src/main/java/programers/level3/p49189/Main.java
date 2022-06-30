package programers.level3.p49189;
/*
문제 설명
n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
입출력 예
n	vertex	return
6	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	3
입출력 예 설명
예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {


    static class Solution {
        public int solution(int n, int[][] edge) {
            int answer = 0;

            //1번과의 거리를 담을 배열
            int[] dis = new int[n + 1];

            //index 출발지점 List 도착지점(여러개임)
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(i, new ArrayList<>());
            }

            for (int i = 0; i < edge.length; i++) {
                //양방향 저장
                graph.get(edge[i][0]).add(edge[i][1]);
                graph.get(edge[i][1]).add(edge[i][0]);
            }

            int max = 0;//최장 거리 담을 변수

            Queue<Integer> queue = new LinkedList<>();

            queue.add(1);//시작지점
            queue.add(0);//1번과의 거리
            dis[1] = -1; //1번 정점 거리 측정하지 않게 초기화

            //bfs
            while(!queue.isEmpty()) {
                int now = queue.poll();
                int depth = queue.poll();

                //최장 거리
                max = Math.max(max, depth);
                for (Integer next : graph.get(now)) {
                    if(dis[next] != 0) { //이미 거리가 정해진 곳은 다시 확인할 필요 없음
                        continue;
                    }
                    dis[next] = depth + 1;
                    queue.offer(next);
                    queue.offer(depth + 1);
                }
            }

            for (int i = 0; i < dis.length; i++) {
                if (max == dis[i]) {
                    answer++;
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {

        Solution s = new Solution();

        int n = 6;
        int[][] edge = {
                {3, 6},
                {4, 3},
                {3, 2},
                {1, 3},
                {1, 2},
                {2, 4},
                {5, 2}
        };

        int answer = s.solution(n, edge);
        int expect = 3;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
