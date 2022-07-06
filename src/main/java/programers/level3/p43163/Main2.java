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

import java.util.LinkedList;
import java.util.Queue;

class Solution2 {

    public class Word {
        public String name;
        public int edge;

        public Word(String name, int edge) {
            this.name = name;
            this.edge = edge;
        }
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0, n = words.length;

        Queue<Word> queue = new LinkedList<>();
        queue.offer(new Word(begin, 0));

        boolean[] visited = new boolean[n];

        while (!queue.isEmpty()) {
            Word word = queue.poll();

            if (word.name.equals(target)) {
                answer = word.edge;
                break;
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i] && canChange(word.name, words[i])) {
                    visited[i] = true;
                    queue.add(new Word(words[i], word.edge + 1));
                }
            }
        }

        return answer;
    }

    private boolean canChange(String source, String target) {
        int count = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != target.charAt(i)) {
                if (++count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

}

public class Main2 {


    public static void main(String[] args) {

        Solution2 s = new Solution2();

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
