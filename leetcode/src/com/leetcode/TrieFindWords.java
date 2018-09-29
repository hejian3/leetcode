package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TrieFindWords {

    private class TrieNode{
        static final int N = 26;
        TrieNode[] children = new TrieNode[N];
        String word;
        TrieNode(){}
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for(String word : words){
            insert(root,word);
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                dsf(board,i,j,root,res);
            }
        }
        return res;
    }

    private void dsf(char[][] board, int i, int j, TrieNode node, List<String> res){
        char c = board[i][j];
        if(c == '$' || node.children[c - 'a'] == null)return;
        node = node.children[c - 'a'];
        if(node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        board[i][j] = '$';
        if(i > 0)dsf(board,i - 1,j,node,res);
        if(i < board.length - 1)dsf(board,i + 1, j,node,res);
        if(j > 0)dsf(board,i, j - 1,node,res);
        if(j < board[0].length - 1)dsf(board,i,j + 1,node,res);
        board[i][j] = c;
    }

    private void insert(TrieNode root, String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode();
            }
            if(i == word.length() - 1){
                node.children[c - 'a'].word = word;
            }
            node = node.children[c - 'a'];
        }
    }

}
