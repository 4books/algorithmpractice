package dfsbfsalgo.no13;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int answer = 0;
    public static void dfs(int v, int n, List<ArrayList<Integer>> graph, boolean[] checks) {
        //v 현재 노드,  n 방문 노드
        if(v == n){ //목표 노드
            answer++;
        } else {
            for (Integer nextV : graph.get(v)) {
                if(!checks[nextV]){
                    checks[nextV] = true;
                    dfs(nextV, n, graph, checks);
                    checks[nextV] = false;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]); //정점 수 이자 방문지점
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
        //이미 방문한 곳은 방문하지 않도록
        boolean[] checks = new boolean[n + 1];
        //출발지점은 이미 방문
        checks[1] = true;
        dfs(1, n, graph, checks);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
