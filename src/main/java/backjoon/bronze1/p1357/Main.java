package backjoon.bronze1.p1357;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int reverse(int input) {
        StringBuilder sb = new StringBuilder(String.valueOf(input));
        sb.reverse();
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        System.out.println(reverse(reverse(X) + reverse(Y)));
    }
}