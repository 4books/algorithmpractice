package dfsbfsalgo.no7;
/*
이진트리 순회(넓이우선탐색 : 레벨탐색)
아래 그림과 같은 이진트리를 레벨탐색 연습하세요.
1
2 3
4 5 6 7
레벨 탐색 순회 출력 : 1 2 3 4 5 6 7
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
        left = right = null;
    }
}

public class Main {

    public static void bfs(Node root, StringBuilder answer) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int length = queue.size();
            answer.append(level).append(" level : ");
            for (int i = 0; i < length; i++) {
                Node curr = queue.poll();
                answer.append(curr.value).append(" ");
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            level++;
            answer.append("\n");
        }
    }

    public static String solution(Node root) {
        StringBuilder answer = new StringBuilder();
        bfs(root, answer);

        return answer.toString();
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(10);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        bw.write(solution(root));
        bw.flush();
        bw.close();
    }

}
