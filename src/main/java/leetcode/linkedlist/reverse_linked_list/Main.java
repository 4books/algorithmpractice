package leetcode.linkedlist.reverse_linked_list;
/*
https://leetcode.com/problems/reverse-linked-list/
 */


import java.util.Stack;

// Definition for singly-linked list.
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
    public ListNode reverseList(ListNode head) {
        ListNode node = head;
        ListNode next = null;

        Stack<ListNode> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        if(!stack.isEmpty()){
            head = stack.peek();
        }
        while (!stack.isEmpty()) {
            node = stack.pop();

            if(stack.isEmpty()){
                node.next = null;
            } else {
                next = stack.peek();
                node.next = next;
            }
        }


        return head;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();

        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);

        head = s.reverseList(head);
        System.out.println(head.val);

        ListNode node = head.next;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }


//        answer = s.solution(new int[]{1, 2});
//        expect = 1;
//        System.out.println("result = " + answer + " " + "expect = " + expect);
    }
}
