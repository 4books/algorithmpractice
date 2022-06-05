package dfsbfsalgo.no8;
/*
8. 송아지 찾기 1(BFS : 상태트리탐색)
설명

현수는 송아지를 잃어버렸다. 다행히 송아지에는 위치추적기가 달려 있다.

현수의 위치와 송아지의 위치가 수직선상의 좌표 점으로 주어지면 현수는 현재 위치에서 송아지의 위치까지 다음과 같은 방법으로 이동한다.

송아지는 움직이지 않고 제자리에 있다.

현수는 스카이 콩콩을 타고 가는데 한 번의 점프로 앞으로 1, 뒤로 1, 앞으로 5를 이동할 수 있다.

최소 몇 번의 점프로 현수가 송아지의 위치까지 갈 수 있는지 구하는 프로그램을 작성하세요.


입력
첫 번째 줄에 현수의 위치 S와 송아지의 위치 E가 주어진다. 직선의 좌표 점은 1부터 10,000까지이다.


출력
점프의 최소횟수를 구한다. 답은 1이상이며 반드시 존재합니다.


예시 입력 1

5 14
예시 출력 1

3
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {


    //1번 단순 무식하게 풀어보기
    public static String solution(int n, int e) {
        //n 현수 위치, e 송아지 위치
        int answer = 0;
        int f = 1, sf = 5, b = -1; //앞점프, 슈퍼 앞점프, 뒤로 점프
        int dis = e - n; //현수와 송아지 사이 거리
        if (dis > 0) {
            answer += (dis / sf);//슈퍼 앞점프로 갈수 있는 거
            int rest = dis % sf;// 나머지 거리
            if (rest == 4) {// 남은 거리가 4인 경우 슈퍼점프하고 뒤로 한칸가는게 최소 이므로 +2
                answer += 2;
            } else {
                answer += (rest * f); //남은 거리 * 앞 점프
            }

        } else if (dis < 0) { //음수 이면 뒤로만 가야됨
            answer = b * dis;
        }

        return String.valueOf(answer);
    }

    //2번 bfs 로 
    public static String bfs(int n, int e) {
        //n 현수위치, e 송아지 위치

        int[] dis = {1, -1, 5}; //이동 가능 범위
        boolean[] checks = new boolean[10001]; //조건이 10000까지 이미 체크한 것은 체크하지 않겠다는 의미

        checks[n] = true; //현재 현수 위치는 이미 체크 됐다 처리
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(n); //현수 위치를 넣음

        int level = 0; //최소 이동 범위
        while (!queue.isEmpty()) {
            int length = queue.size(); //현재 레벨의 원소 갯수
            for (int i = 0; i < length; i++) {
                int tmp = queue.poll(); //queue 에 있는 좌표 값 가져옴

                //다음 레벨 노드 세팅
                for (int j = 0; j < dis.length; j++) { // 이동 가능한 범위
                    int x = tmp + dis[j]; //체크하는 노드에 자식 노드 3가지를 넣음
                    if (x == e) { //세팅중  현재 위치가 송아지 위치이면 다음 레벨 리턴
                        return String.valueOf(level + 1);
                    }
                    //이미 체크된 노드 이거나 범위에 벗어나면 자식 노드로 추가하지 않음
                    if (1 <= x && x <= 10000 && !checks[x]) {
                        checks[x] = true; //지금 위치는 체크 했다 표시
                        queue.offer(x);
                    }
                }
            }
            //현재 레벨 모드 체크 완료 되면 다음 레벨로 
            level++;
        }

        return "";
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");
        int s = Integer.parseInt(tmp[0]);
        int e = Integer.parseInt(tmp[1]);

        //1번 단순 무식하게 풀어보기
//        bw.write(solution(s, e));
        //2번 bfs로 풀기
        bw.write(bfs(s, e));
        bw.flush();
        bw.close();
    }

}
