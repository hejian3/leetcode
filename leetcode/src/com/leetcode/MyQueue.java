package com.leetcode;

import java.util.Stack;

public class MyQueue {

    private Stack<Integer> stack = null;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int [] arr = new int [stack.size() - 1];
        for(int i = arr.length - 1 ;i >=0; i--){
            arr[i] = stack.pop();
        }
        int first = stack.pop();
        for(int i =0; i< arr.length;i++){
            stack.push(arr[i]);
        }
        return first;
    }

    /** Get the front element. */
    public int peek() {
        int [] arr = new int [stack.size() - 1];
        for(int i = arr.length - 1 ;i >=0; i--){
            arr[i] = stack.pop();
        }
        int result = stack.pop();
        stack.push(result);
        for(int i =0; i< arr.length;i++){
            stack.push(arr[i]);
        }
        return result;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.empty();
    }
}
