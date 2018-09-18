package com.leetcode;

public class MyLinkedList {

    SinglyListNode head;
    int size;

    public class SinglyListNode {
        int val;
        SinglyListNode next;
        SinglyListNode(int x) { val = x; }
    }

    private boolean checkIndex(int index){
        return index >=0 && index < size;
    }

    private SinglyListNode getNode(int index){
        SinglyListNode current = head;
        for(int i = 1; i <= index; i++){
            current = current.next;
        }
        return current;
    }

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(checkIndex(index)){
            SinglyListNode current = getNode(index);
            return current.val;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        SinglyListNode node = new SinglyListNode(val);
        if(head == null){
            head = node;
        }else{
            SinglyListNode temp = head;
            head = node;
            node.next = temp;
        }
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if(size == 0){
            addAtHead(val);
        }else{
            addAtIndex(size,val);
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index == 0){
            addAtHead(val);
        }else if(index>=0 && index <=size){
            SinglyListNode node = new SinglyListNode(val);
            SinglyListNode before = getNode(index - 1);
            SinglyListNode current = before.next;
            before.next = node;
            node.next = current;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(checkIndex(index)){
            SinglyListNode before = getNode(index - 1);
            SinglyListNode current = before.next;
            SinglyListNode next = current.next;
            before.next = next;
            size--;
        }
    }

    public static void main(String[]args){
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1,2);
        System.out.print(myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);
        myLinkedList.get(1);
    }

}
