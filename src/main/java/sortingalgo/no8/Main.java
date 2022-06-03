package sortingalgo.no8;
/*
8. 이분검색
설명

임의의 N개의 숫자가 입력으로 주어집니다. N개의 수를 오름차순으로 정렬한 다음 N개의 수 중 한 개의 수인 M이 주어지면

이분검색으로 M이 정렬된 상태에서 몇 번째에 있는지 구하는 프로그램을 작성하세요. 단 중복값은 존재하지 않습니다.


입력
첫 줄에 한 줄에 자연수 N(3<=N<=1,000,000)과 M이 주어집니다.

두 번째 줄에 N개의 수가 공백을 사이에 두고 주어집니다.


출력
첫 줄에 정렬 후 M의 값의 위치 번호를 출력한다.


예시 입력 1

8 32
23 87 65 12 57 32 99 81
예시 출력 1

3
 */

import java.io.*;
import java.util.Arrays;

public class Main {

    public static int search(int left, int right, int target, int[] arr) {
        int mid = (left + right) / 2;
        if (arr[mid] > target) {
            return search(left, mid - 1, target, arr);
        } else if (arr[mid] < target) {
            return search(mid + 1, right, target, arr);
        }
        return mid;
    }

    public static String solution(int n, int target, int[] arr) {
        Arrays.sort(arr);

        //1번 재귀처리
//        return String.valueOf(search(0, n - 1, target, arr) + 1);

        //2번 while
        int left = 0, right = n - 1, answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                answer = mid + 1;
                break;
            }
        }
        return String.valueOf(answer);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");

        int n = Integer.parseInt(tmp[0]);
        int target = Integer.parseInt(tmp[1]);

        tmp = br.readLine().split(" ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tmp[i]);
        }


        bw.write(solution(n, target, arr));
        bw.flush();
        bw.close();
    }

}
