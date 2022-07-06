package programers.level3.p43163;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/43163

두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.

1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
2. words에 있는 단어로만 변환할 수 있습니다.
예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.

두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.

제한사항
각 단어는 알파벳 소문자로만 이루어져 있습니다.
각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
begin과 target은 같지 않습니다.
변환할 수 없는 경우에는 0를 return 합니다.
입출력 예
begin	target	words	return
"hit"	"cog"	["hot", "dot", "dog", "lot", "log", "cog"]	4
"hit"	"cog"	["hot", "dot", "dog", "lot", "log"]	0
입출력 예 설명
예제 #1
문제에 나온 예와 같습니다.

예제 #2
target인 "cog"는 words 안에 없기 때문에 변환할 수 없습니다.
 */

class Solution {
    int answer;

    public int solution(String begin, String target, String[] words) {
        boolean[] checks = new boolean[words.length];//중복 체크 방지
        answer = 51; //단어 배열 길이가 최대 50개

        //dfs
        dfs(0, begin, target, words, checks, begin.length());
        if (answer == 51) {//변환 불가
            answer = 0;
        }

        return answer;
    }

    public void dfs(int level, String source, String target, String[] words, boolean[] checks, int wordSize) {

        if (source.equals(target)) {
            //source 와 타켓이 동일하면 정답 후보
            answer = Math.min(answer, level);
            return;
        }

        //이미 다 체크했는데 답이 아닌 경우 return
        if (level == words.length) {
            return;
        }

        //source char array 로
        char[] srArr = source.toCharArray();

        for (int i = 0; i < words.length; i++) {

            //이미 체크한 단어 제외
            if (checks[i]) {
                continue;
            }

            //체크한 단어라 표시
            checks[i] = true;

            char[] wdArr = words[i].toCharArray();
            int count = 0; //틀린 글자 수, 바꿀 수 있는 단어인가 (한글자만 달라야함)

            //단어 체크
            for (int j = 0; j < wordSize; j++) {
                if (srArr[j] != wdArr[j]) {
                    count++;
                }
                if (count > 1) { //틀린 글자가 1개 이상이면 변환 불가
                    break;
                }
            }

            //한글자만 변환 가능함
            if (count == 1) {
                dfs(level + 1, words[i], target, words, checks, wordSize);
            }

            //체크 초기화
            checks[i] = false;
        }
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        int answer = s.solution(begin, target, words);
        int expect = 4;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);

        words = new String[]{"hot", "dot", "dog", "lot", "log"};
        answer = s.solution(begin, target, words);
        expect = 0;
        System.out.println("answer = " + answer);
        System.out.println("expect = " + expect);
    }
}
