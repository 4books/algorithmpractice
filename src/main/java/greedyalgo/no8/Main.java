package greedyalgo.no8;

/*
7. 원더랜드(최소스패닝트리)
설명

원더랜드에 문제가 생겼다. 원더랜드의 각 도로를 유지보수하는 재정이 바닥난 것이다.

원더랜드는 모든 도시를 서로 연결하면서 최소의 유지비용이 들도록 도로를 선택하고 나머지 도로는 폐쇄하려고 한다.

아래의 그림은 그 한 예를 설명하는 그림이다.

Image1.jpg

위의 지도는 각 도시가 1부터 9로 표현되었고, 지도의 오른쪽은 최소비용 196으로 모든 도시를 연결하는 방법을 찾아낸 것이다.


입력
첫째 줄에 도시의 개수 V(1≤V≤100)와 도로의 개수 E(1≤E≤1,000)가 주어진다.

다음 E개의 줄에는 각 도로에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.

이는 A번 도시와 B번 도시가 유지비용이 C인 도로로 연결되어 있다는 의미이다.


출력
모든 도시를 연결하면서 드는 최소비용을 출려한다.


예시 입력 1

9 12
1 2 12
1 9 25
2 3 10
2 8 17
2 9 8
3 4 18
3 7 55
4 5 44
5 6 60
5 7 38
7 8 35
8 9 15
예시 출력 1

196
 */

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    static class Edge implements Comparable<Edge> {
        public int vex;
        public int cost;

        public Edge(int vex, int cost) {
            this.vex = vex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int n;
    static int m;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");

        n = Integer.parseInt(tmp[0]); //도시의 갯수
        m = Integer.parseInt(tmp[1]); //도로의 갯수

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        boolean[] check = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            int c = Integer.parseInt(tmp[2]);

            //서로 양방향으로 뻗으므로
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));

        }

        int answer = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(); //오름차순
        pq.offer(new Edge(1, 0)); //1번 정점 부터 시작

        while (!pq.isEmpty()) {
            Edge temp = pq.poll();
            int ev = temp.vex; //도착 정점 번호
            if(!check[ev]){ //도착 정점은 체크하지하지 않았다면
                check[ev] = true; //도착 정점 체크
                answer += temp.cost; //비용 추가
                for (Edge ob : graph.get(ev)) { //도착 정점에 연결리스트들을 가지고 옴
                    if(!check[ob.vex]) {
                        pq.offer(new Edge(ob.vex, ob.cost));
                    }
                }
            }
        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }


}


