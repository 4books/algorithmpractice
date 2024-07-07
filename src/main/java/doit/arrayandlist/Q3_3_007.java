package doit.arrayandlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
6
9
2 7 4 1 5 3

출력
2
 */
public class Q3_3_007 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //정렬
        Arrays.sort(arr);

        int count = 0;
        int left = 0; //왼쪽 시작점
        int right = n - 1; //오른쪽 시작점

        while(left < right) {
            int sum = arr[left] + arr[right];
            if (sum < m) {
                left++;
            } else if (sum > m) {
                right--;
            } else {
                count++; //갑옷 하나 완성
                left++;
                right--;
            }
        }
        System.out.println(count);
    }
}
