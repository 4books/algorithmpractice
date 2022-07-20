package backjoon.gold4.p16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int max = Integer.MIN_VALUE;
    static List<Character> oprList;
    static List<Integer> numList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String ori = br.readLine();

        oprList = new ArrayList<>();
        numList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            char c = ori.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                oprList.add(c);
                continue;
            }
            numList.add(Character.getNumericValue(c));
        }

        dfs(numList.get(0), 0);

        System.out.println(max);

    }

    private static void dfs(Integer result, int oprIndex) {
        //종료 조건
        if (oprIndex >= oprList.size()) {
            max = Math.max(max, result);
            return;
        }
        
        int num1 = result;
        char opr = oprList.get(oprIndex);
        int num2 = numList.get(oprIndex + 1);

        //괄호가 없는 경우
        int r1 = cal(num1, opr, num2);
        dfs(r1, oprIndex + 1);

        //괄호가 있는 경우
        if (oprIndex + 1 < oprList.size()) {
            //r1 의 오른쪽 값 계산
            int r2 = cal(numList.get(oprIndex + 1), oprList.get(oprIndex + 1), numList.get(oprIndex + 2));
            //result + r2를 더해 다음 dfs
            //현재 result 와 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자의 인덱스를 넘김.
            dfs(cal(result, oprList.get(oprIndex), r2), oprIndex + 2);
        }
    }

    private static int cal(int num1, Character opr, int num2) {
        if (opr == '+') {
            return num1 + num2;
        } else if (opr == '-') {
            return num1 - num2;
        }
        return num1 * num2;
    }
}
