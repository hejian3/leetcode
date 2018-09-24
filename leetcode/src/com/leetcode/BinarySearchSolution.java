package com.leetcode;

import java.util.Arrays;

public class BinarySearchSolution {

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static int mySqrt(int x) {
        int left = 0;
        int right = x - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            long total = (long)mid * mid;
            if(total == x){ return mid; }
            else if(total < x) { left = mid + 1; }
            else { right = mid - 1; }
        }
        return (long)left * left > x? right : left;
    }

    public static int guessNumber(int n) {
        int left = 1;
        int right = n;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int result = guess(mid);
            if(result == 0)return mid;
            else if(result == -1)left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    public static int guess(int num){
        if(num < 6)return -1;
        if(num > 6)return 1;
        return 0;
    }

    public static int search2(int[] nums, int target) {
        if(nums.length == 0)return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) /2;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[left] == target) {
                return left;
            }else if(nums[right] == target){
                return right;
            }else if(nums[left] < nums[right]){
                if(nums[mid] > target){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else if(nums[mid] > nums[left]){
                if(nums[left] > target){
                    left = mid + 1;
                }else if(nums[mid] > target){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else if(nums[mid] < nums[right]){
                if(nums[mid] > target){
                    right = mid - 1;
                }else if(nums[right] < target){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else{
                return -1;
            }
        }
        return -1;
    }

    public static int firstBadVersion(int n) {
        int left = 1, right = n;
        if(isBadVersion(right)) {
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (isBadVersion(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if(isBadVersion(left))return left;
        }
        return -1;
    }

    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid1 = (left + right) /2;
            int mid2 = mid1 + 1;
            if(nums[mid1] < nums[mid2]){
                left = mid2;
            }else{
                right = mid1;
            }
        }
        return left;
    }

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (left + right) /2;
            if(nums[mid] < nums[right]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return nums[left];
    }


    public static int[] searchRange(int[] nums, int target) {
        int [] ret = new int[]{-1,-1};
        int left = 0, right = nums.length - 1;
        if(nums.length == 0 || nums[right] < target || nums[left] > target){
            return ret;
        }

        while (left + 1 < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                left = mid;
                right  = mid;
                break;
            }
        }

        while (left > 0 && nums[left - 1] == target){
            left--;
        }
        while (right < nums.length - 1 && nums[right + 1] == target){
            right++;
        }

        if(nums[left] == target) ret[0] = left;
        if(nums[right] == target){
            ret[1] = right;
            if(nums[left]!= target)ret[0] = ret[1];
        }else {
            ret[1] = ret[0];
        }
        return ret;
    }

    private static int[] searchHigh(int[] nums, int target){
        int lowerIndex = -1;
        int hightIndex = -1;
        int left = 0, right = nums.length;
        while (left  < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        lowerIndex  = left;

        left = 0;
        right = nums.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        hightIndex = left;

        if(lowerIndex == hightIndex)return new int[]{-1,-1};
        else return new int[]{lowerIndex,hightIndex -1};
    }

    //TODO
    private static boolean isBadVersion(int version){
        return false;
    }

    public static void main(String [] args){

        System.out.println(Arrays.toString(searchHigh(new int[]{3,3,3},3)));
        //[0,0]
        System.out.println(Arrays.toString(searchHigh(new int[]{1,3},1)));
        //[1,1]
        System.out.println(Arrays.toString(searchHigh(new int[]{1,3},3)));
        //[1,1]
        System.out.println(Arrays.toString(searchHigh(new int[]{1,2,3},2)));

        System.out.println(Arrays.toString(searchHigh(new int[]{5,7,7,8,8,10},8)));
        System.out.println(Arrays.toString(searchHigh(new int[]{5,7,7,8,8,10},7)));
        System.out.println(Arrays.toString(searchHigh(new int[]{5,7,7,8,8,10},6)));

    }
}