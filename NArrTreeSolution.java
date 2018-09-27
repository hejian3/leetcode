package com.leetcode;

import java.util.*;

public class NArrTreeSolution {

    static class Node {

        public int val;

        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        preorderRecursive(root,res);
        return res;
    }

    private void preorderRecursive(Node node, List<Integer> res){
        if(node == null)return;
        res.add(node.val);
        for(Node n : node.children){
            preorderRecursive(n,res);
        }
    }

    private void preorderIterative(Node root, List<Integer>res){
        if(root == null)return;
        Stack<Node> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.isEmpty()){
            Node node = nodes.pop();
            res.add(node.val);
            for(int i = node.children.size() -1 ; i >=0 ; i--){
                nodes.push(node.children.get(i));
            }
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        postorderIterative(root,res);
        return res;
    }

    private void postorderRecursive(Node node, List<Integer> res){
        if(node == null)return;
        for(Node n : node.children){
            postorderRecursive(n,res);
        }
        res.add(node.val);
    }

    private void postorderIterative(Node root, List<Integer>res){
        if(root == null)return;
        Stack<Node> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.isEmpty()){
            Node node = nodes.pop();
            res.add(node.val);
            for(Node n : node.children){
                nodes.push(n);
            }
        }
        Collections.reverse(res);
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root == null)return lists;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> integers = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                for(Node n : node.children){
                    queue.add(n);
                }
                integers.add(node.val);
            }
            lists.add(integers);
        }
        return lists;
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;                                   // return 0 for null node
        }
        if(root.children.size() > 0) {
            List<Integer> list = new ArrayList<>(root.children.size());
            for (Node node : root.children) {
                list.add(maxDepth(node));
            }
            Collections.sort(list);
            return list.get(list.size() - 1) + 1;
        }
        return 1;
    }


    public static void main(String [] args){
        NArrTreeSolution solution = new NArrTreeSolution();

        Node five = new Node(5,new ArrayList<>());

        Node six = new Node(6,new ArrayList<>());

        Node two = new Node(2,new ArrayList<>());
        Node four = new Node(4,new ArrayList<>());

        Node three = new Node(3, Arrays.asList(five,six));

        Node root = new Node(1,Arrays.asList(three,two,four));

        System.out.println(solution.maxDepth(root));

    }
}
