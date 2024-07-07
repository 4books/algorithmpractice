package doit.arrayandlist;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
12 3
1 5 2 3 6 2 3 7 3 5 2 6

1 1 1 2 2 2 2 2 3 3 2 2
 */
public class Q3_4_010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(st.nextToken());
            //들어가 있는 값이 현재 값보다 작으면 제거
            while (!deque.isEmpty() && deque.getLast().value > now) {
                deque.removeLast();
            }
            //값과 index 저장
            deque.addLast(new Node(now, i));

            //슬라이딩 윈도우 범위에 벗어나면 삭제
            if(deque.getFirst().index <= i - l){
                deque.removeFirst();
            }

            //가장 첫번째 value가 해당 범위내 최소값
            bw.write(deque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }

    static class Node{
        public int value;
        public int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
