package com.leetcode;

public class BSTSolution {
	
	 public static class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
     }
	 
	 
	 public TreeNode deleteNode(TreeNode root, int key) {
		 if(root == null)return null;
		 if(root.val == key) {
			 if(root.left == null && root.right == null){
			 	return null;
			 } else if(root.left != null){
			 	root.left.right = root.right;
			 	return root.left;
			 } else{
			 	return root.right;
			 }
		 }
		 deleteNodeHelper(root,key);
		 return root;
	 }
	 
	 private void deleteNodeHelper(TreeNode root,int key) {
		if(root.val > key) {
			if(root.left != null) {
				if(root.left.val == key) {
					TreeNode right = root.left.right;
					if(right != null) {
						TreeNode parent = right;
						while(right != null && right.left != null) {
							parent = right;
							right = right.left;
						}
						TreeNode left = root.left;
						if(parent == right){
							right.left = left.left;
							right.right = null;
							root.left = right;
						}else {
							right.left = left.left;
							right.right = left.right;
							root.left = right;
							parent.left = null;
						}
					}else {
						root.left = root.left.left;
					}
				}else {
					deleteNodeHelper(root.left,key);
				}
			}
		}else {
			if(root.right != null) {
				if(root.right.val == key) {
					root.right = root.right.right;
				}else {
					deleteNodeHelper(root.right,key);
				}
			}
		}
	 }
	 
	 public static void main(String[]args) {
		 TreeNode two = new TreeNode(2);
		 TreeNode three = new TreeNode(3);
		 TreeNode four = new TreeNode(4);
		 TreeNode five = new TreeNode(5);
		 TreeNode six = new TreeNode(6);
		 TreeNode seven = new TreeNode(7);
		 
		 //five.left = three;
		 //five.right =six;
		 
		 //three.left = two;
		 //three.right = four;
		 
		 //six.right = seven;

		 two.left = new TreeNode(1);
		 two.right = three;

		 
		 BSTSolution solution = new BSTSolution();
		 //TreeNode node = solution.deleteNode(five, 3);

		 //TreeNode node = solution.deleteNode(five, 6);

		 TreeNode node = solution.deleteNode(two, 2);


		 System.out.println(node);
	 }

}
