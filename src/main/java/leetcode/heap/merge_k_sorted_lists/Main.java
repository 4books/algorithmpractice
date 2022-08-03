package leetcode.heap.merge_k_sorted_lists;
/*
https://leetcode.com/problems/merge-k-sorted-lists/
 */


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                //o1 source, o2 target
                //만약 o1 - o2 값이 음수 또는 0이면(== target 값이 크거나 같으면)
                //o1 과 o2 를 순서를 바꾸지 않음
                //즉, 올림차순 정렬
                //내림차순을 하고 싶으면 source 와 target 을 바꿔서 빼면 됨.
                //참고로 lambda 형식으로 변경 가능
                return o1.val - o2.val; 
            }
        });

        //최상단 루트 노드
        ListNode first = new ListNode(0);
        ListNode curr = first;

        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }

        while (!pq.isEmpty()) {
            //우선 순위 큐로 정렬된 node 를 제일 첫번째를 가져옴
            ListNode next = pq.poll();

            //현재 node 의 next 를 변경
            curr.next = next;
            //현재 node 를 next node 로 변경
            curr = curr.next;

            //현재 node 의 next 가 있다면, 해당 node queue 에 offer
            if(curr.next != null){
                pq.offer(curr.next);
            }
        }
        return first.next;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        int answer, expect;
        ListNode[] lists = new ListNode[3];
        ListNode a5 = new ListNode(5);
        ListNode a4 = new ListNode(4, a5);
        ListNode a1 = new ListNode(1, a4);
        lists[0] = a1;

        ListNode b4 = new ListNode(4);
        ListNode b3 = new ListNode(3, b4);
        ListNode b1 = new ListNode(1, b3);
        lists[1] = b1;

        ListNode c6 = new ListNode(6);
        ListNode c2 = new ListNode(2, c6);
        lists[2] = c2;

        ListNode listNode = s.mergeKLists(lists);

//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
