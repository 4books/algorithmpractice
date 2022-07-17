package backjoon.gold2.p17780;
/*
https://www.acmicpc.net/problem/17780

원판모양이고, 하나의 말 위에 다른 말을 올릴 수 있다. 체스판의 각 칸은 흰색, 빨간색, 파란색 중 하나로 색칠되어있다.

게임은 체스판 위에 말 K개를 놓고 시작한다. 말은 1번부터 K번까지 번호가 매겨져 있고, 이동 방향도 미리 정해져 있다. 이동 방향은 위, 아래, 왼쪽, 오른쪽 4가지 중 하나이다.

턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것이다. 한 말이 이동할 때 위에 올려져 있는 말도 함께 이동하며, 가장 아래에 있는 말만 이동할 수 있다. 말의 이동 방향에 있는 칸에 따라서 말의 이동이 다르며 아래와 같다. 턴이 진행되던 중에 말이 4개 이상 쌓이는 순간 게임이 종료된다.

A번 말이 이동하려는 칸이
흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
예를 들어, A, B, C로 쌓여있고, 이동하려는 칸에 D, E가 있는 경우에는 A번 말이 이동한 후에는 D, E, A, B, C가 된다.
빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.
파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다. 방향을 반대로 한 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 방향만 반대로 바꾼다.
체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
다음은 크기가 4×4인 체스판 위에 말이 4개 있는 경우이다.



첫 번째 턴은 다음과 같이 진행된다.


두 번째 턴은 다음과 같이 진행된다.


체스판의 크기와 말의 위치, 이동 방향이 모두 주어졌을 때, 게임이 종료되는 턴의 번호를 구해보자.

입력
첫째 줄에 체스판의 크기 N, 말의 개수 K가 주어진다. 둘째 줄부터 N개의 줄에 체스판의 정보가 주어진다. 체스판의 정보는 정수로 이루어져 있고, 각 정수는 칸의 색을 의미한다. 0은 흰색, 1은 빨간색, 2는 파란색이다.

다음 K개의 줄에 말의 정보가 1번 말부터 순서대로 주어진다. 말의 정보는 세 개의 정수로 이루어져 있고, 순서대로 행, 열의 번호, 이동 방향이다. 행과 열의 번호는 1부터 시작하고, 이동 방향은 4보다 작거나 같은 자연수이고 1부터 순서대로 →, ←, ↑, ↓의 의미를 갖는다.

같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다.

출력
게임이 종료되는 턴의 번호를 출력한다. 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.

제한
4 ≤ N ≤ 12
4 ≤ K ≤ 10
예제 입력 1
4 4
0 0 2 0
0 0 1 0
0 0 1 2
0 2 0 0
2 1 1
3 2 3
2 2 1
4 1 2
예제 출력 1
-1
 */


import java.io.*;
import java.util.*;

public class Main {

    static class Piece {
        int number, row, col, arrow;

        public Piece(int number, int row, int col, int arrow) {
            this.number = number;
            this.row = row;
            this.col = col;
            this.arrow = arrow;
        }
    }

    static class Grid {
        List<Piece> pieces;
        int info;

        public Grid(int info) {
            this.pieces = new LinkedList<>();
            this.info = info;
        }
    }

    //오른쪽 왼쪽 위 아래
    static final int[] dR = {0, 0, -1, 1};
    static final int[] dC = {1, -1, 0, 0};
    static final int[] change = {1, 0, 3, 2};

    static boolean notInRange(int nRow, int nCol, int N) {
        return nRow <= 0 || nRow > N || nCol <= 0 || nCol > N;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //체스판 N x N
        int N = Integer.parseInt(st.nextToken());
        Grid[][] board = new Grid[N + 1][N + 1];

        //말 갯수
        int K = Integer.parseInt(st.nextToken());

        //체스판 정보
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = new Grid(Integer.parseInt(st.nextToken()));
            }
        }

        //말 정보
        Piece[] pieces = new Piece[K + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int arrow = Integer.parseInt(st.nextToken()) - 1;
            Piece piece = new Piece(i, row, col, arrow);
            board[row][col].pieces.add(piece);
            pieces[i] = piece;
        }

        int result = -1;
        boolean isSuccess = false;

        //1000턴 동안
        for (int i = 1; i <= 1000; i++) {

            //말들 정보를 가져와 턴 진행
            for (int j = 1; j <= K; j++) {
                Piece piece = pieces[j];

                //현재 위치
                Grid oldGrid = board[piece.row][piece.col];

                //가장 하단 말이 아니면 다음 말로
                if (oldGrid.pieces.get(0).number != j) {
                    continue;
                }

                int nRow = piece.row + dR[piece.arrow];
                int nCol = piece.col + dC[piece.arrow];

                //0 흰색
                //1 빨간색 : 말들의 위치를 반대로
                //2 파란색 : 말들의 방향을 반대로 하고 한칸 더 이동 + 다시 파란색 or 범위 밖이면 제자리
                //체스판을 벗어나려고 하면 파란색과 같음

                //새로운 좌표가 체스판 범위 밖이거나 파란색이면
                // + 그래도 범위 밖이거나 파란색이면 스킵
                if (notInRange(nRow, nCol, N) || board[nRow][nCol].info == 2) {
                    piece.arrow = change[piece.arrow];
                    nRow = piece.row + dR[piece.arrow];
                    nCol = piece.col + dC[piece.arrow];
                    if (notInRange(nRow, nCol, N) || board[nRow][nCol].info == 2) {
                        continue;
                    }
                }

                Grid newGrid = board[nRow][nCol];
                if (newGrid.info == 1) { //빨간색일 경우 순서 바꿈
                    //A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.
                    for (int k = oldGrid.pieces.size() - 1; k >= 0; k--) {
                        Piece p = oldGrid.pieces.get(k);
                        pieces[p.number].row = nRow;
                        pieces[p.number].col = nCol;
                        newGrid.pieces.add(p);
                    }
                    oldGrid.pieces.clear();

                } else if (newGrid.info == 0) {
                    for (int k = 0; k < oldGrid.pieces.size(); k++) {
                        Piece p = oldGrid.pieces.get(k);
                        pieces[p.number].row = nRow;
                        pieces[p.number].col = nCol;
                        newGrid.pieces.add(p);
                    }
                    oldGrid.pieces.clear();
                }

                if (newGrid.pieces.size() >= 4) {
                    isSuccess = true;
                    result = i;
                    break;
                }
            }

            //성공시 종료
            if (isSuccess) {
                break;
            }
        }

        //결과 출력
        System.out.println(result);
    }

}


