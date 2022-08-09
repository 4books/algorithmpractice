package backjoon.silver3.p1463;
/*
https://www.acmicpc.net/problem/1463
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        Set<Integer> source = new HashSet<>();
        Set<Integer> target = new HashSet<>();

        source.add(N);

        while(!source.contains(1)){

            target.clear();

            for (Integer num : source) {
                if(num % 3 == 0){
                    target.add(num / 3);
                }
                if(num % 2 == 0){
                    target.add(num / 2);
                }
                target.add(num - 1);
            }

            //swap
            Set<Integer> tmp = source;
            source = target;
            target = tmp;

            cnt++;
        }
        System.out.println(cnt);



    } //END of Main Method

}


