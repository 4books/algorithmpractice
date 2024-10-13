package programers.level3.p340210;
import java.util.*;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/340210
 */

class Solution {
    public String[] solution(String[] expressions) {
        // 가능한 진법을 저장하는 집합 (2진법부터 9진법까지)
        Set<Integer> possibleBases = new HashSet<>();
        for (int base = 2; base <= 9; base++) {
            possibleBases.add(base);
        }

        List<String> knownEquations = new ArrayList<>();
        List<String> unknownEquations = new ArrayList<>();
        List<String> result = new ArrayList<>();

        // 수식을 분류: 결과값이 주어진 수식과 결과값이 지워진 수식
        for (String expr : expressions) {
            if (expr.contains("= X")) {
                unknownEquations.add(expr);
            } else {
                knownEquations.add(expr);
            }
        }

        // 결과값이 주어진 수식을 통해 가능한 진법을 필터링
        for (String equation : knownEquations) {
            Set<Integer> basesToRemove = new HashSet<>();
            for (int base : possibleBases) {
                if (!isValidEquation(equation, base)) {
                    basesToRemove.add(base);
                }
            }
            possibleBases.removeAll(basesToRemove);
        }

        // **수정 부분 시작: 수식에 사용된 모든 숫자의 자릿수가 해당 진법에서 유효한지 확인**
        Set<Integer> basesToRemove = new HashSet<>();
        for (int base : possibleBases) {
            if (!areAllDigitsValid(expressions, base)) {
                basesToRemove.add(base);
            }
        }
        possibleBases.removeAll(basesToRemove);
        // **수정 부분 끝**

        // 결과값이 지워진 수식의 결괏값을 채워넣음
        for (String equation : unknownEquations) {
            Set<String> possibleResults = new HashSet<>();
            for (int base : possibleBases) {
                String resultValue = computeResult(equation, base);
                if (resultValue != null) {
                    possibleResults.add(resultValue);
                }
            }
            // 결괏값이 유일하면 해당 값으로 채워넣고, 아니면 '?'로 표시
            String filledEquation;
            if (possibleResults.size() == 1) {
                String value = possibleResults.iterator().next();
                filledEquation = equation.replace("X", value);
            } else {
                filledEquation = equation.replace("X", "?");
            }
            result.add(filledEquation);
        }

        return result.toArray(new String[0]);
    }

    // 수식이 해당 진법에서 성립하는지 확인하는 함수
    private boolean isValidEquation(String equation, int base) {
        String[] tokens = equation.split(" ");
        String A = tokens[0];
        String operator = tokens[1];
        String B = tokens[2];
        String C = tokens[4];

        try {
            int numA = parseNumber(A, base);
            int numB = parseNumber(B, base);
            int numC = parseNumber(C, base);

            if (operator.equals("+")) {
                return numA + numB == numC;
            } else if (operator.equals("-")) {
                return numA - numB == numC;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    // 수식을 계산하고 결과를 문자열로 반환하는 함수
    private String computeResult(String equation, int base) {
        String[] tokens = equation.split(" ");
        String A = tokens[0];
        String operator = tokens[1];
        String B = tokens[2];

        try {
            int numA = parseNumber(A, base);
            int numB = parseNumber(B, base);
            int result;

            if (operator.equals("+")) {
                result = numA + numB;
            } else if (operator.equals("-")) {
                result = numA - numB;
                if (result < 0) return null; // 결과가 음수인 경우
            } else {
                return null;
            }

            return Integer.toString(result, base).toUpperCase();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // 문자열 숫자를 해당 진법의 정수로 파싱하는 함수
    private int parseNumber(String numStr, int base) throws NumberFormatException {
        for (char c : numStr.toCharArray()) {
            int digit = Character.digit(c, 10);
            if (digit >= base || digit < 0) {
                throw new NumberFormatException();
            }
        }
        return Integer.parseInt(numStr, base);
    }

    // **추가된 함수: 모든 수식의 숫자들이 해당 진법에서 사용 가능한지 확인하는 함수**
    private boolean areAllDigitsValid(String[] expressions, int base) {
        for (String expr : expressions) {
            String[] tokens = expr.split(" ");
            for (String token : tokens) {
                if (token.equals("+") || token.equals("-") || token.equals("=") || token.equals("X")) {
                    continue;
                }
                for (char c : token.toCharArray()) {
                    int digit = Character.digit(c, 10);
                    if (digit >= base || digit < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}


public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] input = new String[]{"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"};
        String[] solution = s.solution(input);
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }



}
