package backjoon.bronze5.p10757;
/*
https://www.acmicpc.net/problem/10757
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");

        StringBuilder A = new StringBuilder(tmp[0]).reverse();
        StringBuilder B = new StringBuilder(tmp[1]).reverse();

        //올림이 발생할 수 있으므로 +1
        int size = Math.max(A.length(), B.length()) + 1;
        int[] aArr = new int[size];
        int[] bArr = new int[size];
        for (int i = 0; i < A.length(); i++) {
            aArr[i] = A.charAt(i) - '0';
        }
        for (int i = 0; i < B.length(); i++) {
            bArr[i] = B.charAt(i) - '0';
        }

        for (int i = 0; i < aArr.length - 1; i++) {
            int sum = aArr[i] + bArr[i];
            if (sum >= 10) {
                aArr[i + 1] += 1;
                sum -= 10;
            }
            aArr[i] = sum;
        }
        StringBuilder sb = new StringBuilder();
        if(aArr[size - 1] == 1){
            sb.append(1);
        }
        for (int i = size - 2; i >= 0; i--) {
            sb.append(aArr[i]);
        }
        System.out.println(sb);
    } //END of Main Method
}


