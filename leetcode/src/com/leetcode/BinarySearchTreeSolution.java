package com.leetcode;

import java.util.*;

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
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }

        final TreeSet<Long> set = new TreeSet<>();
        for (int ind = 0; ind < nums.length; ind++) {

            final Long floor = set.floor(nums[ind] + (long)t);
            final Long ceil = set.ceiling(nums[ind] - (long)t);
            if ((floor != null && floor >= nums[ind])
                || (ceil != null && ceil <= nums[ind])) {
                return true;
            }

            set.add((long)nums[ind]);

            if (ind >= k) {
                set.remove((long)nums[ind - k]);
            }
        }
        return false;
    }


    public boolean isBalanced(TreeNode root) {
        if(root == null)return true;
        boolean flag =  Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1;
        return flag && isBalanced(root.left) && isBalanced(root.right);
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;                                   // return 0 for null node
        }
        int left_depth = maxDepth(root.left);
        int right_depth = maxDepth(root.right);
        return Math.max(left_depth, right_depth) + 1;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0)return null;
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums,0,mid));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums,mid + 1,nums.length));
        return root;
    }

    public static void main(String [] args){
        BinarySearchTreeSolution searchTreeSolution = new BinarySearchTreeSolution();
        TreeNode root = searchTreeSolution.sortedArrayToBST(new int[]{-10,-3,0,5,9});
        System.out.println(root);
        root = searchTreeSolution.sortedArrayToBST(new int[]{0,1,2,3,4,5});
        System.out.println(root);
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
