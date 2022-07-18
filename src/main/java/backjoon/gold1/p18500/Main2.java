package backjoon.gold1.p18500;
/*
https://www.acmicpc.net/problem/18500

문제
창영과 상근은 한 동굴을 놓고 소유권을 주장하고 있다. 두 사람은 막대기를 서로에게 던지는 방법을 이용해 누구의 소유인지를 결정하기로 했다. 싸움은 동굴에서 벌어진다. 동굴에는 미네랄이 저장되어 있으며, 던진 막대기가 미네랄을 파괴할 수도 있다.

동굴은 R행 C열로 나타낼 수 있으며, R×C칸으로 이루어져 있다. 각 칸은 비어있거나 미네랄을 포함하고 있으며, 네 방향 중 하나로 인접한 미네랄이 포함된 두 칸은 같은 클러스터이다.

창영은 동굴의 왼쪽에 서있고, 상근은 오른쪽에 서있다. 두 사람은 턴을 번갈아가며 막대기를 던진다. 막대를 던지기 전에 던질 높이를 정해야 한다. 막대는 땅과 수평을 이루며 날아간다.

막대가 날아가다가 미네랄을 만나면, 그 칸에 있는 미네랄은 모두 파괴되고 막대는 그 자리에서 이동을 멈춘다.

미네랄이 파괴된 이후에 남은 클러스터가 분리될 수도 있다. 새롭게 생성된 클러스터가 떠 있는 경우에는 중력에 의해서 바닥으로 떨어지게 된다. 떨어지는 동안 클러스터의 모양은 변하지 않는다. 클러스터는 다른 클러스터나 땅을 만나기 전까지 게속해서 떨어진다. 클러스터는 다른 클러스터 위에 떨어질 수 있고, 그 이후에는 합쳐지게 된다.

동굴에 있는 미네랄의 모양과 두 사람이 던진 막대의 높이가 주어진다. 모든 막대를 던지고 난 이후에 미네랄 모양을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 동굴의 크기 R과 C가 주어진다. (1 ≤ R,C ≤ 100)

다음 R개 줄에는 C개의 문자가 주어지며, '.'는 빈 칸, 'x'는 미네랄을 나타낸다.

다음 줄에는 막대를 던진 횟수 N이 주어진다. (1 ≤ N ≤ 100)

마지막 줄에는 막대를 던진 높이가 주어지며, 공백으로 구분되어져 있다. 모든 높이는 1과 R사이이며, 높이 1은 행렬의 가장 바닥, R은 가장 위를 의미한다. 첫 번째 막대는 왼쪽에서 오른쪽으로 던졌으며, 두 번째는 오른쪽에서 왼쪽으로, 이와 같은 식으로 번갈아가며 던진다.

공중에 떠 있는 미네랄 클러스터는 없으며, 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우도 없다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int[] dR = {0, 0, 1, -1}; //좌 우 하 상
    static int[] dC = {-1, 1, 0, 0};

    static class Mineral implements Comparable<Mineral> {
        int row, col;

        public Mineral(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Mineral o) {
            if (this.row == o.row) {
                return this.col - o.col; //col 은 오름차순
            }
            return o.row - this.row; //내림차순 맨 밑에부터 내려야함
        }
    }

    public static void main(String[] args) throws IOException {


        //새롭게 생성된 클러스터가 떠 있는 경우에는 중력에 의해서 바닥으로 떨어지게 된다.
        //떨어지는 동안 클러스터의 모양은 변하지 않는다.
        //클러스터는 다른 클러스터나 땅을 만나기 전까지 게속해서 떨어진다. 클러스터는 다른 클러스터 위에 떨어질 수 있고, 그 이후에는 합쳐지게 된다.

        //1은 행렬의 가장 바닥, R은 가장 위를 의미한다. 첫 번째 막대는 왼쪽에서 오른쪽으로 던졌으며, 두 번째는 오른쪽에서 왼쪽으로, 이와 같은 식으로 번갈아가며 던진다.

        //공중에 떠 있는 미네랄 클러스터는 없으며, 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우도 없다.


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] cave = new char[R][C];

        //미네랄 위치
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                cave[i][j] = s.charAt(j);
            }
        }

        //던지는 횟수
        int count = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < count; i++) {
            //던진 높이가 5라면 배열상 row 는 0
            int row = R - Integer.parseInt(st.nextToken()), col;

            if (i % 2 == 0) { //왼편 막대기 던짐
                for (col = 0; col < C; col++) {
                    if (cave[row][col] == 'x') {
                        cave[row][col] = '.';
                        break;
                    }
                }
            } else { //오른편 막대기 던짐
                for (col = C - 1; col >= 0; col--) {
                    if (cave[row][col] == 'x') {
                        cave[row][col] = '.';
                        break;
                    }
                }
            }

            //아무것도 없는 곳에 던진 경우
            if (col == C || col < 0) {
                col = C - 1;
            }

            //미네랄이 공중에 떠 있는지 확인
            PriorityQueue<Mineral> pq = new PriorityQueue<>();
            List<Mineral> mineralList = fineNewCluster(R, C, cave, pq);

            //움직일 미네랄이 없으면 다음 던지기로
            if (mineralList.isEmpty()) {
                continue;
            }
            Collections.sort(mineralList);
            moveMineral(R, C, cave, mineralList, pq);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(cave[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static List<Mineral> fineNewCluster(int R, int C, char[][] cave, PriorityQueue<Mineral> pq) {
        //초기화
        boolean[][] visited = new boolean[R][C];
        List<Mineral> mineralList = new ArrayList<>();

        //새로운 클러스터 찾기
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {

                //방문하지 않은 미네랄
                if (visited[i][j] || cave[i][j] == '.') {
                    continue;
                }

                pq.offer(new Mineral(i, j));
                boolean isNewCluster = true;

                while (!pq.isEmpty()) {
                    Mineral temp = pq.poll();

                    for (int k = 0; k < 4; k++) {
                        int nRow = temp.row + dR[k];
                        int nCol = temp.col + dC[k];

                        if (inRange(R, C, nRow, nCol) && !visited[nRow][nCol] && cave[nRow][nCol] == 'x') {
                            visited[nRow][nCol] = true;
                            Mineral mineral = new Mineral(nRow, nCol);
                            mineralList.add(mineral);
                            pq.offer(mineral);
                            //현재 클러스터가 땅에 닿았으면
                            if (nRow == R - 1) {
                                isNewCluster = false;
                            }
                        }
                    }
                }

                //움직일 수 있는 새로운 클러스터
                if (isNewCluster) {
                    break;
                } else {
                    mineralList.clear();
                }

            }
        }

        return mineralList;
    }

    private static void moveMineral(int R, int C, char[][] cave, List<Mineral> mineralList, PriorityQueue<Mineral> pq) {

        boolean move = true;

        Set<Mineral> bottomSet = new HashSet<>();
        for (Mineral mineral : mineralList) {
            if (cave[mineral.row + 1][mineral.col] == '.') {
                bottomSet.add(mineral);
            }
        }

        //모든 미네랄이 밑에 미네랄이 있는 경우 or 이미 땅인 경우
        if (bottomSet.isEmpty()) {
            return;
        }

        while (move) {
            //한칸 아래 이동 좌표로 변경
            for (Mineral mineral : mineralList) {
                mineral.row++;
                pq.offer(mineral);
                //하단의 미네랄들이 이미 닿은 경우거나 아래가 미네랄 인 경우
                if (bottomSet.contains(mineral) && (mineral.row >= R || cave[mineral.row][mineral.col] == 'x')) {
                    move = false;
                }
            }

            //떨어질 수 있다면
            while (move && !pq.isEmpty()) {
                Mineral mineral = pq.poll();
                cave[mineral.row - 1][mineral.col] = '.';
                cave[mineral.row][mineral.col] = 'x';
            }
        }
    }

    private static boolean inRange(int R, int C, int nRow, int nCol) {
        return 0 <= nRow && nRow < R && 0 <= nCol && nCol < C;
    }
}


