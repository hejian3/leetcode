package com.leetcode;

public class MaximumXORSolution {

    private class TrieNode{
        TrieNode[] children = new TrieNode[2];
        TrieNode(){}
    }

    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        for(int num : nums) insert(root,num);
        int max = Integer.MIN_VALUE;
        for(int num: nums) {
            TrieNode node = root;
            int curSum = 0;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(node.children[curBit ^ 1] != null) {
                    curSum += (1 << i);
                    node = node.children[curBit ^ 1];
                }else {
                    node = node.children[curBit];
                }
            }
            max = Math.max(curSum, max);
        }
        return max;
    }


    private void insert(TrieNode root,int num) {
        TrieNode node = root;
        for(int i = 31; i >= 0; i --) {
            int curBit = (num >>> i) & 1;
            if(node.children[curBit] == null) {
                node.children[curBit] = new TrieNode();
            }
            node = node.children[curBit];
        }
    }

    public static void main(String [] args){
        MaximumXORSolution solution = new MaximumXORSolution();
        System.out.println(solution.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }
}
