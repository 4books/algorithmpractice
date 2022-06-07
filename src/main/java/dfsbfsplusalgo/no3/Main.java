package dfsbfsplusalgo.no3;
/*
3. 최대점수 구하기(DFS)
설명

이번 정보올림피아드대회에서 좋은 성적을 내기 위하여 현수는 선생님이 주신 N개의 문제를 풀려고 합니다.

각 문제는 그것을 풀었을 때 얻는 점수와 푸는데 걸리는 시간이 주어지게 됩니다.

제한시간 M안에 N개의 문제 중 최대점수를 얻을 수 있도록 해야 합니다.

(해당문제는 해당시간이 걸리면 푸는 걸로 간주한다, 한 유형당 한개만 풀 수 있습니다.)


입력
첫 번째 줄에 문제의 개수N(1<=N<=20)과 제한 시간 M(10<=M<=300)이 주어집니다.

두 번째 줄부터 N줄에 걸쳐 문제를 풀었을 때의 점수와 푸는데 걸리는 시간이 주어집니다.


출력
첫 번째 줄에 제한 시간안에 얻을 수 있는 최대 점수를 출력합니다.


예시 입력 1

5 20
10 5
25 12
15 8
6 3
7 4
예시 출력 1

41
 */

import java.io.*;

class Question {
    public int point;
    public int time;

    public Question(int point, int time) {
        this.point = point;
        this.time = time;
    }
}

public class Main {

    public static int expectPoint = Integer.MIN_VALUE; //기대 점수
    public static int totalTime = Integer.MIN_VALUE; //최대 시간

    public static void dfs(int level, int sumTime, int sumPoint, int n, Question[] arr) {
        //sum 은 문제를 푸는데 걸린 총 시간
        if (sumTime > totalTime) {
            return;
        }

        if (level == n) { //말단 노드까지 왔을 때( := 부분 집합이 완성되었을 때)
            expectPoint = Math.max(expectPoint, sumPoint);
        } else {
            //다음 문제를 푼다 가정
            dfs(level + 1, sumTime + arr[level].time, sumPoint + arr[level].point, n, arr);
            //다음 문제를 풀지 않는다 가정
            dfs(level + 1, sumTime, sumPoint, n, arr);
        }
    }

    public static String solution(int n, Question[] arr) {
        dfs(0, 0, 0, n, arr);

        return String.valueOf(expectPoint);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");

        int n = Integer.parseInt(tmp[0]); //문제 수 
        totalTime = Integer.parseInt(tmp[1]); //최대 시간
        Question[] arr = new Question[n];
        for (int i = 0; i < n; i++) {
            tmp = br.readLine().split(" ");
            //0 점수
            //1 소요시간
            arr[i] = new Question(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
        }

        bw.write(solution(n, arr));
        bw.flush();
        bw.close();
    }
}
