package backjoon.gold5.p5014;
/*
https://www.acmicpc.net/problem/5014

문제
강호는 코딩 교육을 하는 스타트업 스타트링크에 지원했다. 오늘은 강호의 면접날이다. 하지만, 늦잠을 잔 강호는 스타트링크가 있는 건물에 늦게 도착하고 말았다.

스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층이다. 강호가 지금 있는 곳은 S층이고, 이제 엘리베이터를 타고 G층으로 이동하려고 한다.

보통 엘리베이터에는 어떤 층으로 이동할 수 있는 버튼이 있지만, 강호가 탄 엘리베이터는 버튼이 2개밖에 없다. U버튼은 위로 U층을 가는 버튼, D버튼은 아래로 D층을 가는 버튼이다. (만약, U층 위, 또는 D층 아래에 해당하는 층이 없을 때는, 엘리베이터는 움직이지 않는다)

강호가 G층에 도착하려면, 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램을 작성하시오. 만약, 엘리베이터를 이용해서 G층에 갈 수 없다면, "use the stairs"를 출력한다.

입력
첫째 줄에 F, S, G, U, D가 주어진다. (1 ≤ S, G ≤ F ≤ 1000000, 0 ≤ U, D ≤ 1000000) 건물은 1층부터 시작하고, 가장 높은 층은 F층이다.

출력
첫째 줄에 강호가 S층에서 G층으로 가기 위해 눌러야 하는 버튼의 수의 최솟값을 출력한다. 만약, 엘리베이터로 이동할 수 없을 때는 "use the stairs"를 출력한다.

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    static int TOP, C, E, U, D, ANS = 900_000_000;
    static final String STAIR = "use the stairs";
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());

        TOP = Integer.parseInt(tk.nextToken());
        C = Integer.parseInt(tk.nextToken());
        E = Integer.parseInt(tk.nextToken());
        U = Integer.parseInt(tk.nextToken());
        D = Integer.parseInt(tk.nextToken());


        if (C == E) {
            System.out.println(0);
        } else if (C < E && U == 0 || C > E && D == 0) {
            //현재 위치보다 윗층에 있는데 위 버튼이 0인 경우
            //현재 위치보다 아래층에 있는데 아래 버튼이 0인 경우
            System.out.println(STAIR);
        } else {
            //지하와 지상 위를 더 만듬
            //U 버튼과 D 버튼을 눌러서 갈 층 넘버를 만들어야 하기 때문

            //위로 먼저 가는 경우
            findMin(C + U);
            //아래로 먼저 가는 경우
            findMin(C - D);

            if (ANS == 900_000_000) {
                System.out.println(STAIR);
            } else {
                System.out.println(ANS);
            }
        }
    } //END of Main Method

    private static void findMin(int start) {

        visited = new boolean[TOP + 1];
        visited[C] = true;

        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(start, 1));

        while (!q.isEmpty()) {
            Point floor = q.poll();

            int f = floor.curr;
            int c = floor.cnt;

            //도착 확인
            if (f == E) {
                ANS = Math.min(ANS, c);
            }

            //범위를 벗어날 경우
            if (f <= 0 || f > TOP) {
                continue;
            }

            if (visited[f]) {
                continue;
            }

            visited[f] = true;

            if (f - D > 0) {
                q.offer(new Point(f - D, c + 1));
            }
            if (f + U <= TOP) {
                q.offer(new Point(f + U, c + 1));
            }
        }
    }

    static class Point {
        int curr, cnt;

        public Point(int curr, int cnt) {
            this.curr = curr;
            this.cnt = cnt;
        }
    }
}


