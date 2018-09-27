package com.leetcode;

public class MyCircularQueue {

    private int [] arrs = null;
    private int start = -1;
    private int end  = -1;
    private int size = 0;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        arrs = new int[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) return false;
        if(end == -1 || end == arrs.length - 1){
            end = 0;
        }else {
            end++;
        }
        if(isEmpty()){
            start = end;
        }
        arrs[end] = value;
        size++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(!isEmpty()){
            if(start == arrs.length - 1){
                start = 0;
            }else {
                start++;
            }
            size--;
            return true;
        }
        return false;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty())return -1;
        return arrs[start];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty())return -1;
        return arrs[end];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == arrs.length;
    }

    public static void main(String []args){
        MyCircularQueue obj = new MyCircularQueue(2);
        System.out.println(obj.enQueue(8));
        System.out.println(obj.enQueue(8));
        System.out.println(obj.Front());
        System.out.println(obj.enQueue(4));
        System.out.println(obj.deQueue());
        System.out.println(obj.enQueue(1));
        System.out.println(obj.enQueue(1));
        System.out.println(obj.Rear());
        System.out.println(obj.isEmpty());
        System.out.println(obj.Front());
        System.out.println(obj.deQueue());
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
