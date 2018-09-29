package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrieSolution {

    private class TrieNode{
        static final int N = 26;
        TrieNode[] children = new TrieNode[N];
        boolean flag = false;
        TrieNode(){}
    }

    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        for(String word : dict) insert(root,word);
        String [] words = sentence.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < words.length; i++){
            if(i > 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(replaceWord(root,words[i]));
        }

        return stringBuilder.toString();
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

    private String replaceWord(TrieNode root,String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            TrieNode child = node.children[c - 'a'];
            if(child != null && child.flag)return word.substring(0,i + 1);
            if(child == null)break;
            node = child;
        }
        return word;
    }


    public static void main(String [] args){
        TrieSolution solution = new TrieSolution();
        System.out.println(solution.replaceWords(Arrays.asList("cat", "bat", "rat"),"the cattle was rattled by the battery"));
    }
}
