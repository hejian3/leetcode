package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int left = 0, right = arr.length - k - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) left = mid + 1;
            else right = mid -1;
        }
        for (int i = 0; i < k; i++) {
            res.add(arr[i + left]);
        }
        return res;
    }


    public static double myPow(double x, int n) {
        if(n >= 0){
            if(n == 0)return 1;
            if(n == 1)return x;
            int count = 1;
            double result = x;
            while (count < n/2){
                result = result * result;
                count *= 2;
            }
            result = result * myPow(x,n - count);
            return result;
        }
        if(n == Integer.MIN_VALUE){
            return 1 / myPow(x,Integer.MAX_VALUE) / x;
        }
        return 1 / myPow(x,Math.abs(n));
    }

    public static boolean isPerfectSquare(int num) {
        long low = 0;
        long high = num;
        while (low <= high){
            long mid = (low + high) / 2;
            long square = mid * mid;
            if(square == num){
                return true;
            }else if(square > num){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return false;
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        int high = letters.length;
        while (low < high){
            int mid = low + (high - low) / 2;
            if(letters[mid] == target){
                low = mid;
                high = mid;
            }else if(letters[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        if(low >= letters.length || low < 0)return letters[0];
        if(letters[low] <= target){
            while (letters[low] <= target){
                low++;
                if(low >= letters.length)return letters[0];
            }
            return letters[low];
        }
        return letters[low];
    }

    public static int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (left + right) / 2;
            if(nums[mid] == nums[left] && nums[right] == nums[mid]){
                if(mid < right - 1){
                    right--;
                }else{
                    left++;
                }
            }else if(nums[mid] <= nums[right]) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    public static int findDuplicate(int[] nums) {
        int [] arr = Arrays.copyOf(nums,nums.length);
        Arrays.sort(arr);
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] == arr[i + 1])return arr[i];
        }
        return arr[0];
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int [] arr = new int[nums1.length + nums2.length];
        int nums1Index = 0;
        int nums2Index = 0;
        int count = 0;
        while (count < arr.length){
            if(nums1Index >= nums1.length){
                arr[count++] = nums2[nums2Index++];
            }else if(nums2Index >= nums2.length){
                arr[count++] = nums1[nums1Index++];
            }else if(nums1[nums1Index] > nums2[nums2Index]){
                arr[count++] = nums2[nums2Index++];
            }else{
                arr[count++] = nums1[nums1Index++];
            }
        }
        if(arr.length % 2 == 0){
            return (arr[arr.length / 2] + arr[arr.length/2 - 1])/2.0;
        }else{
            return arr[arr.length / 2];
        }
    }

    public static int smallestDistancePair(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);

        // Minimum absolute difference
        int low = a[1] - a[0];
        for (int i = 1; i < n - 1; i++)
            low = Math.min(low, a[i + 1] - a[i]);

        // Maximum absolute difference
        int high = a[n - 1] - a[0];

        // Do binary search for k-th absolute difference
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countPairs(a, mid) < k)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    // Returns number of pairs with absolute difference less than or equal to mid.
    private static int countPairs(int[] a, int mid) {
        int n = a.length, res = 0;
        for (int i = 0; i < n; ++i) {
            int j = i;
            while (j < n && a[j] - a[i] <= mid) j++;
            res += j - i - 1;
        }
        return res;
    }

    //
    public static int splitArray(int[] nums, int m) {
        int max = 0; long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int)sum;
        //binary search
        long l = max; long r = sum;
        while (l <= r) {
            long mid = (l + r)/ 2;
            if (valid(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)l;
    }

    public static boolean valid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for(int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }



    public static void main(String [] args){
        System.out.println(splitArray(new int[]{2,16,14,15},2));
        System.out.println(splitArray(new int[]{7,2,5,10,8},2));
        System.out.println(smallestDistancePair(new int[]{1,3,1},1));
    }
}