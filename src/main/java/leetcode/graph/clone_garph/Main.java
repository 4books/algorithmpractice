package leetcode.graph.clone_garph;
/*
https://leetcode.com/problems/clone-graph/
 */

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;

        //기존 Node 와 clone 노드를 저장하는 map
        Map<Node, Node> oldToNew = new HashMap<>();
        oldToNew.put(node, new Node(node.val));

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (Node next : curr.neighbors) {
                //방문하지 않은 노드라면 다음 map 과 queue 에 추가
                if(!oldToNew.containsKey(next)){
                    oldToNew.put(next, new Node(next.val));
                    queue.offer(next);
                }
                //이웃 복사
                //Old node key 로 value 인 new node 를 가져와 new node 의 이웃에 next 를 추가한다.
                oldToNew.get(curr).neighbors.add(oldToNew.get(next));
            }
        }

        return oldToNew.get(node);
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
