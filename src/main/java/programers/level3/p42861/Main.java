package programers.level3.p42861;
/*

 */
import java.util.*;

class Solution {

    int[] unf = null;

    class Node implements Comparable<Node> {
        int source;
        int target;
        int cost;
        public Node(int source, int target, int cost){
            this.source = source;
            this.target = target;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public int find(int v){
        if(unf[v] == v){
            return v;
        } else {
            return unf[v] = find(unf[v]);
        }
    }

    void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if(fa != fb){
            unf[fa] = fb;
        }
    }

    public int solution(int n, int[][] costs) {


        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;
        }

        List<Node> island = new ArrayList<>();
        for (int[] ints : costs) {
            int source = ints[0];
            int target = ints[1];
            int cost = ints[2];

            island.add(new Node(source, target, cost));
        }

        int answer = 0;
        Collections.sort(island);
        for (Node node : island) {
            int source = find(node.source);
            int target = find(node.target);
            if(source != target){
                answer += node.cost;
                union(source, target);
            }
        }
        return answer;
    }

}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

    }



}
