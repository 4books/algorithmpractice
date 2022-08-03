package hashalgo.no5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);

        String[] TEMP_ARR = br.readLine().split(" ");
        int[] arr = new int[TEMP_ARR.length];
        for (int i = 0; i < TEMP_ARR.length; i++) {
            arr[i] = Integer.parseInt(TEMP_ARR[i]);
        }

        int answer = -1;
        TreeSet<Integer> tree = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    tree.add(arr[i] + arr[j] + arr[k]);
                }
            }
        }

        if(tree.size() < K){
            System.out.println(-1);
            System.exit(0);
        }

        int cnt = 0;
        for (Integer number : tree) {
            cnt++;
            if (cnt == K) {
                answer = number;
                break;
            }
        }

        System.out.println(answer);

    } //END of Main Method

}


