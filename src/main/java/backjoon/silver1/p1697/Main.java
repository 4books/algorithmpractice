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

        int next, nTime;
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            nTime = visited[curr] + 1;

            if (visited[E] != 0) {
                break;
            }

            next = curr - 1;
            if (next > 0) {
                if (visited[next] == 0) {
                    visited[next] = nTime;
                    queue.offer(next);
                }
            }
            next = curr + 1;
            if (next <= 100_000) {
                if (visited[next] == 0) {
                    visited[next] = nTime;
                    queue.offer(curr);
                }
            }
            next = curr * 2;
            if (next > 0 && next <= 100_000) {
                if (visited[next] == 0) {
                    visited[next] = nTime;
                    queue.offer(next);
                }
            }

        }

        //최초에 1로 초기화 했으므로 -1
        System.out.println(visited[E] - 1);


    } //END of Main Method

}


