package backjoon.silver3.p1021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); //큐 넓이
        int M = Integer.parseInt(st.nextToken()); //뽑는 수 갯수

        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.offer(i);
        }

        int[] popNums = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            popNums[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        for (int i = 0; i < M; i++) {
            int target_index = deque.indexOf(popNums[i]);
            int half_index = deque.size() / 2;

            //짝수일 경우 {1,2,3,4} 중간 index는 하나 더 감소해야함
            if (deque.size() % 2 == 0){
                half_index -= 1;
            }

            if (target_index <= half_index) {
                while (deque.peekFirst() != popNums[i]) {
                    deque.offerLast(deque.pollFirst());
                    result++;
                }
            } else {
                while (deque.peekLast() != popNums[i]) {
                    deque.offerFirst(deque.pollLast());
                    result++;
                }

                //마지막 원소를 첫번째로 보낸다 -> 첫번째 원소를 뽑는 기능만 있으므로...
                deque.offerFirst(deque.pollLast());
                result++;
            }
            
            //뽑아낸 원소 제거
            deque.pollFirst();
        }
        System.out.println(result);
    }
}