package dfsbfsalgo.no14;
/*
그래프 최단거리(BFS)
다음 그래프에서 1번 정점에서 각 정점으로 가는 최소 이동 간선수를 출력하세요.
1 2 5
3 4 6
▣ 입력설명
첫째 줄에는 정점의 수 N(1<=N<=20)와 간선의 수 M가 주어진다. 그 다음부터 M줄에 걸쳐 연
결정보가 주어진다.
▣ 출력설명
1번 정점에서 각 정점으로 가는 최소 간선수를 2번 정점부터 차례대로 출력하세요.
▣ 입력예제 1
6 9
1 3
1 4
2 1
2 5
3 4
4 5
4 6
6 2
6 5
▣ 출력예제 1
2 : 3
3 : 1
4 : 1
5 : 2
6 : 2
 */

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void bfs(int v, List<ArrayList<Integer>> graph, boolean[] checks, int[] distance) {
        Queue<Integer> queue = new LinkedList<>();

        checks[v] = true;
        distance[v] = 0;

        queue.offer(v); //1번 노드 초기화 완료

        while(!queue.isEmpty()){
            int cv = queue.poll(); //queue 안의 노드를 가져옴

            //해당 노드의 방문 가능한 노드를 가져옴
            for(int nv : graph.get(cv)){ 
                
                //방문하지 않은 노드라면
                if(!checks[nv]){
                    checks[nv] = true;
                    queue.offer(nv);
                    distance[nv] = distance[cv] + 1; //1번 노드까지 최단 거리 기록
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]); //정점 수, 방문지점
        int m = Integer.parseInt(tmp[1]); //간선 수

        List<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            graph.get(x).add(y);
        }
        //이미 방문한 곳
        boolean[] checks = new boolean[n + 1];
        int[] distance = new int[n + 1];

        bfs(1, graph, checks, distance);
        StringBuilder sb = new StringBuilder();

        for (int i = 2; i < distance.length; i++) {
            sb.append(i).append(" : ").append(distance[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
