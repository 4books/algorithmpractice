package greedyalgo.no4;

/*
4. 최대 수입 스케쥴(PriorityQueue 응용문제)
설명

현수는 유명한 강연자이다. N개이 기업에서 강연 요청을 해왔다. 각 기업은 D일 안에 와서 강연을 해 주면 M만큼의 강연료를 주기로 했다.

각 기업이 요청한 D와 M를 바탕으로 가장 많을 돈을 벌 수 있도록 강연 스케쥴을 짜야 한다.

단 강연의 특성상 현수는 하루에 하나의 기업에서만 강연을 할 수 있다.


입력
첫 번째 줄에 자연수 N(1<=N<=10,000)이 주어지고, 다음 N개의 줄에 M(1<=M<=10,000)과 D(1<=D<=10,000)가 차례로 주어진다.


출력
첫 번째 줄에 최대로 벌 수 있는 수입을 출력한다.


예시 입력 1

6
50 2
20 1
40 2
60 3
30 3
30 1
예시 출력 1

150
 */

import java.io.*;
import java.util.*;

public class Main {

    static class Lesson implements Comparable<Lesson> {
        public Integer money;
        public Integer date;

        public Lesson(Integer money, Integer date) {
            this.money = money;
            this.date = date;
        }

        @Override
        public int compareTo(Lesson l) {
            return l.date - this.date; //음수이면 바꾸지 않음. 내림차순
        }
    }

    static List<Lesson> lessonList = new ArrayList<>();
    static Integer maxDate = 0;

    static Integer n; //Lesson 의 갯수

    public static String solution() {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Collections.sort(lessonList);
        int j = 0;
        for (int i = maxDate; i >= 1; i--) {
            for (; j < n; j++) {
                if(lessonList.get(j).date < i) {
                    break;
                }
                pq.offer(lessonList.get(j).money);
            }
            if(!pq.isEmpty()){
                answer += pq.poll();
            }
        }

        return String.valueOf(answer);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            int money = Integer.parseInt(tmp[0]);
            int date = Integer.parseInt(tmp[1]);
            lessonList.add(new Lesson(money, date));
            if (date > maxDate) {
                maxDate = date;
            }
        }

        bw.write(solution());
        bw.flush();
        bw.close();
    }
}


