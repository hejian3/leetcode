package com.leetcode;

public class MapSum {

    private class TrieNode{
        static final int N = 26;
        TrieNode[] children = new TrieNode[N];
        int sum = 0;
        boolean flag = false;
        TrieNode(){}
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode node = root;
        int diff = val;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode();
            }

            if(i == key.length() - 1){
                if(node.children[c - 'a'].flag) {
                    diff = val - node.children[c - 'a'].sum;
                    node.children[c - 'a'].sum = val;
                }else {
                    node.children[c - 'a'].sum = node.children[c - 'a'].sum + val;
                }
            }

            node = node.children[c - 'a'];
        }
        node.flag = true;
        node = root;
        node.sum = node.sum + diff;

        for(int i = 0; i < key.length() - 1; i++) {
            char c = key.charAt(i);
            node.children[c - 'a'].sum = node.children[c - 'a'].sum + diff;
            node =  node.children[c - 'a'];
        }
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            node = node.children[c - 'a'];
            if(node == null)break;
        }
        if(node != null) return node.sum;
        return 0;
    }

    public static void main(String [] args){
        MapSum mapSum = new MapSum();
        mapSum.insert("apple",3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app",2);
        System.out.println(mapSum.sum("ap"));


        mapSum = new MapSum();
        mapSum.insert("aa",3);
        System.out.println(mapSum.sum("a"));
        mapSum.insert("aa",2);
        System.out.println(mapSum.sum("a"));
        System.out.println(mapSum.sum("aa"));



    }
}
