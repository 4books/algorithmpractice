package ajd.q3;
/*
끝까지 가는 경로의 최솟값..
(0,0)부터 시작해서.. grid의 가장 오른쪽 아래 끝까지 간다..
각 grid의 cell에는 고유 값이 있고 이때 가장 오른쪽 아래 까지 갈때 값이 제일 적게 걸리는 걸 찾아라..
 */

import java.util.PriorityQueue;

class Solution {

    class Node implements Comparable<Node> {
        int value;
        int x;
        int y;

        Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public int solution(int[][] grid) {

        int[] dx = {-1, 1, 0, 0}; // 상하좌우
        int[] dy = {0, 0, -1, 1};

        int n = grid.length;
        int m = grid[0].length;

        int[][] dynamic = new int[n][m];

        // dynamic 배열을 최대값으로 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dynamic[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dynamic[0][0] = grid[0][0];
        pq.offer(new Node(dynamic[0][0], 0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            // 현재 위치까지의 누적 합이 이미 저장된 값보다 크면 스킵
            if (curr.value > dynamic[curr.x][curr.y]) {
                continue;
            }

            // 목표 지점에 도달한 경우 종료 가능 (필요시)
            if (curr.x == n - 1 && curr.y == m - 1) {
                return curr.value;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int newValue = curr.value + grid[nx][ny];
                    if (newValue < dynamic[nx][ny]) {
                        dynamic[nx][ny] = newValue;
                        pq.offer(new Node(newValue, nx, ny));
                    }
                }
            }
        }

        return dynamic[n - 1][m - 1];
    }
}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = {{1,7,3,5},{9,1,1,1}};
        int expect = 11;
        System.out.println("expect = " + expect);
        System.out.println("solution = " + s.solution(grid));
    }



}
