package twopointersslidingwindow.no2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk;

        int N = Integer.parseInt(br.readLine());
        tk = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tk.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        tk = new StringTokenizer(br.readLine());
        int[] B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(tk.nextToken());
        }

        List<Integer> answer = new ArrayList<>();

        int p1 = 0, p2 = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        while (p1 < N && p2 < M) {
            int v1 = A[p1];
            int v2 = B[p2];
            if(v1 == v2){
                p1++;
                p2++;
                answer.add(v1);
            } else if(v1 > v2){
                //작은 값의 포인터를 옮긴다.
                //오름차순 sorting 을 했기 떄문에 큰 값의 포인터를 옮기면 찾을 수 없다.
                //1 3 5   p1=0
                //2 5 6   p2=0 이고 p2 를 옮기면 끝까지 답이 안나옴
                p2++;
            } else {
                p1++;
            }
        }

    } //END of Main Method
}


