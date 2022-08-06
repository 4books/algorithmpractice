package toss.test20220806.n6;
/*

 */


import java.util.*;

class Solution {

    static class Person implements Comparable<Person>{
        String name;
        int cnt;

        public Person(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Person o) {
            return o.cnt - this.cnt;
        }
    }

    //[[1, 2], [1, 3], [3, 4], [4, 5], [4, 6], [4, 7]]
    public String[] solution(int[] steps_one, String[] names_one, int[] steps_two, String[] names_two, int[] steps_three, String[] names_three) {

        Map<String, Person> map = new HashMap<>();

        for (int i = 0; i < steps_one.length; i++) {

            String usr = names_one[i];
            int steps = steps_one[i];

            Person person = new Person(usr, steps);

            if(map.get(usr) == null){
                map.put(usr, person);
            } else {
                Person tmp = map.get(usr);
                tmp.cnt = steps;
            }
        }

        Map<String, Person> tmpMap = new HashMap<>();
        for (int i = 0; i < steps_two.length; i++) {

            String usr = names_two[i];
            int steps = steps_two[i];

            Person person = new Person(usr, steps);
            tmpMap.put(usr, person);
        }

        for (String key : tmpMap.keySet()) {
            if(map.get(key) == null){
                map.put(key, tmpMap.get(key));
            } else {
                Person tmp = map.get(key);
                tmp.cnt += tmpMap.get(key).cnt;
            }

        }

        tmpMap = new HashMap<>();
        for (int i = 0; i < steps_three.length; i++) {

            String usr = names_three[i];
            int steps = steps_three[i];

            Person person = new Person(usr, steps);
            tmpMap.put(usr, person);
        }

        for (String key : tmpMap.keySet()) {
            if(map.get(key) == null){
                map.put(key, tmpMap.get(key));
            } else {
                Person tmp = map.get(key);
                tmp.cnt += tmpMap.get(key).cnt;
            }
        }

        TreeMap<String, Person> tree = new TreeMap<>(map);
        List<Person> list = new ArrayList<>(tree.values());
        Collections.sort(list);

        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).name;
        }

        return answer;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        s.solution(new int[]{0, 5, 1}, new String[]{"evan", "ed", "evan"}, new int[]{9999}, new String[]{"rose"}, new int[]{1}, new String[]{"richard"});
        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
