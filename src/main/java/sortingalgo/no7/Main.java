package sortingalgo.no7;
/*
7. 좌표 정렬
설명

N개의 평면상의 좌표(x, y)가 주어지면 모든 좌표를 오름차순으로 정렬하는 프로그램을 작성하세요.

정렬기준은 먼저 x값의 의해서 정렬하고, x값이 같을 경우 y값에 의해 정렬합니다.


입력
첫째 줄에 좌표의 개수인 N(3<=N<=100,000)이 주어집니다.

두 번째 줄부터 N개의 좌표가 x, y 순으로 주어집니다. x, y값은 양수만 입력됩니다.


출력
N개의 좌표를 정렬하여 출력하세요.


예시 입력 1

5
2 7
1 3
1 2
2 5
3 6
예시 출력 1

1 2
1 3
2 5
2 7
3 6
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Point implements Comparable<Point> {
    public int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        //양수가 나오면 this 와 o 의 위치를 바꾼다.
        //음수가 나오면 그대로 냅둔다
        if(this.x == o.x){
//            return this.y - o.y; //오름차순
            return o.y - this.y; // 내림차순
        } else {
//            return this.x - o.x; //오름차순
            return o.x - this.x; // 내림차순
        }
    }
}

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

//        Point[] arr = new Point[n]; //또는

        List<Point> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            arr.add(new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
//            arr[i] = new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])); //또는
        }
//        Arrays.sort(arr, Point::compareTo); //또는
        Collections.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (Point point : arr) {
            sb.append(point.x).append(" ").append(point.y).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
