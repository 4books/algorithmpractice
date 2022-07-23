package backjoon.gold4.p1043;
/*
https://www.acmicpc.net/problem/1043

지민이는 파티에 가서 이야기 하는 것을 좋아한다. 파티에 갈 때마다, 지민이는 지민이가 가장 좋아하는 이야기를 한다. 지민이는 그 이야기를 말할 때, 있는 그대로 진실로 말하거나 엄청나게 과장해서 말한다. 당연히 과장해서 이야기하는 것이 훨씬 더 재미있기 때문에, 되도록이면 과장해서 이야기하려고 한다. 하지만, 지민이는 거짓말쟁이로 알려지기는 싫어한다. 문제는 몇몇 사람들은 그 이야기의 진실을 안다는 것이다. 따라서 이런 사람들이 파티에 왔을 때는, 지민이는 진실을 이야기할 수 밖에 없다. 당연히, 어떤 사람이 어떤 파티에서는 진실을 듣고, 또다른 파티에서는 과장된 이야기를 들었을 때도 지민이는 거짓말쟁이로 알려지게 된다. 지민이는 이런 일을 모두 피해야 한다.

사람의 수 N이 주어진다. 그리고 그 이야기의 진실을 아는 사람이 주어진다. 그리고 각 파티에 오는 사람들의 번호가 주어진다. 지민이는 모든 파티에 참가해야 한다. 이때, 지민이가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.

둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다. 진실을 아는 사람의 수가 먼저 주어지고 그 개수만큼 사람들의 번호가 주어진다. 사람들의 번호는 1부터 N까지의 수로 주어진다.

셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어진다.

N, M은 50 이하의 자연수이고, 진실을 아는 사람의 수는 0 이상 50 이하의 정수, 각 파티마다 오는 사람의 수는 1 이상 50 이하의 정수이다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String[] know;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.
        int N = Integer.parseInt(st.nextToken()); //사람 수
        int M = Integer.parseInt(st.nextToken()); //파티 수

        //사람 그래프
        Set[] graph = new Set[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new TreeSet<Integer>();
        }

        //둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다.
        know = br.readLine().split(" ");
        for (int i = 1; i < Integer.parseInt(know[0]) - 1; i++) {
            int p1 = Integer.parseInt(know[i]);
            int p2 = Integer.parseInt(know[i + 1]);

            graph[p1].add(p2);
            graph[p2].add(p1);
        }

        //셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호
        for (int p = 0; p < M; p++) {
            know = br.readLine().split(" ");
            for (int i = 1; i < Integer.parseInt(know[0]) - 1; i++) {
                int p1 = Integer.parseInt(know[i]);
                int p2 = Integer.parseInt(know[i + 1]);

                if (!graph[p1].isEmpty() || !graph[p2].isEmpty()) {
                    graph[p1].add(p2);
                    graph[p2].add(p1);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < N + 1; i++) {
            if(graph[i].isEmpty()){
                answer++;
            }
        }

        System.out.println(answer);
    } //END of Main Method


}


