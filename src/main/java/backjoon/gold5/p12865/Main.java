package backjoon.gold5.p12865;
/*
https://www.acmicpc.net/problem/12865

문제
이 문제는 아주 평범한 배낭에 관한 문제이다.

한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.

준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.

입력
첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.

입력으로 주어지는 모든 수는 정수이다.

출력
한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //물건 갯수
        int N = Integer.parseInt(st.nextToken());
        //최대 무게
        int K = Integer.parseInt(st.nextToken());

        //무게 배열
        int[] dy = new int[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken()); //무게
            int worth = Integer.parseInt(st.nextToken()); //가치

            //무게 제한을 둔다.
            //현재 물건 자기 자신이 담길 떄까지만
            for (int j = K; j >= weight; j--) {
                //이전에 담긴 무게의 가치
                int preWorth = dy[j - weight];
                //이미 해당 무게에 담긴 가치와 이전 무게 가치 + 현재 물건의 가치를 합한 것!
                //즉, j = 7에서 4 무게에 8 가치가 있고 여기에 3 무게의 가치 8을 더해서
                //지금 무게 7 에 담긴 가치보다 높은지 확인한다!
                dy[j] = Math.max(dy[j], preWorth + worth); 
            }
        }
        System.out.println(dy[K]);
    }
}


