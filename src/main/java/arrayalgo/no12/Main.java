package arrayalgo.no12;

/*
12. 멘토링
설명

현수네 반 선생님은 반 학생들의 수학점수를 향상시키기 위해 멘토링 시스템을 만들려고 합니다.

멘토링은 멘토(도와주는 학생)와 멘티(도움을 받는 학생)가 한 짝이 되어 멘토가 멘티의 수학공부를 도와주는 것입니다.

선생님은 M번의 수학테스트 등수를 가지고 멘토와 멘티를 정합니다.

만약 A학생이 멘토이고, B학생이 멘티가 되는 짝이 되었다면, A학생은 M번의 수학테스트에서 모두 B학생보다 등수가 앞서야 합니다.

M번의 수학성적이 주어지면 멘토와 멘티가 되는 짝을 만들 수 있는 경우가 총 몇 가지 인지 출력하는 프로그램을 작성하세요.


입력
첫 번째 줄에 반 학생 수 N(1<=N<=20)과 M(1<=M<=10)이 주어진다.

두 번째 줄부터 M개의 줄에 걸쳐 수학테스트 결과가 학생번호로 주어진다. 학생번호가 제일 앞에서부터 1등, 2등, ...N등 순으로 표현된다.

만약 한 줄에 N=4이고, 테스트 결과가 3 4 1 2로 입력되었다면 3번 학생이 1등, 4번 학생이 2등, 1번 학생이 3등, 2번 학생이 4등을 의미합니다.


출력
첫 번째 줄에 짝을 만들 수 있는 총 경우를 출력합니다.


예시 입력 1

4 3
3 4 1 2
4 3 2 1
3 1 4 2
예시 출력 1

3
힌트

출력설명

(3, 1), (3, 2), (4, 2)와 같이 3가지 경우의 (멘토, 멘티) 짝을 만들 수 있다.
 */

import java.io.*;

public class Main {
    public static String solution(int numberStudents, int examCount, int[][] grid) {

        int answer = 0;

        //numberStudents 학생 수
        //examCount 시험횟수
        //소스 학생 번호
        for (int i = 1; i <= numberStudents; i++) {

            //타켓학생 번호
            for (int j = 1; j <= numberStudents; j++) {
                if (i == j) continue; //자기자신 패스

                //i 학생이 j 학생보다 등수가 높았을때 수
                int iHighScoreCount = 0;

                //M차 시험
                for (int k = 0; k < examCount; k++) {

                    //M차 시험의 학생 등수 (index) 를 넣음
                    int indexI = -1, indexJ = -1;

                    //만약 i = 3번 이고, j = 1번 이면서
                    // M차 시험 등수가 3 2 1 4 이면
                    // 3번 학생의 index = 0
                    // 1번 학생의 index = 2
                    for (int l = 0; l < numberStudents; l++) {
                        //exam[k] = {3번학생, 2번학생, 1번학생, 4번학생,...  }
                        int[] exam = grid[k]; //K차 시험
                        int rank = exam[l]; //K차 시험의 등수

                        //l은 index(==등수) 0등, 1등,...
                        if (rank == i) {
                            indexI = l;
                        }
                        if (rank == j) {
                            indexJ = l;
                        }

                        //학생수 겁나 많으면 굳이 다 갈필요 없잖아
                        //둘다 등수 찾았으면
                        if (indexI != -1 && indexJ != -1) {
                            break;
                        }

                    }
                    //I가 J보다 성적이 높으면 iHighScoreCount++
                    if (indexI < indexJ) {
                        iHighScoreCount++;
                    }

                    //k번째 검사 했는데 성적이 낮은적이 있다면?
                    //k = 3 이면 4번 확인한거임. 근데 iHighScoreCount 가 4보다 작다면?
                    //만약 시험을 10번 쳤는데 1번째부터 삑났는데 계속 검사할 필요 없잖아?
                    //이미 멘토/멘티 안됨
                    if (k + 1 > iHighScoreCount) {
                        break;
                    }
                }

                //모든 시험 (M번 시험)과 성적이 높은 던 적이 같다면 멘토/멘티 관계 가능!
                if (iHighScoreCount == examCount) {
                    answer++;
                }
            }
        }

        return String.valueOf(answer);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numberStudents, examCount;
        String[] s = br.readLine().split(" ");
        numberStudents = Integer.parseInt(s[0]);//학생수
        examCount = Integer.parseInt(s[1]);//시험횟수

        int[][] grid = new int[examCount][numberStudents];
        for (int i = 0; i < examCount; i++) {
            String[] r = br.readLine().split(" ");
            for (int j = 0; j < numberStudents; j++) {
                grid[i][j] = Integer.parseInt(r[j]);
            }
        }

        bw.write(solution(numberStudents, examCount, grid));
        bw.flush();
        bw.close();
    }

}
