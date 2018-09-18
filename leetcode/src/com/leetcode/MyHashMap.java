package com.leetcode;

public class MyHashMap {

    private Node[] arr;

    private class Node{
        int key;
        int val;
        Node next;
        Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        arr = new Node[10];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node == null){
            arr[hash] = new Node(key,value);
        }else{
            if(node.key == key){
                node.val = value;
            }else {
                while (node.next != null && node.next.key != key) {
                    node = node.next;
                }
                if (node.next != null) {
                    node.next.val = value;
                } else {
                    node.next = new Node(key, value);
                }
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node != null){
            if(node.key == key){
                return node.val;
            }else {
                while (node.next != null && node.next.key != key) {
                    node = node.next;
                }
                if (node.next != null) {
                    return node.next.val;
                }
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node != null){
            if(node.key == key){
                arr[hash] = node.next;
            }else {
                while (node.next != null && node.next.key != key) {
                    node = node.next;
                }
                if (node.next != null) {
                    node.next = node.next.next;
                }
            }
        }
    }

    private int hash(int key){
        return key % arr.length;
    }

    public static void main(String[]args){
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1,1);
        myHashMap.put(2,2);
        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.get(3));
        myHashMap.put(2,1);
        System.out.println(myHashMap.get(2));
        myHashMap.remove(2);
        System.out.println(myHashMap.get(2));
        System.out.println(myHashMap.hash(2));
    }
}
