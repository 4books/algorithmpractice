package dfsbfsalgo.no12;

/*
경로 탐색(인접행렬)
방향그래프가 주어지면 1번 정점에서 N번 정점으로 가는 모든 경로의 가지 수를 출력하는 프
로그램을 작성하세요. 아래 그래프에서 1번 정점에서 5번 정점으로 가는 가지 수는
1 2 3 4 5
1 2 5
1 3 4 2 5
1 3 4 5
1 4 2 5
1 4 5
총 6 가지입니다.
▣ 입력설명
첫째 줄에는 정점의 수 N(1<=N<=20)와 간선의 수 M가 주어진다. 그 다음부터 M줄에 걸쳐 연
결정보가 주어진다.
▣ 출력설명
총 가지수를 출력한다.
▣ 입력예제 1
5 9
1 2
1 3
1 4
2 1
2 3
2 5
3 4
4 2
4 5
▣ 출력예제 1
6
 */
import java.io.*;

public class Main {

    public static int answer = 0;
    public static void dfs(int v, int n, int[][] graph, boolean[] checks) {
        //v 현재 노드,  n 방문 노드
        if(v == n){ //방문할수 있다면?
            answer++;
        } else {
            for (int i = 1; i <= n; i++) {
                if(graph[v][i] == 1 && !checks[i]){
                    checks[i] = true;
                    dfs(i, n, graph, checks);
                    checks[i] = false;
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

        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            graph[x][y] = 1;
        }
        //이미 방문한 곳은 방문하지 않도록
        boolean[] checks = new boolean[n + 1];
        //출발지점은 이미 방문문
        checks[1] = true;
        dfs(1, n, graph, checks);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }


}
