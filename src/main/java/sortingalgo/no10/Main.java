package sortingalgo.no10;
/*
10. 마구간 정하기(결정알고리즘)
설명

N개의 마구간이 수직선상에 있습니다. 각 마구간은 x1, x2, x3, ......, xN의 좌표를 가지며, 마구간간에 좌표가 중복되는 일은 없습니다.

현수는 C마리의 말을 가지고 있는데, 이 말들은 서로 가까이 있는 것을 좋아하지 않습니다. 각 마구간에는 한 마리의 말만 넣을 수 있고,

가장 가까운 두 말의 거리가 최대가 되게 말을 마구간에 배치하고 싶습니다.

C마리의 말을 N개의 마구간에 배치했을 때 가장 가까운 두 말의 거리가 최대가 되는 그 최대값을 출력하는 프로그램을 작성하세요.


입력
첫 줄에 자연수 N(3<=N<=200,000)과 C(2<=C<=N)이 공백을 사이에 두고 주어집니다.

둘째 줄에 마구간의 좌표 xi(0<=xi<=1,000,000,000)가 차례로 주어집니다.


출력
첫 줄에 가장 가까운 두 말의 최대 거리를 출력하세요.


예시 입력 1

5 3
1 2 8 4 9
예시 출력 1

3
 */

import java.io.*;
import java.util.Arrays;

public class Main {

    //dist 이 거리로 arr 에 몇마리 (count) 를 배치할 수 있는가
    public static int count(int[] arr, int dist) {
        //몇마리 배치했는가
        int count = 1;
        //이전에 배치한 마구간 포지션
        int endPosition = arr[0];//첫번째 말은 제일 왼쪽에 배치, count = 1
        for (int i = 1; i < arr.length; i++) {
            if((arr[i] - endPosition) >= dist){
                count++;
                endPosition = arr[i];
            }
        }
        return count;
    }

    //n 마구간 갯수
    //c 말 수
    //arr 마구간 좌표
    public static String solution(int n, int c, int[] arr) {
        int answer = 0;
        Arrays.sort(arr);

        //항상 최소 값
        //만약 5 6 7... 이런식으로 있다면 최대 거리는 1인데 답이 나오지 않음
        int min = 1;
        int max = Arrays.stream(arr).max().getAsInt(); // 좌표의 최대 값

        while (min <= max) {
            int mid = (min + max) / 2; // 가장 가까운 두 말의 최대거리 값으로 가정
            //mid 로 말을 c 만큼 배치 했을때 유효한지 확인한다.
            //(= mid 간격으로 말을 배치했을 때 수를 리턴한다)
            if (count(arr, mid) >= c) { //count 리턴이 말의 수보다 많다면 정답 후보
                answer = mid;
                min = mid + 1;
            } else { //거리가 너무 넓어서 다 배치할 수 없을때
                max = mid - 1;
            }
        }

        return String.valueOf(answer);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");

        int n = Integer.parseInt(tmp[0]);
        int c = Integer.parseInt(tmp[1]);
        tmp = br.readLine().split(" ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tmp[i]);
        }
        bw.write(solution(n, c, arr));
        bw.flush();
        bw.close();
    }

}
