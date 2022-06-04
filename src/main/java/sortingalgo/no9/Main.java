package sortingalgo.no9;
/*
9. 뮤직비디오(결정알고리즘)
설명

지니레코드에서는 불세출의 가수 조영필의 라이브 동영상을 DVD로 만들어 판매하려 한다.

DVD에는 총 N개의 곡이 들어가는데, DVD에 녹화할 때에는 라이브에서의 순서가 그대로 유지되어야 한다.

순서가 바뀌는 것을 우리의 가수 조영필씨가 매우 싫어한다. 즉, 1번 노래와 5번 노래를 같은 DVD에 녹화하기 위해서는

1번과 5번 사이의 모든 노래도 같은 DVD에 녹화해야 한다. 또한 한 노래를 쪼개서 두 개의 DVD에 녹화하면 안된다.

지니레코드 입장에서는 이 DVD가 팔릴 것인지 확신할 수 없기 때문에 이 사업에 낭비되는 DVD를 가급적 줄이려고 한다.

고민 끝에 지니레코드는 M개의 DVD에 모든 동영상을 녹화하기로 하였다. 이 때 DVD의 크기(녹화 가능한 길이)를 최소로 하려고 한다.

그리고 M개의 DVD는 모두 같은 크기여야 제조원가가 적게 들기 때문에 꼭 같은 크기로 해야 한다.


입력
첫째 줄에 자연수 N(1≤N≤1,000), M(1≤M≤N)이 주어진다.

다음 줄에는 조영필이 라이브에서 부른 순서대로 부른 곡의 길이가 분 단위로(자연수) 주어진다.

부른 곡의 길이는 10,000분을 넘지 않는다고 가정하자.


출력
첫 번째 줄부터 DVD의 최소 용량 크기를 출력하세요.


예시 입력 1

9 3
1 2 3 4 5 6 7 8 9
예시 출력 1

17
힌트

설명 : 3개의 DVD용량이 17분짜리이면 (1, 2, 3, 4, 5) (6, 7), (8, 9) 이렇게 3개의 DVD로 녹음을 할 수 있다.

17분 용량보다 작은 용량으로는 3개의 DVD에 모든 영상을 녹화할 수 없다.
 */

import java.io.*;
import java.util.Arrays;

public class Main {

    //capacity = DVD 용량의 후보값
    //return DVD 갯수
    public static int count(int[] arr, int capacity) {
        int cnt = 1; //DVD 갯수
        int sum = 0; //DVD 안에 노래의 총 분
        for (int i : arr) {
            //dvd 용량 보다 크다면?
            //dvd 갯수 추가 및 sum = i 로 다시 시작
            if((sum + i) > capacity){
                cnt++;
                sum = i;
            } else { //작다면 더함
                sum += i;
            }
        }

        return cnt;

    }

    public static String solution(int n, int target, int[] arr) {
        int answer = 0;
        //target 은 원하는 DVD 갯수 
        int min = Arrays.stream(arr).max().getAsInt(); //DVD 용량의 최소값,  arr 에서 가장 큰 값
        int max = Arrays.stream(arr).sum(); //DVD 용량의 최대값
        while (min <= max) {
            int mid = (min + max) / 2; //DVD 용량의 후보값
            //count()는 이 이 노래들이 mid 용량의 크기 DVD 몇 장으로 가능한지 리턴함
            if(count(arr, mid) <= target){ //작거나 같다면? 정답 후보
                answer = mid;
                max = mid - 1;
            } else { //크다면 불가능하므로 좀더 큰값을 탐색
                min = mid + 1;
            }
        }

        return String.valueOf(answer);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");

        int n = Integer.parseInt(tmp[0]);
        int target = Integer.parseInt(tmp[1]);
        tmp = br.readLine().split(" ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tmp[i]);
        }
        bw.write(solution(n, target, arr));
        bw.flush();
        bw.close();
    }

}
