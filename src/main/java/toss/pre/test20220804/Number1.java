package toss.pre.test20220804;
/*

 */

import java.util.*;

class Solution {
    public int[][] solution2(int servers, boolean sticky, int[] requests) {
        int[][] answer = new int[servers][];

        //서버 리스트
        List<Integer>[] list = new List[servers];
        for (int i = 0; i < servers; i++) {
            list[i] = new ArrayList<>();
        }

        //round robin
        if (!sticky) {
            for (int i = 0; i < requests.length; i++) {
                int target = i % servers;
                list[target].add(requests[i]);
            }

        } else {

            //응답 처리 set
            Set<Integer>[] set = new Set[servers];
            for (int i = 0; i < set.length; i++) {
                set[i] = new HashSet<>();
            }

            for (int i = 0; i < requests.length; i++) {
                int rq = requests[i];

                boolean isExist = false;
                int target = i % servers;
                for (int j = 0; j < servers; j++) {
                    if (set[j].contains(rq)) {
                        target = j;
                        list[target].add(rq);
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    int lower = 987654321;
                    int sv = 0;
                    for (int j = 0; j < set.length; j++) {
                        if (lower > set[j].size()) {
                            lower = set[j].size();
                            sv = j;
                        }
                    }
                    list[sv].add(rq);
                    set[sv].add(rq);
                }
            }
        }


        for (int i = 0; i < list.length; i++) {
            answer[i] = new int[list[i].size()];
            for (int j = 0; j < list[i].size(); j++) {
                answer[i][j] = list[i].get(j);
            }
        }


        return answer;
    }

    public int[][] solution(int servers, boolean sticky, int[] requests) {
        int[][] answer = new int[servers][];

        //서버 리스트
        ArrayList<Integer>[] list = new ArrayList[servers + 1];
        for (int i = 0; i <= servers; i++) {
            list[i] = new ArrayList<>();
        }

        int[] preTarget = new int[requests.length + 1];
        Arrays.fill(preTarget, Integer.MAX_VALUE);

        int target = 1;
        for (int i = 0; i < requests.length; i++) {
            int rq = requests[i];

            //sticky
            if(sticky){
                if (preTarget[rq] == Integer.MAX_VALUE) {
                    //최초 요청
                    preTarget[rq] = target;
                } else {
                    //중복 요청
                    target = preTarget[rq];
                }
            }
            //기본은 round 로빈 방식
            list[target].add(rq);

            target += 1;
            if (target > servers) {
                target = 1;
            }
        }

        for (int i = 1; i < list.length; i++) {
            answer[i - 1] = new int[list[i].size()];
            for (int j = 0; j < list[i].size(); j++) {
                answer[i - 1][j] = list[i].get(j);
            }
        }

        return answer;
    }
}

public class Number1 {


    public static void main(String[] args) {

        Solution s = new Solution();
        int[][] solution;
//        solution = s.solution(2, false, new int[]{1, 2, 3, 4});
//        solution = s.solution(2, true, new int[]{1, 1, 2, 2});
        solution = s.solution(2, true, new int[]{1, 2, 2, 3, 4, 1});
        int[][] solution2 = s.solution2(2, true, new int[]{1, 2, 2, 3, 4, 1});

        for (int i = 0; i < solution.length; i++) {
            int[] row = solution[i];
            for (int j = 0; j < row.length; j++) {
                System.out.println(solution[i][j]);
                System.out.println(solution2[i][j]);
            }
        }
//        solution = s.solution(3, true, new int[]{1, 2, 2, 3, 4, 1});
        //정답
        //1번 1 4 1
        //2번 2 2
        //3번 3

        System.out.println();
        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
