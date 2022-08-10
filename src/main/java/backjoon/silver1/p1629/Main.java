package backjoon.silver1.p1629;
/*
https://www.acmicpc.net/problem/1629
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Long A,B,C;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        A = Long.parseLong(tmp[0]);
        B = Long.parseLong(tmp[1]);
        C = Long.parseLong(tmp[2]);

        Long pow = pow(A, B);
        System.out.println(pow);


    } //END of Main Method

    private static Long pow(Long a, Long b) {
        if (b == 1L) {
            return A % C;
        }

        Long tmp = pow(A, b / 2);
        if(b % 2 == 1){
            return (tmp * tmp % C) * A % C;

        }
        return tmp * tmp % C;
    }


}


