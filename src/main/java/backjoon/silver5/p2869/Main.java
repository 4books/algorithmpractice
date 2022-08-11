package backjoon.silver5.p2869;

/*
https://www.acmicpc.net/submit/2869
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        int UP = Integer.parseInt(tmp[0]);
        int DOWN = Integer.parseInt(tmp[1]);
        int HEIGHT = Integer.parseInt(tmp[2]);

        int day = (HEIGHT - DOWN) / (UP - DOWN);
        if((HEIGHT - DOWN) % (UP - DOWN) != 0){
            day += 1;
        }

        System.out.println(day);



    } //END of Main Method


}


