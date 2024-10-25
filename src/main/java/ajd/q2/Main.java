package ajd.q2;
/*

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    int total1 = 0;
    int total2 = 0;

    int oneHarder = 0;

    class Person implements Comparable<Person>{
        int id;
        int sum;
        int number1;
        int number2;

        Person(int id, int sum, int number1, int number2){
            this.id = id;
            this.sum = sum;
            this.number1 = number1;
            this.number2 = number2;
        }

        @Override
        public int compareTo(Person other){
            if(other.sum  == this.sum){//총점이 같다면
                //더 어려운 문제를 푼 사람이 등수 1등
                if(oneHarder == 1){
                    return other.number1 - this.number1;
                } else if(oneHarder == -1){
                    return other.number2 - this.number2;
                } else {// 어려운 문제가 없다면
                    return this.id - other.id; //음수면 바꾸지 않음
                }
            }
            return other.sum - this.sum; //내림차순
        }
    }

    public int[] solution(int[][] scores) {
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < scores.length; i++){
            int num1 = scores[i][0];
            int num2 = scores[i][1];

            total1 += num1;
            total2 += num2;

            persons.add(new Person(i + 1, num1+num2, num1, num2));
        }

        if(total1 > total2){
            oneHarder = -1; //2번이 어려움(점수가 낮으므로)
        }else if(total1 == total2){
            oneHarder = 0;
        } else {
            oneHarder = 1; //1번이 어려움
        }

        Collections.sort(persons);

        int[] ranks = new int[persons.size()];
        for(int i = 0; i < persons.size(); i++){
            ranks[persons.get(i).id - 1] = i + 1;
        }

        return ranks;
    }
}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] scores = new int[3][2];
        scores[0][0] = 85;
        scores[0][1] = 90;
        scores[1][0] = 91;
        scores[1][1] = 87;
        scores[2][0] = 88;
        scores[2][1] = 87;

        s.solution(scores);

    }



}
