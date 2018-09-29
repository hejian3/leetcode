package com.leetcode;

public class Trie {

    private class TrieNode{
        static final int N = 26;
        TrieNode[] children = new TrieNode[N];
        boolean flag = false;
        TrieNode(){}
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode();
            }
            if(i == word.length() - 1){
                node.children[c - 'a'].flag = true;
            }
            node = node.children[c - 'a'];
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null)return false;
            node = node.children[c - 'a'];
        }
        return node.flag;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(node.children[c - 'a'] == null)return false;
            node = node.children[c - 'a'];
        }
        return true;
    }

    public static void main(String[]args){
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println( trie.search("app"));
    }
}