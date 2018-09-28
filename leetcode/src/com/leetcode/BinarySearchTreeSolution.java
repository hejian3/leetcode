package com.leetcode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTreeSolution {

    public boolean isValidBST(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))return true;
        return isValidBSTHelper(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode root,long high,long low){
        if(root.val >= high || root.val <= low)return false;
        boolean flag = true;
        if(root.left !=null) flag = isValidBSTHelper(root.left,root.val,low);
        if(root.right !=null) flag = flag && isValidBSTHelper(root.right,high,root.val);
        return flag;
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;

        }
        return list;
    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0) break;
            root = root.right;
        }
        return root.val;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null)return null;
        if(root.val == val)return root;
        if(root.val > val)return searchBST(root.left,val);
        return searchBST(root.right,val);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.val > val)root.left =  insertIntoBST(root.left,val);
        if(root.val < val)root.right = insertIntoBST(root.right,val);
        return root;
    }


    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)return null;
        if(root.val > key)root.left = deleteNode(root.left,key);
        if(root.val < key)root.right = deleteNode(root.right,key);
        if(root.val == key){
            if(root.right == null)return root.left;
            else if(root.left == null)return root.right;
            else {
                TreeNode temp = root.right;
                while (temp.left != null){
                    temp = temp.left;
                }
                root.val = temp.val;
                root.right = deleteNode(root.right,root.val);
            }
        }
        return root;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left !=null && right!=null)return root;
        if(left !=null)return left;
        return right;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        return false;
    }

    public static void main(String [] args){

        BinarySearchTreeSolution searchTreeSolution = new BinarySearchTreeSolution();

        TreeNode three = new TreeNode(3);
        TreeNode zero = new TreeNode(0);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode five = new TreeNode(5);
        TreeNode four = new TreeNode(4);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);


        five.left = two;
        five.right = six;
        two.left = one;
        two.right =four;
        four.left = three;
        six.right = seven;
        six.left = zero;

        TreeNode node = searchTreeSolution.deleteNode(five,6);
        System.out.println(node);

        KthLargest kthLargest1 = new KthLargest(3,new int[]{4,5,8,2});
        System.out.println(kthLargest1.add(3));   // returns 4
        System.out.println(kthLargest1.add(5));   // returns 5
        System.out.println(kthLargest1.add(10));  // returns 5
        System.out.println(kthLargest1.add(9));   // returns 8
        System.out.println(kthLargest1.add(4));   // returns 8

        KthLargest kthLargest2 = new KthLargest(1,new int[0]);
        System.out.println(kthLargest2.add(-3));
        System.out.println(kthLargest2.add(-2));
        System.out.println(kthLargest2.add(-4));
        System.out.println(kthLargest2.add(0));
        System.out.println(kthLargest2.add(4));


        KthLargest kthLargest3 = new KthLargest(2,new int[]{0});
        System.out.println(kthLargest3.add(-1));
        System.out.println(kthLargest3.add(1));
        System.out.println(kthLargest3.add(-2));
        System.out.println(kthLargest3.add(-4));
        System.out.println(kthLargest3.add(3));
    }

    public class BSTIterator {

        private Stack<TreeNode> stack = new Stack<>();

        private TreeNode next;

        public BSTIterator(TreeNode root) {
            if(root == null)return;
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            next = stack.pop();
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return next != null;
        }

        /** @return the next smallest number */
        public int next() {
            if(hasNext()){
                int val = next.val;
                TreeNode root = next.right;
                if(root != null || !stack.isEmpty()){
                    while(root != null){
                        stack.push(root);
                        root = root.left;
                    }
                    next = stack.pop();
                }else{
                    next = null;
                }
                return val;
            }
            return -1;
        }
    }

    static class KthLargest {

        class TreeNode {
            int val, count = 1;
            TreeNode left, right;
            TreeNode(int v) { val = v; }
        }

        private int k;
        private TreeNode root;

        private TreeNode insertIntoBST(TreeNode root, int val) {
            if(root == null) return new TreeNode(val);
            root.count++;
            if(root.val > val)root.left =  insertIntoBST(root.left,val);
            if(root.val <= val)root.right = insertIntoBST(root.right,val);
            return root;
        }

        public KthLargest(int k, int[] nums) {
            this.k = k;
            TreeNode root = null;
            for(int i = 0; i < nums.length; i++){
                root = insertIntoBST(root,nums[i]);
            }
            this.root = root;
        }

        public int add(int val) {
            root = insertIntoBST(root,val);
            TreeNode node = root;
            int count = k;
            while (count > 0){
                int rightCount = 1 + (node != null && node.right != null ? node.right.count : 0);
                if(count == rightCount)break;
                if(count > rightCount){
                    node = node.left;
                    count -= rightCount;
                }else node = node.right;
            }
            return node.val;
        }
    }
}
