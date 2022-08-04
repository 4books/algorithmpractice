package toss.pre.n1;
/*

 */

import java.util.*;

class Solution {
    public int[][] solution(int servers, boolean sticky, int[] requests) {
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

            //TODO Array 로 다시 풀어보기

            int[] targetServers = new int[requests.length];
            Arrays.fill(targetServers, 987654321);

            int last = 0;
            for (int i = 0; i < requests.length; i++) {
                int rq = requests[i];

                if (targetServers[rq] == 987654321) {
                    //최초 요청
                    int target = 0;
                    int min = Integer.MAX_VALUE;
                    for (int j = 0; j < list.length; j++) {
                        if (list[j].size() < min) {
                            min = list[j].size();
                            target = j;
                        }
                    }
                    list[target].add(rq);
                    targetServers[rq] = target;
                } else {
                    //다음 요청
                    int target = targetServers[rq];
                    last = target;
                    list[target].add(rq);
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
}

public class Number1 {


    public static void main(String[] args) {

        Solution s = new Solution();
        int[][] solution;
        solution = s.solution2(2, false, new int[]{1, 2, 3, 4});
        solution = s.solution2(2, true, new int[]{1, 1, 2, 2});
        solution = s.solution2(2, true, new int[]{1, 2, 2, 3, 4, 1});


        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
