package twopointersslidingwindow.no1;



import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
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

        //answer 의 총 길이
        int total = N + M;

        int[] answer = new int[total];

        //Two Pointer
        int p1 = 0, p2 = 0, index = 0;

        //먼저 동일한 길이 만큼 answer 에 담는다.
        while (p1 < N && p2 < M) {
            if(A[p1] < B[p2]){
                answer[index++] = A[p1++];
            } else {
                answer[index++] = B[p2++];
            }
        }

        //둘 중 남은 것을 전부 넣는다.
        //다 돈 것도 이미 포인트가 해당 배열 길이보다 크기 떄문에 돌지 않는다.
        while(p1 < N){
            answer[index++] = A[p1++];
        }
        while(p2 < M){
            answer[index++] = B[p2++];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < total; i++) {
            sb.append(answer[i] + " ");
        }
        System.out.println(sb);

    } //END of Main Method
}

