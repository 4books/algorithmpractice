package backjoon.silver1.p1697;
/*
https://www.acmicpc.net/problem/1697
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int S = Integer.parseInt(arr[0]);
        int E = Integer.parseInt(arr[1]);

        int[] visited = new int[100_001];

        Queue<Integer> queue = new LinkedList<>();
        //일단 곱하기가 있으므로 1로 초기화
        queue.offer(S);

        visited[S] = 1;

        int n;
        while (!queue.isEmpty()) {
            n = queue.poll();

            if(n == E){
                break;
            }

            if (n - 1 >= 0 && visited[n - 1] == 0) {
                visited[n - 1] = visited[n] + 1;
                queue.offer(n - 1);
            }
            if (n + 1 <= 100_000 && visited[n + 1] == 0) {
                visited[n + 1] = visited[n] + 1;
                queue.offer(n + 1);
            }
            if (2 * n <= 100_000 && visited[2 * n] == 0) {
                visited[2 * n] = visited[n] + 1;
                queue.offer(2 * n);
            }
        }

        //최초에 1로 초기화 했으므로 -1
        System.out.println(visited[E] - 1);


    } //END of Main Method

}


