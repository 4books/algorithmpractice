package twopointersslidingwindow.no3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int days = Integer.parseInt(strings[0]);
        int max = Integer.parseInt(strings[1]);

        String[] bS = br.readLine().split(" ");
        int[] sale = new int[days];
        for (int i = 0; i < bS.length; i++) {
            sale[i] = Integer.parseInt(bS[i]);
        }

        int sum = 0;

        //첫날부터 max 데이까지 합
        for (int i = 0; i < max; i++) {
            sum += sale[i];
        }
        int answer = sum;


        for (int i = max; i < days; i++) {
            sum += sale[i];
            sum -= sale[i - max];
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);



    } //END of Main Method
}


