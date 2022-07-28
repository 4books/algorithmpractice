package programers.level3.p84021;
/*

 */

import java.util.*;

class Solution2 {

    static int[] dX = {1, -1, 0, 0};
    static int[] dY = {0, 0, 1, -1};

    class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x != o.x) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
    }

    class Figure implements Comparable<Figure> {
        List<Point> ps;
        boolean isUse;

        public Figure(List<Point> ps) {
            this.ps = ps;
            this.isUse = false;
        }

        @Override
        public int compareTo(Figure o) {
            return this.ps.size() - o.ps.size();
        }
    }

    public int solution(int[][] game_board, int[][] table) {

        int answer = 0;
        int range = game_board.length;
        boolean[][] visited = new boolean[range][range];

        //빈 공간 모음
        List<Figure> emptyList = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {

                if (game_board[i][j] == 0 && !visited[i][j]) {
                    List<Point> list = new ArrayList<>();
                    list.add(new Point(i, j));

                    bfs(game_board, list, 1, range, visited);
                    emptyList.add(new Figure(list));

                }
                visited[i][j] = true;
            }
        }

        visited = new boolean[range][range];
        //도형 모음
        List<Figure> figureList = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                if (table[i][j] == 1 && !visited[i][j]) {
                    List<Point> ps = new ArrayList<>();
                    ps.add(new Point(i, j));

                    bfs(table, ps, 0, range, visited);
                    figureList.add(new Figure(ps));
                }
                visited[i][j] = true;
            }
        }

        Collections.sort(emptyList);
        Collections.sort(figureList);

        int minimum = figureList.get(0).ps.size();

        for (Figure empty : emptyList) {
            if (empty.ps.size() < minimum) {
                continue;
            }
            for (Figure figure : figureList) {
                if (figure.isUse || empty.ps.size() != figure.ps.size()) {
                    continue;
                }

                if (checkSame(empty, figure)) {
                    figure.isUse = true;
                    answer += empty.ps.size();
                    break;
                }
            }
        }

        return answer;
    }

    private boolean checkSame(Figure empty, Figure figure) {

        int sourceDis, targetDis;
        Collections.sort(empty.ps);

        //공간의 가로 세로 사이즈
        int ew = empty.ps.get(empty.ps.size() - 1).x - empty.ps.get(0).x + 1;
        int eh = empty.ps.get(empty.ps.size() - 1).y - empty.ps.get(0).y + 1;

        boolean isSame = true;
        //회전 횟수
        for (int r = 0; r < 4; r++) {
            rotate(figure, r);
            Collections.sort(figure.ps);

            //도형의 가로 세로 사이즈
            int fw = figure.ps.get(figure.ps.size() - 1).x - figure.ps.get(0).x + 1;
            int fh = figure.ps.get(figure.ps.size() - 1).y - figure.ps.get(0).y + 1;

            //가로 세로 사이즈가 다르다면 진행 불필요
            if (ew != fw || eh != fh) {
                isSame = false;
                continue;
            }

            //source 가 될 기준점
            Point es = empty.ps.get(0);
            Point fs = figure.ps.get(0);

            for (int i = 1; i < empty.ps.size(); i++) {
                //target
                Point et = empty.ps.get(i);
                Point ft = figure.ps.get(i);

                //source 와 target 의 거리가 같은지 확인 (절대값 X 모양이 같아야함)
                if (es.x == et.x) {
                    sourceDis = es.y - et.y;
                    targetDis = fs.y - ft.y;
                } else if (es.y == et.y) {
                    sourceDis = es.x - et.x;
                    targetDis = fs.x - ft.x;
                } else { //같지 않을때
                    sourceDis = (es.x - et.x) + (es.y - et.y);
                    targetDis = (fs.x - ft.x) + (fs.y - ft.y);
                }

                if (sourceDis == targetDis) {
                    isSame = true;
                } else {
                    isSame = false;
                    break;
                }

            }
            //빈 공간에 들어갈 수 있다면 회전 종료
            if (isSame) {
                break;
            }
        }
        return isSame;
    }

    private void rotate(Figure figure, int angle) {
        //90 도 회전 (x, y) -> (y, -x)
        if (angle > 0) {
            for (Point p : figure.ps) {
                int tmp = p.x;
                p.x = p.y;
                p.y = -tmp;
            }
        }
    }

    private void bfs(int[][] map, List<Point> list, int avoid, int range, boolean[][] visited) {

        //초기화
        Queue<Point> queue = new LinkedList<>();

        queue.offer(list.get(0));
        visited[list.get(0).x][list.get(0).y] = true;

        while (!queue.isEmpty()) {
            Point tmp = queue.poll();

            int x = tmp.x;
            int y = tmp.y;

            for (int d = 0; d < 4; d++) {
                int nX = x + dX[d];
                int nY = y + dY[d];

                if (nX < 0 || nX >= range || nY < 0 || nY >= range || visited[nX][nY] || map[nX][nY] == avoid) {
                    continue;
                }

                visited[nX][nY] = true;

                Point next = new Point(nX, nY);
                queue.offer(next);
                list.add(next);
            }
        }
    }

}


public class Main2 {


    public static void main(String[] args) {

        Solution2 s = new Solution2();

        int answer, expect;

        answer = s.solution(new int[][]{{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}},
                new int[][]{{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}});
        expect = 14;
        System.out.println(answer + " " + expect);

        answer = s.solution(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 1}},
                new int[][]{{1, 1, 1}, {1, 0, 0}, {0, 0, 0}});
        expect = 0;
        System.out.println(answer + " " + expect);

        answer = s.solution(new int[][]{{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1}, {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0}, {1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0}, {0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}},
                new int[][]{{1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0}, {0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0}, {1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1}, {1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1}, {0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1}, {1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1}, {1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1}, {1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1}});
        expect = 54;
        System.out.println(answer + " " + expect);
    }
}

