package programers.level3.p42579;
/*
https://programmers.co.kr/learn/courses/30/lessons/42579

문제 설명
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
입출력 예
genres	plays	return
["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
입출력 예 설명
classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

고유 번호 3: 800회 재생
고유 번호 0: 500회 재생
고유 번호 2: 150회 재생
pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

고유 번호 4: 2,500회 재생
고유 번호 1: 600회 재생
따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

장르 별로 가장 많이 재생된 노래를 최대 두 개까지 모아 베스트 앨범을 출시하므로 2번 노래는 수록되지 않습니다.
※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.
 */


import java.util.*;

public class Main {

    static class Solution {

        //노래 정보
        public class Song implements Comparable<Song> {
            public int index;
            public int play;

            public Song(int index, int play) {
                this.index = index;
                this.play = play;
            }

            @Override
            public int compareTo(Song o) {
                if (o.play == this.play) {
                    //재생수가 같다면 index 가 낮은 것 부터
                    return this.index - o.index;
                }
                return o.play - this.play; //노래 재생이 높은 순으로  내림차순
            }
        }

        //장르 정보
        public class Genre implements Comparable<Genre> {
            public String type;
            public int play;

            public Genre(String type, int play) {
                this.type = type;
                this.play = play;
            }

            @Override
            public int compareTo(Genre o) {
                return o.play - this.play; //내림차순
            }
        }

        public List<Integer> solution(String[] genres, int[] plays) {
            List<Integer> answer = new ArrayList<>();

            //각 장르의 노래들 index 와 재생횟수 저장
            Map<String, ArrayList<Song>> songMap = new HashMap<>();

            //각 장르의 총 재생 횟수 저장
            Map<String, Integer> playMap = new HashMap<>();

            for (int i = 0; i < genres.length; i++) {
                String type = genres[i];
                if (!songMap.containsKey(type)) {
                    songMap.put(type, new ArrayList<>());
                }
                songMap.get(type).add(new Song(i, plays[i]));

                playMap.put(type, playMap.getOrDefault(type, 0) + plays[i]);
            }

            //가장 많이 재생된 장르 sorting 내림차순
            List<Genre> genreList = new ArrayList<>();
            for (String type : playMap.keySet()) {
                genreList.add(new Genre(type, playMap.get(type)));
            }
            Collections.sort(genreList);

            //가장 많이 재생된 장르 부터
            for (Genre genre : genreList) {
                String type = genre.type;
                ArrayList<Song> songs = songMap.get(type);

                //같은 장르중 가장 많이 재생됐거나 같은 재생수일 때 앞 번호부터
                Collections.sort(songs);

                //최대 2개까지만 저장
                answer.add(songs.get(0).index);
                if (songs.size() > 1) {
                    answer.add(songs.get(1).index);
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {

        Solution s = new Solution();

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        List<Integer> answer = s.solution(genres, plays);

        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
        System.out.println();

        String expect = "4 1 3 0";
        System.out.println("expect = " + expect);

        String[] genres2 = {"classic", "pop", "classic", "classic", "pop", "test"};
        int[] plays2 = {500, 600, 150, 800, 2500, 100};

        answer = s.solution(genres2, plays2);

        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
        System.out.println();

        expect = "4 1 3 0 5";
        System.out.println("expect = " + expect);

        String[] genres3 = {"classic"};
        int[] plays3 = {300};

        answer = s.solution(genres3, plays3);

        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
        System.out.println();

        expect = "0";
        System.out.println("expect = " + expect);

    }
}
