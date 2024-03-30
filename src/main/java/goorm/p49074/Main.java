package goorm.p49074;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] rams = new int[len];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            rams[i] = Integer.parseInt(input[i]);
        }

        int bad = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int n = rams[i];
            if ((n & (n - 1)) != 0) {
                bad++;
                sb.append(i + 1).append(" ");
            }
        }

        System.out.println(bad);
        System.out.println(sb.toString().trim());
    }
}