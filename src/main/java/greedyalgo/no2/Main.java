package greedyalgo.no2;

/*
2. 회의실 배정
설명

한 개의 회의실이 있는데 이를 사용하고자 하는 n개의 회의들에 대하여 회의실 사용표를 만들려고 한다.

각 회의에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 최대수의 회의를 찾아라.

단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.


입력
첫째 줄에 회의의 수 n(1<=n<=100,000)이 주어진다. 둘째 줄부터 n+1 줄까지 각 회의의 정보가 주어지는데

이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 회의시간은 0시부터 시작한다.

회의의 시작시간과 끝나는 시간의 조건은 (시작시간 <= 끝나는 시간)입니다.


출력
첫째 줄에 최대 사용할 수 있는 회의 수를 출력하여라.


예시 입력 1

5
1 4
2 3
3 5
4 6
5 7
예시 출력 1

3
예시 입력 2

3
3 3
1 3
2 3
예시 출력 2

2
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static class Time implements Comparable<Time>{
        public int start;
        public int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time r) {
            if(this.end == r.end){ //오름차순
                return this.start - r.start;
            }
            return this.end - r.end;
        }
    }

    static List<Time> timeList = new ArrayList<>();

    public static String solution() {
        int answer = 0;
        int endTime = 0;
        Collections.sort(timeList);

        for (Time time : timeList) {
            if(time.start >= endTime){
                answer++;
                endTime = time.end;
            }
        }
        return String.valueOf(answer);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            timeList.add(new Time(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
        }

        bw.write(solution());
        bw.flush();
        bw.close();
    }
}


