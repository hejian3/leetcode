package com.leetcode;

public class WordDictionary {

    private class TrieNode{
        static final int N = 26;
        TrieNode[] children = new TrieNode[N];
        boolean flag = false;
        TrieNode(){}
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        insert(root,word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root,word);
    }

    private boolean search(TrieNode root,String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(c == '.'){
                if(i < word.length() - 1) {
                    String subWord = word.substring(i + 1);
                    for (TrieNode trieNode : node.children) {
                        if(trieNode != null) {
                            boolean flag = search(trieNode, subWord);
                            if (flag) return true;
                        }
                    }
                    return false;
                }else{
                    for (TrieNode trieNode : node.children) {
                        if(trieNode != null && trieNode.flag)return true;
                    }
                    return false;
                }
            }else{
                node = node.children[c - 'a'];
                if(node == null)return false;
                if(i == word.length() - 1)return node.flag;
            }
        }
        return true;
    }

    private void insert(TrieNode root,String word) {
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

    public static void main(String [] args){
        WordDictionary wordDictionary = new WordDictionary();

        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));
    }
}
