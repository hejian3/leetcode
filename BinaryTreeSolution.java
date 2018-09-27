package com.leetcode;

import java.util.*;

public class BinaryTreeSolution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderIterative(root,res);
        return res;
    }

    private static void preorderRecursive(TreeNode node,List<Integer> res){
        if(node == null)return;
        res.add(node.val);
        preorderRecursive(node.left,res);
        preorderRecursive(node.right,res);
    }

    private static void preorderIterative(TreeNode node,List<Integer>res){
        if(node == null)return;
        Stack<TreeNode> nodes = new Stack<>();
        res.add(node.val);
        nodes.add(node);
        preorderIterativeLeft(node,res,nodes);
        while (!nodes.isEmpty()){
            TreeNode right = nodes.pop();
            right = right.right;
            if(right!=null) {
                res.add(right.val);
                nodes.add(right);
                preorderIterativeLeft(right, res, nodes);
            }
        }
    }

    private static void preorderIterativeLeft(TreeNode node,List<Integer>res,Stack<TreeNode> nodes){
        TreeNode left = node.left;
        while(left !=null){
            res.add(left.val);
            nodes.add(left);
            left = left.left;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderIterative(root,res);
        return res;
    }

    private static void inorderRecursive(TreeNode node,List<Integer> res){
        if(node == null)return;
        inorderRecursive(node.left,res);
        res.add(node.val);
        inorderRecursive(node.right,res);
    }

    private static void inorderIterative(TreeNode node,List<Integer>res){
        if(node == null)return;
        Stack<TreeNode> nodes = new Stack<>();
        nodes.add(node);
        inorderIterativeLeft(node,res,nodes);
        while (!nodes.isEmpty()){
            TreeNode right = nodes.pop();
            if(right !=null){
                res.add(right.val);
                right = right.right;
                if(right!=null) {
                    res.add(right.val);
                    nodes.add(right);
                    inorderIterativeLeft(right, res, nodes);
                }
            }
        }
    }

    private static void inorderIterativeLeft(TreeNode node,List<Integer>res,Stack<TreeNode> nodes){
        TreeNode left = node.left;
        while(left !=null){
            res.add(left.val);
            nodes.add(left);
            left = left.left;
        }
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderIterative(root,res);
        return res;
    }

    private static void postorderRecursive(TreeNode node,List<Integer> res){
        if(node == null)return;
        postorderRecursive(node.left,res);
        postorderRecursive(node.right,res);
        res.add(node.val);
    }

    private static void postorderIterative(TreeNode node,List<Integer>res){
        if(node == null)return;
        postorderIterativeLeft(node,res);
        postorderIterativeRight(node,res);
        res.add(node.val);
    }

    private static void postorderIterativeLeft(TreeNode node,List<Integer>res){
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode left = node.left;
        while(left !=null){
            nodes.add(left);
            left = left.left;
        }

        while (!nodes.isEmpty()) {
            TreeNode n = nodes.pop();
            if (n.right != null) {
                postorderIterativeLeft(n.right, res);
                postorderIterativeRight(n.right,res);
                res.add(n.right.val);
            }
            res.add(n.val);
        }
    }

    private static void postorderIterativeRight(TreeNode node,List<Integer>res){
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode right = node.right;
        while (right!=null) {
            nodes.add(right);
            postorderIterativeLeft(right, res);
            right = right.right;
        }

        while (!nodes.isEmpty()) {
            TreeNode n = nodes.pop();
            res.add(n.val);
        }
    }

    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (root == null) {
            return result;
        }
        stack1.push(root);
        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.push(root);
            if (root.left != null) {
                stack1.push(root.left);
            }
            if (root.right != null) {
                stack1.push(root.right);
            }
        }
        while (!stack2.isEmpty()) {
            root = stack2.pop();
            result.add(root.val);
        }
        return result;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root == null)return lists;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> integers = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left !=null)queue.add(node.left);
                if(node.right !=null) queue.add(node.right);
                integers.add(node.val);
            }
            lists.add(integers);
        }
        return lists;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;                                   // return 0 for null node
        }
        int left_depth = maxDepth(root.left);
        int right_depth = maxDepth(root.right);
        return Math.max(left_depth, right_depth) + 1;
    }

    private class MaxDepthSolution{

        private int answer;

        public int maxDepth2(TreeNode root) {
            if (root == null) {
                return 0; // return 0 for null node
            }
            maximum_depth(root,1);
            return answer;
        }

        private void maximum_depth(TreeNode root, int depth) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                answer = Math.max(answer, depth);
            }
            maximum_depth(root.left, depth + 1);
            maximum_depth(root.right, depth + 1);
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricRecursive(root.left,root.right);
    }

    private static boolean isSymmetricIteratively(TreeNode root){
        if(root == null)return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            if(queue.get(0) != root){
                if(size % 2 != 0)return false;
                for(int i = 0; i < size / 2; i++){
                    TreeNode left = queue.get(i);
                    TreeNode right = queue.get(size - 1 - i);
                    if(left.val != right.val
                            || (left.left != null && right.right ==null)
                            || (left.right!= null && right.left == null)
                            || (left.left == null && right.right!=null)
                            || (left.right == null && right.left!=null)){
                        return false;
                    }
                }
            }

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return true;
    }

    private static boolean isSymmetricRecursive(TreeNode left,TreeNode right){
        if(left == null || right == null) return left == right;
        if(left.val != right.val)return false;
        return isSymmetricRecursive(left.left,right.right) && isSymmetricRecursive(left.right,right.left);
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)return false;
        if(root.left == null && root.right == null)return root.val == sum;
        return hasPathSum(root.left,sum - root.val) || hasPathSum(root.right,sum - root.val);
    }

    public static TreeNode buildTreeOfIP(int[] inorder, int[] postorder) {
        if(inorder.length == 0 || postorder.length == 0)return null;
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        int index = -1;
        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == root.val){
                index = i;
                break;
            }
        }

        int linorder [] = Arrays.copyOfRange(inorder,0,index);
        int rinorder [] = Arrays.copyOfRange(inorder,index + 1,inorder.length);

        int lpostorder[] = Arrays.copyOfRange(postorder,0,linorder.length);
        int rpostorder[] = Arrays.copyOfRange(postorder,linorder.length,postorder.length -1 );

        root.left = buildTreeOfIP(linorder,lpostorder);
        root.right = buildTreeOfIP(rinorder,rpostorder);
        return root;
    }

    public static TreeNode buildTreeOfPI(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0)return null;
        TreeNode root = new TreeNode(preorder[0]);

        int index = -1;
        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == root.val){
                index = i;
                break;
            }
        }

        int []lpreorder = Arrays.copyOfRange(preorder,1,index + 1);
        int []rpreorder = Arrays.copyOfRange(preorder,index + 1,preorder.length);

        int linorder [] = Arrays.copyOfRange(inorder,0,index);
        int rinorder [] = Arrays.copyOfRange(inorder,index + 1,inorder.length);

        root.left = buildTreeOfPI(lpreorder,linorder);
        root.right = buildTreeOfPI(rpreorder,rinorder);

        return root;
    }

    public static class TreeLinkNode {
      int val;
      TreeLinkNode left, right, next;
      TreeLinkNode(int x) { val = x; }
    }

    public static void connect(TreeLinkNode root) {
        connectRecursiveHelper2(root,null);
    }

    private static void connectIteratively(TreeLinkNode root){
        if(root == null)return;
        LinkedList<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();
                if(i != size -1){
                    node.next = queue.peek();
                }
                if(node.left !=null)queue.add(node.left);
                if(node.right !=null) queue.add(node.right);
            }
        }
    }

    private static void connectRecursive(TreeLinkNode root){
        if(root == null)return;
        connectRecursiveHelper(root.left,root.right);
    }

    private static void connectRecursiveHelper(TreeLinkNode left,TreeLinkNode right){
        if(left == null || right == null)return;
        left.next = right;
        connectRecursiveHelper(left.left,left.right);
        connectRecursiveHelper(left.right, right.left);
        connectRecursiveHelper(right.left,right.right);
    }

    private static void connectRecursiveHelper2(TreeLinkNode root,TreeLinkNode next){
        if(root == null)return;
        root.next  = next;
        while (next !=null && next.left == null && next.right == null){
            next = next.next;
        }
        TreeLinkNode childNext = next == null?null:next.left != null?next.left:next.right;

        if(root.right ==null){
            connectRecursiveHelper2(root.left,childNext);
        }else{
            connectRecursiveHelper2(root.right,childNext);
            connectRecursiveHelper2(root.left,root.right);
        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left !=null && right!=null)return root;
        if(left !=null)return left;
        return right;
    }

    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q)return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left !=null && right!=null)return root;
        if(left !=null)return left;
        return right;
    }

    private static TreeNode lowestCommonAncestorIteratively(TreeNode root, TreeNode p, TreeNode q){
        LinkedList<TreeNode> pQueue = new LinkedList<>();
        LinkedList<TreeNode> qQueue = new LinkedList<>();
        findPath(root,p,pQueue);
        findPath(root,q,qQueue);

        int minLen = Math.min(pQueue.size(),qQueue.size());

        for(int i = 0; i < minLen;i++){
            if(pQueue.get(i) != qQueue.get(i)){
                return pQueue.get(i - 1);
            }
        }
        return pQueue.size() < qQueue.size()?pQueue.getLast():qQueue.getLast();

    }

    private static boolean findPath(TreeNode root,TreeNode node,LinkedList<TreeNode> queue){
        if(root == null)return false;
        queue.add(root);
        if(root == node)return true;
        int len = queue.size();
        if(findPath(root.left,node,queue))return true;
        while (queue.size() > len){
            queue.removeLast();
        }
        return findPath(root.right,node,queue);
    }

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null)return "[]";
            return "[" + String.valueOf(root.val) +","+serialize(root.left,root.right) +"]";
        }

        private String serialize(TreeNode left,TreeNode right) {
            StringBuilder stringBuilder = new StringBuilder();
            if (left == null) stringBuilder.append("null");
            else stringBuilder.append(left.val);
            stringBuilder.append(",");
            if (right == null) stringBuilder.append("null");
            else stringBuilder.append(right.val);
            if(left == null && right == null)return stringBuilder.toString();
            else if(left !=null && right !=null)return stringBuilder.append(",").append(serialize(left.left,left.right)).
                    append(",").append(serialize(right.left,right.right)).toString();
            else if(left != null )return stringBuilder.append(",").append(serialize(left.left,left.right)).toString();
            else return stringBuilder.append(",").append(serialize(right.left,right.right)).toString();

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data.length() == 2) return null;
            String [] arr = data.substring(1,data.length() -1).split(",");
            Queue<String> queue = new ArrayDeque<>(Arrays.asList(arr));
            TreeNode root = new TreeNode(Integer.valueOf(queue.poll()));
            deserialize(queue,root);
            return root;
        }

        private void deserialize(Queue<String> queue,TreeNode root){
            String leftStr = queue.poll();
            String rightStr = queue.poll();
            if(!leftStr.equals("null")) {
                root.left = new TreeNode(Integer.valueOf(leftStr));
                deserialize(queue,root.left);
            }

            if(!rightStr.equals("null")) {
                root.right = new TreeNode(Integer.valueOf(rightStr));
                deserialize(queue,root.right);
            }
        }
    }

    public static void main(String[]args){
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode six = new TreeNode(6);
        TreeNode two = new TreeNode(2);
        TreeNode seven = new TreeNode(7);
        TreeNode four = new TreeNode(4);
        TreeNode zero = new TreeNode(0);
        TreeNode eight = new TreeNode(8);

        //one.left = two;
        //one.right = three;
        //three.left = four;
        //three.right = five;

        one.left = two;
        one.right = three;
        two.left = new TreeNode(11);
        two.right = new TreeNode(33);

        three.left = new TreeNode(22);
        three.right = new TreeNode(44);

        Codec codec = new Codec();
        String str = codec.serialize(one);
        System.out.println(str);
        TreeNode root = codec.deserialize(str);
        System.out.println(root);
    }
}
