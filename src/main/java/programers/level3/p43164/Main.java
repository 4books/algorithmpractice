package programers.level3.p43164;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/43164

문제 설명
주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.

항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한사항
모든 공항은 알파벳 대문자 3글자로 이루어집니다.
주어진 공항 수는 3개 이상 10,000개 이하입니다.
tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
주어진 항공권은 모두 사용해야 합니다.
만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
입출력 예
tickets	return
[["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]	["ICN", "JFK", "HND", "IAD"]
[["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]]	["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
입출력 예 설명
예제 #1

["ICN", "JFK", "HND", "IAD"] 순으로 방문할 수 있습니다.

예제 #2

["ICN", "SFO", "ATL", "ICN", "ATL", "SFO"] 순으로 방문할 수도 있지만 ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"] 가 알파벳 순으로 앞섭니다.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    List<String> candidates = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        boolean[] isUse = new boolean[tickets.length];

        //시작은 ICN
        dfs(0, tickets.length, "ICN", tickets, isUse, "ICN");

        //경로가 여러개 이면 알파벳 순서가 앞서는 경로
        Collections.sort(candidates);
        return candidates.get(0).split(" ");
    }

    public void dfs(int level, int maxLevel, String start, String[][] tickets, boolean[] isUse, String value) {
        if (level == maxLevel) {
            candidates.add(value);
        } else {
            for (int i = 0; i < maxLevel; i++) {
                //start 와 티켓의 출발 지점이 동일
                //사용하지 않은 티켓
                if(start.equals(tickets[i][0]) && !isUse[i]){
                    isUse[i] = true;
                    String newStart = tickets[i][1];
                    dfs(level + 1, maxLevel, newStart, tickets, isUse, value + " " + newStart);
                    isUse[i] = false;
                }
            }
        }
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};

        String[] answer = s.solution(tickets);
        String expect = "ICN JFK HND IAD";
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
        System.out.println("expect = " + expect);

        s.candidates.clear();

        tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        answer = s.solution(tickets);
        expect = "ICN ATL ICN SFO ATL SFO";
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
        System.out.println("expect = " + expect);

    }
}
