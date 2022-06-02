package sortingalgo.no1;
/*
1. 선택 정렬
설명

N개이 숫자가 입력되면 오름차순으로 정렬하여 출력하는 프로그램을 작성하세요.

정렬하는 방법은 선택정렬입니다.


입력
첫 번째 줄에 자연수 N(1<=N<=100)이 주어집니다.

두 번째 줄에 N개의 자연수가 공백을 사이에 두고 입력됩니다. 각 자연수는 정수형 범위 안에 있습니다.


출력
오름차순으로 정렬된 수열을 출력합니다.


예시 입력 1

6
13 5 11 7 23 15
예시 출력 1

5 7 11 13 15 23
 */
import java.io.*;

public class Main {
    public static String solution(int n, int[] numbers){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if(numbers[i] > numbers[j]){
                    int tmp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = tmp;
                }
            }
        }

        for (int number : numbers) {
            sb.append(number).append(" ");
        }

        return sb.toString();
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(s[i]);
        }


        bw.write(solution(n, numbers));
        bw.flush();
        bw.close();
    }
}
