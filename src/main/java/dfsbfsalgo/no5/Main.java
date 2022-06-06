package dfsbfsalgo.no5;
/*
이진트리 순회(깊이우선탐색) DFS
아래 그림과 같은 이진트리를 전위순회와 후위순회를 연습해보세요.

1
2 3
4 5 6 7
전위순회 출력 : 1 2 4 5 3 6 7
중위순회 출력 : 4 2 5 1 6 3 7
후위순회 출력 : 4 5 2 6 7 3 1
 */



import dfsbfsalgo.Node;

import java.io.*;



public class Main {

    //전위 순회 출력
    public static void preorder(Node root, StringBuilder answer) {
        if (root != null) {
            answer.append(root.value).append(" ");
            preorder(root.left, answer);
            preorder(root.right, answer);
        }
    }

    //중위 순회 출력
    public static void inorder(Node root, StringBuilder answer) {
        if (root != null) {
            preorder(root.left, answer);
            answer.append(root.value).append(" ");
            preorder(root.right, answer);
        }
    }

    //후위 순회 출력
    public static void postorder(Node root, StringBuilder answer) {
        if (root != null) {
            postorder(root.left, answer);
            postorder(root.right, answer);
            answer.append(root.value).append(" ");
        }
    }

    public static String solution(Node root) {
        StringBuilder answer = new StringBuilder();

        answer.append("전위 순회 출력 : ");
        preorder(root, answer);
        answer.append("\n중위 순회 출력 : ");
        inorder(root, answer);
        answer.append("\n후위 순회 출력 : ");
        postorder(root, answer);

        return answer.toString();
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        bw.write(solution(root));
        bw.flush();
        bw.close();
    }

}
