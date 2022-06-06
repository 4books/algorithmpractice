package dfsbfsalgo.no9;
/*
Tree 말단 노드까지의 가장 짧은 경로
아래 그림과 같은 이진트리에서 루트 노드 1에서 말단노드까지의 길이 중 가장 짧은 길이를
구하는 프로그램을 작성하세요.
각 경로의 길이는 루트노드에서 말단노드까지 가는데 이동하는 횟수를 즉 간선(에지)의 개수를
길이로 하겠습니다.
1
2 3
4 5
가장 짧은 길이는 3번 노느까지의 길이인 1이다.
 */
import dfsbfsalgo.Node;

import java.io.*;

public class Main {

    public static int dfs(Node root, int level) {
        if (root.left == null && root.right == null) {
            return level;
        } else {
            return Math.min(dfs(root.left, level + 1), dfs(root.right, level + 1));
        }
    }

    public static String solution(Node root){
        return String.valueOf(dfs(root, 0));
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);

        bw.write(solution(root));


        bw.flush();
        bw.close();
    }

}
