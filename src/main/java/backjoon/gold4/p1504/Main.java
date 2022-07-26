package backjoon.gold4.p1504;
/*
https://www.acmicpc.net/problem/1504

문제
방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.

출력
첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, R;
    static final int INF = 200_000_000;
    static List<ArrayList<Node>> graph;
    static int[] dis;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        R = Integer.parseInt(token.nextToken());

        graph = new ArrayList<>();
        
        //각 정점으로 갈 때 cost 저장
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        //거리를 담은 배열 생성
        dis = new int[N + 1];

        for (int i = 0; i < R; i++) {
            token = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());
            int cost = Integer.parseInt(token.nextToken());

            //양방향 생성
            graph.get(start).add(new Node(end, cost));
            graph.get(end).add(new Node(start, cost));
        }

        token = new StringTokenizer(br.readLine());


        //필수 방문 두 지점
        int v1 = Integer.parseInt(token.nextToken());
        int v2 = Integer.parseInt(token.nextToken());

        //1 -> v1 -> v2 -> N
        int ans1 = 0;
        ans1 += bfs(1, v1);
        ans1 += bfs(v1, v2);
        ans1 += bfs(v2, N);

        //1 -> v2 -> v1 -> N
        int ans2 = 0;
        ans2 += bfs(1, v2);
        ans2 += bfs(v2, v1);
        ans2 += bfs(v1, N);

        int answer = (ans1 >= INF && ans2 >= INF) ? -1 : Math.min(ans1, ans2);
        System.out.println(answer);

    } //END of Main Method

    private static int bfs(int start, int end) {
        //거리 초기화
        Arrays.fill(dis, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[N + 1];

        //시작 지점 초기화
        dis[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();

            int nStart = tmp.end;

            if (visited[nStart]) {
                continue;
            }

            visited[nStart] = true;
            for (Node node : graph.get(nStart)) {
                //방문하지 않았고, 해당 거리가 현재 거리보다 크다면 바꾸고 탐색
                //1 -> 2 비용이 10 이고
                //1 -> 3 -> 비용이 2이면 1 -> 2 비용을 2로 변경
                if (!visited[node.end] && dis[node.end] > dis[nStart] + node.cost) {
                    dis[node.end] = dis[nStart] + node.cost;
                    pq.offer(new Node(node.end, dis[node.end]));
                }
            }

        }

        return dis[end];
    }

}


