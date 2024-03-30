package goorm.p49062;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String N = input[0];
        String T = input[1];

        for (int i = 2; i <= 16; i++) {
            String newN = Integer.toString(Integer.parseInt(N), i);
            if (newN.toUpperCase().equals(T)){
                System.out.println(i);
                break;
            }
        }
    }
}