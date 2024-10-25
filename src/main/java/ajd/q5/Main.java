package ajd.q5;
/*
추천 검색어..
검색한 word 보다 많은 게시글이 나와야 되고..
k이하로 달라진 검색어야 함..
추천 검색어 사전 순서대로 출력..
 */
import java.util.*;
class Solution {
    public String[] solution(int k, String word, String[] titles) {

        int minCount = 0; //게시물 검색되는 수 최솟값
        Map<String, Set<Integer>> substringToTitles = new HashMap<>();

        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];

            if(title.contains(word)){
                minCount++;
            }

            //title 잘라 확인하기
            for(int j = 0; j <= title.length() - word.length(); j++){
                String temp = title.substring(j, j + word.length());
                if(word.equals(temp)){
                    continue;
                }

                char[] wordChars = word.toCharArray();
                char[] tempChars = temp.toCharArray();

                int diffCount = 0;
                for(int l = 0; l < wordChars.length; l++){
                    if(wordChars[l] != tempChars[l]){
                        diffCount++;
                        if (diffCount > k) {
                            break; // 이미 k보다 크면 더 이상 비교할 필요 없음
                        }
                    }
                }
                //다른 글자가 K보다 많으면 안되고 최소 한개라도 있어야함
                if (diffCount <= k && diffCount > 0) {
                    substringToTitles
                            .computeIfAbsent(temp, key -> new HashSet<>())
                            .add(i);
                }
            }
        }

        List<String> answer = new ArrayList<>();
        for (Map.Entry<String, Set<Integer>> entry : substringToTitles.entrySet()) {
            String str = entry.getKey();
            int count = entry.getValue().size(); // 고유한 제목의 수
            if (count > minCount) {
                answer.add(str);
            }
        }

        if(answer.isEmpty()){
            return new String[]{"-1"};
        }
        Collections.sort(answer);//사전 순으로
        return answer.toArray(new String[0]);
    }
}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

    }



}
