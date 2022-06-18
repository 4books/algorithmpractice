package greedyalgo.no5;
/*
다익스트라 알고리즘
아래의 가중치 방향그래프에서 1번 정점에서 모든 정점으로의 최소 거리비용을 출력하는 프로
그램을 작성하세요. (경로가 없으면 Impossible를 출력한다)
2 5
1 2 5
12
5
4
5 2
3 4 5 6
5
▣ 입력설명
첫째 줄에는 정점의 수 N(1<=N<=20)와 간선의 수 M가 주어진다. 그 다음부터 M줄에 걸쳐 연
결정보와 거리비용이 주어진다.
▣ 출력설명
1번 정점에서 각 정점으로 가는 최소비용을 2번 정점부터 차례대로 출력하세요.


// 1번 정점에서 2번정점으로 가는데 12의 비용이 든다.
▣ 입력예제 1
6 9
1 2 12
1 3 4
2 1 2
2 3 5
2 5 5
3 4 5
4 2 2
4 5 5
6 4 5
▣ 출력예제 1
2 : 11
3 : 4
4 : 9
5 : 14
6 : impossible
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static class Edge implements Comparable<Edge> {
        public int vex;//도착 정점
        public int cost;//도착 비용

        public Edge(int vex, int cost) {
            this.vex = vex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) { //코스트가 적은 것부터 나오게 하기 위해
            return this.cost - o.cost; //내림차순
        }
    }


    static int n = 0; //정점 수
    static int m = 0; //간선 수
    static int[] dis; //각 정점이 1과의 거리. MAX 로 초기화
    static ArrayList<ArrayList<Edge>> graph;//그래프. 사진 참고


    public static void solution(int v) {
        //코스트가 적은 것부터 poll 되는 Queue
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(v, 0));

        //모든 dis 는 MAX 로 초기화 되어 있다.
        //최초는 1번이고 비용은 0이다.
        dis[v] = 0;

        while (!pq.isEmpty()) {
            Edge tmp = pq.poll();
            int now = tmp.vex;
            int nowCost = tmp.cost;

            //만약 이미 dis 가 더 작은 값이면 넘어감
            if (nowCost > dis[now]) {
                continue;
            }

            //graph 에서 현재 정점
            //최초는 1이고 1번일 경우 (2,12), (3,4) (가는 정점, 비용) 이다.
            //이 두개를 차례대로 가져 온다.
            for (Edge ob : graph.get(now)) {

                //now 의 정점간 연결된 정점의 코스트
                //현재 정점의 코스트 만약 1->5를 간다면
                //1->2 가는 비용 12 + 2->5 가는 비용 5 를 더해서 최소거리인지 확인하는 것
                if (dis[ob.vex] > nowCost + ob.cost) {
                    int newCost = nowCost + ob.cost;
                    dis[ob.vex] = newCost;
                    //1일 경우
                    //1 -> 2를 가는 비용을 PQ에 넣는다
                    pq.offer(new Edge(ob.vex, newCost));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");

        n = Integer.parseInt(tmp[0]); // 정점의 수
        m = Integer.parseInt(tmp[1]); // 간선의 수

        //ArrayList<ArrayList<Edge>>
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            //ArrayList<Edge> 를 차례대로 넣음
            graph.add(new ArrayList<>());
        }
        
        dis = new int[n + 1];//1번에서 해당 index 까지의 거리를 담을 배열
        Arrays.fill(dis, Integer.MAX_VALUE); //MAX 값으로 초기화

        for (int i = 0; i < m; i++) {
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);//출발 정점
            int b = Integer.parseInt(tmp[1]);//도착 정점
            int c = Integer.parseInt(tmp[2]);//비용

            //graph 에서 차례대로 출발 정점을 가져와서 (도착정점, 비용) 을 넣는다.
            graph.get(a).add(new Edge(b, c));
        }

        solution(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            if (dis[i] == Integer.MAX_VALUE) {
                sb.append(i).append(" : impossible\n");
            } else {
                sb.append(i).append(" : ").append(dis[i]).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}


