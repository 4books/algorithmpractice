package backjoon.bronze1.p1193;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String result = "";
        int x = sc.nextInt();

        int line = 0;
        int maxNum = 0;

        //계차수열
        while (maxNum < x) {
            line++;
            maxNum = line * (line + 1) / 2;
        }

        // line 짝수 홀수
        // 홀수일때는 분자가 커졌다가 작아지고, 분모는 작았다가 커진다.
        // 짝수일때는 분자가 작았다가 커지고, 분모는 커졌다가 작아진다.

        if (line % 2 == 1) {  //홀수줄
            result = (maxNum - x + 1) + "/" + (line - (maxNum - x));
        } else {     //짝수줄
            result = (line - (maxNum - x)) + "/" + (maxNum - x + 1);
        }

        System.out.println(result);
    }

}
