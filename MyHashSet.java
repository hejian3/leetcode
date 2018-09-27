package com.leetcode;

public class MyHashSet {

    private Node [] arr;

    private class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }

    /** Initialize your data structure here. */
    public MyHashSet() {
        arr = new Node[10];
    }

    public void add(int key) {
        if(contains(key))return;
        int hash = hash(key);
        Node node = arr[hash];
        if(node == null){
            arr[hash] = new Node(key);
        }else{
            while (node.next!=null){
                node = node.next;
            }
            node.next = new Node(key);
        }
    }

    public void remove(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node !=null){
            if(node.val == key){
                arr[hash] = node.next;
            }else {
                while (node.next!=null && node.next.val != key){
                    node = node.next;
                }
                if(node.next !=null){
                    node.next = node.next.next;
                }
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node !=null){
            if(node.val == key){
                return true;
            }else {
                while (node.next!=null && node.next.val != key){
                    node = node.next;
                }
                if(node.next !=null){
                    return true;
                }
            }
        }
        return false;
    }

    private int hash(int key){
        return key % arr.length;
    }

    public static void main(String[]args){
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));
        System.out.println(hashSet.contains(3));
        hashSet.add(2);
        System.out.println(hashSet.contains(2));
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));
    }
}
