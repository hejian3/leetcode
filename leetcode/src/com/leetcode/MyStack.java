package com.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyStack {

    private Queue<Integer>inQueue = null;
    private Queue<Integer>outQueue = null;

    /** Initialize your data structure here. */
    public MyStack() {
        inQueue = new ArrayDeque<>();
        outQueue = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        while (!outQueue.isEmpty()){
            inQueue.add(outQueue.poll());
        }
        outQueue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int result = outQueue.poll();
        if(outQueue.isEmpty()){
            while(inQueue.size()>1) {
                outQueue.add(inQueue.poll());
            }
            Queue<Integer> temp = inQueue;
            inQueue = outQueue;
            outQueue = temp;
        }
        return result;
    }

    /** Get the top element. */
    public int top() {
        return outQueue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return inQueue.isEmpty() && outQueue.isEmpty();
    }

    public static void main(String []args){
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.empty();
    }
}
