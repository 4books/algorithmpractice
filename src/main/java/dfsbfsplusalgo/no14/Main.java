package dfsbfsplusalgo.no14;


/*
14. 피자 배달 거리(삼성 SW역량평가 기출문제 : DFS활용)
설명

N×N 크기의 도시지도가 있습니다. 도시지도는 1×1크기의 격자칸으로 이루어져 있습니다.

각 격자칸에는 0은 빈칸, 1은 집, 2는 피자집으로 표현됩니다. 각 격자칸은 좌표(행번호, 열 번호)로 표현됩니다.

행번호는 1번부터 N번까지이고, 열 번호도 1부터 N까지입니다.

도시에는 각 집마다 “피자배달거리”가 았는데 각 집의 피자배달거리는 해당 집과 도시의 존재하는

피자집들과의 거리 중 최소값을 해당 집의 “피자배달거리”라고 한다.

집과 피자집의 피자배달거리는 |x1-x2|+|y1-y2| 이다.

예를 들어, 도시의 지도가 아래와 같다면

Image1.jpg

(1, 2)에 있는 집과 (2, 3)에 있는 피자집과의 피자 배달 거리는 |1-2| + |2-3| = 2가 된다.

최근 도시가 불경기에 접어들어 우후죽순 생겼던 피자집들이 파산하고 있습니다.

도시 시장은 도시에 있는 피자집 중 M개만 살리고 나머지는 보조금을 주고 폐업시키려고 합니다.

시장은 살리고자 하는 피자집 M개를 선택하는 기준으로 도시의 피자배달거리가 최소가 되는 M개의 피자집을 선택하려고 합니다.

도시의 피자 배달 거리는 각 집들의 피자 배달 거리를 합한 것을 말합니다.


입력
첫째 줄에 N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 12)이 주어진다.

둘째 줄부터 도시 정보가 입력된다.


출력
첫째 줄에 M개의 피자집이 선택되었을 때 도시의 최소 피자배달거리를 출력한다.


예시 입력 1

4 4
0 1 2 0
1 0 2 1
0 2 1 2
2 0 1 2
예시 출력 1

6
 */

import java.io.*;
import java.util.ArrayList;

public class Main {

    static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n; //board 사이즈
    static int m; //살릴 피자집 갯수
    static int len; //현재 피자집들의 수

    static ArrayList<Point> houseList = new ArrayList<>(); //집 위치
    static ArrayList<Point> pizzaList = new ArrayList<>(); //피자집 위치

    static int[] combination; //선택 되는 피자 수
    static int answer = Integer.MAX_VALUE;

    //level == m 선택되는 피자집 수
    //start 다음으로 선택되는 피자집
    public static void dfs(int level, int start) {
        if(level == m){ //살릴 피자집의 조합이 되었을 때
            int sum = 0;
            //집들을 가져옴
            for (Point house : houseList) {
                int dis = Integer.MAX_VALUE; //현재 집과 각 피자집 간의 배달 최소 거리
                for (int index : combination) { //선택된 피자집들의 index
                    Point pizza = pizzaList.get(index);
                    dis = Math.min(
                            dis,
                            Math.abs(house.x - pizza.x) + Math.abs(house.y - pizza.y)
                    );
                }
                sum += dis;
            }
            //sum 이 현재 최소피자배달거리보다 작다면 바꿔줌
            answer = Math.min(answer, sum);
        } else {
            for (int i = start; i < len; i++) { //피자 조합을 뽑는 것 (Combination)
                combination[level] = i;
                dfs(level + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");

        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);

        combination = new int[m];

        for (int i = 0; i < n; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int tmpInt = Integer.parseInt(tmp[j]);
                if(tmpInt == 1) {
                    houseList.add(new Point(i, j));
                } else if(tmpInt == 2){
                    pizzaList.add(new Point(i, j));
                }
            }
        }
        len = pizzaList.size(); //현재 피자집들의 수

        dfs(0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}


