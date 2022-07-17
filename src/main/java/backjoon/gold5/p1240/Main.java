package backjoon.gold5.p1240;
/*
https://www.acmicpc.net/problem/1240

문제
N(2≤N≤1,000)개의 노드로 이루어진 트리가 주어지고 M(M≤1,000)개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.

입력
첫째 줄에 노드의 개수 N이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리(10,000 이하의 정수)를 입력받는다. 그 다음 줄에는 거리를 알고 싶은 M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.

출력
M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.

 */


import java.io.*;
import java.util.*;

public class Main {

    public static class Edge implements Comparable<Edge>{
        public int vex;
        public int cost;

        public Edge(int vex, int cost) {
            this.vex = vex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;//내림차순
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //노드 갯수
        int N = Integer.parseInt(st.nextToken());
        //알고 싶은 정점 거리 갯수
        int M = Integer.parseInt(st.nextToken());

        List<ArrayList<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        //0은 사용안함
        //N - 1
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int edge1 = Integer.parseInt(st.nextToken());
            int edge2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            //양방향
            graph.get(edge1).add(new Edge(edge2, cost));
            graph.get(edge2).add(new Edge(edge1, cost));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dis = new int[N + 1];
        int maxInt = 10_001;

        for (int i = 0; i < M; i++) {

            //거리 초기화
            Arrays.fill(dis, maxInt);

            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            //start 지점 초기화
            dis[start] = 0;

            pq.add(new Edge(start, 0));

            Edge now;
            int nowVex, nowCost;

            while (!pq.isEmpty()) {

                now = pq.poll();

                nowVex = now.vex;
                nowCost = now.cost;

                //만약 이미 거리가 더 짧다면 다음으로
                if (nowCost > dis[nowVex]) {
                    continue;
                }


                for (Edge next : graph.get(nowVex)) {
                    int newCost = nowCost + next.cost;
                    if(dis[next.vex] > newCost) {
                        dis[next.vex] = newCost;
                        pq.add(new Edge(next.vex, newCost));
                    }
                }
            }
            
            //모든 탐색이 끝나면 end cost 를 가져온다
            bw.write(dis[end] + "\n");

        }

        bw.close();
    }
}


