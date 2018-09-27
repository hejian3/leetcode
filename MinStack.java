package com.leetcode;

public class MinStack {

    private int min;
    private ListNode head;

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /** initialize your data structure here. */
    public MinStack() {
        head = null;
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        ListNode node = new ListNode(x);
        if(head == null){
            head = node;
            min = x;
        }else{
            if(x < min)min = x;
            node.next = head;
            head = node;
        }
    }

    public void pop() {
        if(head !=null){
            head = head.next;
            min = Integer.MAX_VALUE;
            ListNode node = head;
            while (node!=null){
                if(node.val < min){
                    min = node.val;
                }
                node = node.next;
            }
        }
    }

    public int top() {
        if(head != null){
            return head.val;
        }
        return -1;
    }

    public int getMin() {
        return min;
    }

    public static void main(String []args){
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
    }
}
