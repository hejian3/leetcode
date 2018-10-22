package com.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;
import java.util.*;

public class Solution {

    private static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
   }

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    };

    public static ListNode detectCycle(ListNode head) {
        ListNode faster = null;
        ListNode slower = null;
        if(head == null || head.next == null){
            return null;
        }else{
            faster = head.next;
            slower = head;
            while(true){
                if(faster == slower){
                    return slower;
                }else if(faster.next == null || faster.next.next == null){
                    return null;
                }
                faster = faster.next.next;
                slower = slower.next;
            }
        }
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode oddTail = null;
        ListNode even = null;
        ListNode start = head;
        ListNode prev = null;
        while(start != null){
            oddTail = start;
            if(start.next != null){
                if(prev == null){
                    even = start.next;
                    prev = even;
                }else {
                    prev.next = start.next;
                    prev = start.next;
                }
                start.next = start.next.next;
                start = start.next;
                prev.next = null;
            }else{
                break;
            }
        }
        oddTail.next = even;
        return head;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 ==null && l2 == null)return null;
        ListNode head = null;
        ListNode cur  = null;
        ListNode node = null;
        while(l1 !=null || l2!=null) {
            if(l1 != null && l2 != null){
                if(l1.val <= l2.val){
                    node = l1;
                }else{
                    node = l2;
                }
            }else if(l1 !=null){
                node = l1;
            }else{
                node = l2;
            }

            if(node!=null){
                if (cur == null) {
                    head = node;
                    cur = node;
                    if(node == l1) {
                        l1 = l1.next;
                    }else {
                        l2 = l2.next;
                    }
                } else {
                    cur.next = node;
                    if(node == l1) {
                        l1 = l1.next;
                    }else {
                        l2 = l2.next;
                    }
                    cur = cur.next;
                }
                node = null;
            }
        }
        return head;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
   /*     StringBuffer numStr1 = new StringBuffer();
        StringBuffer numStr2 = new StringBuffer();
        ListNode head = null;
        while(l1!=null){
            numStr1.append(l1.val);
            l1 = l1.next;
        }
        while(l2!=null){
            numStr2.append(l2.val);
            l2 = l2.next;
        }
        BigDecimal num1 = new BigDecimal(numStr1.reverse().toString());
        BigDecimal num2 = new BigDecimal(numStr2.reverse().toString());
        String result = num1.add(num2).toPlainString();
        ListNode cur = null;
        for(int i = result.length() -1 ; i >=0 ;i--){
            if(head == null){
                head = new ListNode(result.charAt(i) - 48 );
                cur  = head;
            }else{
                cur.next = new ListNode(result.charAt(i) - 48);
                cur = cur.next;
            }
        }
        return head;*/

        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }

    public static Node flatten(Node head) {
        if(head == null || (head.next == null && head.child == null))return head;
        if(head.child !=null){
            Node prev = head.next;
            head.next = head.child;
            head.child.prev = head;
            Node node = flatten(head.child);
            while(node!=null && node.next!=null){
                node = node.next;
            }
            if(prev !=null){
                prev.prev = node;
                node.next = prev;
                flatten(prev);
            }
            head.child = null;
        }else if(head.next!=null){
            flatten(head.next);
        }
        return head;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if(k <= 0 || head == null)return head;

        int size = 0;
        ListNode node = head;
        while(node != null){
            size++;
            node = node.next;
        }

        k = k % size;

        if(k == 0)return head;
        node = head;
        for(int i= 0; i < k; i++){
            node = node.next;
        }
        ListNode ret = null;
        if(node.next!=null) {
            ListNode n = node.next;
            node.next = null;
            ret = n;
            while (n != null && n.next != null) {
                n = n.next;
            }
            n.next = head;
        }else{
            ListNode n = head;
            while (n != null && n.next != null && n.next.next != null) {
                n = n.next;
            }
            ret = n.next;
            ret.next = head;
            n.next = null;
        }
        return ret;
    }

    public static int numIslands(char[][] grid) {
        if(grid.length ==0)return 0;
        int count = 0;
        for(int i =0; i < grid.length;i++){
            for(int j =0 ; j < grid[0].length; j++){
                if(grid[i][j] =='1'){
                    dsf(grid,i,j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dsf(char[][] grid,int i ,int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j]!='1')return;
        grid[i][j] = 0;
        dsf(grid,i-1,j);
        dsf(grid,i+1,j);
        dsf(grid,i,j-1);
        dsf(grid,i,j+1);
    }

    public static int openLock(String[] deadends, String target) {
        String initially ="0000";
        Set<String>deadendSet = new HashSet<>(Arrays.asList(deadends));
        if(deadendSet.contains(initially))return -1;
        String[] targetSlots = getAdj(target);
        int targetSlotCount = 0;
        for(String t : targetSlots){
            if(deadendSet.contains(t))targetSlotCount++;
        }
        if(targetSlotCount == deadends.length)return -1;
        ArrayDeque<String> queue = new ArrayDeque<>();
        Set<String> used = new HashSet<>();
        queue.add(initially);
        used.add(initially);
        int step = 0;
        while(!queue.isEmpty()){
            step++;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String slot = queue.getFirst();
                if(slot.equals(target))return step - 1;
                String []slots = getAdj(slot);
                for(String s : slots){
                    if(!deadendSet.contains(s) && !used.contains(s)) {
                        queue.add(s);
                        used.add(s);
                    }
                }
                queue.remove(slot);
            }
        }
        return -1;
    }

    private static String[] getAdj(String slot){
        String []slots = new String[8];
        slots[0] = slot.charAt(0)=='9'?"0"+slot.substring(1):(slot.charAt(0)-47) +slot.substring(1);
        slots[1] = slot.charAt(0)=='0'?"9"+slot.substring(1):(slot.charAt(0)-49) +slot.substring(1);

        slots[2] = slot.charAt(1)=='9'?slot.charAt(0) + "0" +slot.substring(2) : slot.charAt(0) +""+(slot.charAt(1) - 47) +slot.substring(2);
        slots[3] = slot.charAt(1)=='0'?slot.charAt(0) + "9" +slot.substring(2) : slot.charAt(0) +""+(slot.charAt(1) - 49) +slot.substring(2);

        slots[4] = slot.charAt(2)=='9'?slot.substring(0,2)+"0"+slot.charAt(3): slot.substring(0,2)+(slot.charAt(2) - 47) +slot.charAt(3);
        slots[5] = slot.charAt(2)=='0'?slot.substring(0,2)+"9"+slot.charAt(3): slot.substring(0,2)+(slot.charAt(2)-49) +slot.charAt(3);

        slots[6] = slot.charAt(3)=='9'?slot.substring(0,3)+"0":slot.substring(0,3)+(slot.charAt(3) - 47);
        slots[7] = slot.charAt(3)=='0'?slot.substring(0,3)+"9":slot.substring(0,3)+(slot.charAt(3) -49 );
        return slots;
    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static boolean isValid(String s){
        if(s == null || s.length() == 0)return true;
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ')' || c == ']' || c == '}'){
                if(stack.empty())return false;
                if(c == ')' && stack.pop()!='(')return false;
                if(c == ']' && stack.pop()!='[')return false;
                if(c == '}' && stack.pop()!='{')return false;
            }else{
                stack.push(s.charAt(i));
            }
        }
        if(!stack.empty())return false;
        return true;
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int [] days = new int [temperatures.length];
        for(int i = 0 ; i < temperatures.length; i++){
            int temp1 = temperatures[i];
            boolean flag = false;
            for(int j = i + 1; j < temperatures.length; j++){
                int temp2 = temperatures[j];
                if(temp2 > temp1){
                    flag = true;
                    days[i] = j - i;
                    break;
                }
            }
            if(!flag){
                days[i] = 0;
            }
        }
        return days;
    }

    /**
     * Input: ["2", "1", "+", "3", "*"]
     * Output: 9
     * Explanation: ((2 + 1) * 3) = 9
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for(String token : tokens){
            if("+".equals(token) || "-".equals(token)
                    || "*".equals(token) || "/".equals(token)){
                int num1 = Integer.valueOf(stack.pop());
                int num2 = Integer.valueOf(stack.pop());
                if("+".equals(token))stack.push(String.valueOf(num2 + num1));
                if("-".equals(token))stack.push(String.valueOf(num2 - num1));
                if("*".equals(token))stack.push(String.valueOf(num2 * num1));
                if("/".equals(token))stack.push(String.valueOf(num2 / num1));
            }else {
                stack.push(token);
            }
        }
        return Integer.valueOf(stack.pop());
    }

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<>(); }
    }

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>();
        return dsfCloneGraph(node,map);
    }

    private static UndirectedGraphNode dsfCloneGraph(UndirectedGraphNode node,Map<UndirectedGraphNode,UndirectedGraphNode>map){
        if(node.neighbors !=null && node.neighbors.size() >0 ){
            for(UndirectedGraphNode nei : node.neighbors){
                if(!map.containsKey(nei) && nei != node){
                    map.put(nei,dsfCloneGraph(nei,map));
                }
            }
        }
        if(!map.containsKey(node)){
            UndirectedGraphNode undirectedGraphNode = new UndirectedGraphNode(node.label);
            map.put(node,undirectedGraphNode);
            for(UndirectedGraphNode nei : node.neighbors){
                undirectedGraphNode.neighbors.add(map.get(nei));
            }
        }
        return map.get(node);
    }

    public static int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWays(nums,S,0);
    }

    private static int findTargetSumWays(int [] nums,int S,int start){
        if(start == nums.length){
            if(S == 0){
                return 1;
            }
            return 0;
        }
        return findTargetSumWays(nums,S + nums[start],start + 1)
                + findTargetSumWays(nums,S - nums[start],start + 1);
    }

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        Set<TreeNode> set = new HashSet<>();
        foreachNode(root,list,stack,set);
        return list;
    }

    private static void foreachNode(TreeNode node,List<Integer> list,Stack<TreeNode>stack,Set<TreeNode> set){
        if(node == null)return;
        while(true){
            TreeNode left = node.left;
            TreeNode right = node.right;
            if(left != null && !set.contains(left)){
                stack.push(node);
                node = left;
            }else{
                list.add(node.val);
                set.add(node);
                if(right !=null){
                    node = right;
                }else {
                    if (stack.isEmpty()) break;
                    node = stack.pop();
                }
            }
        }
    }

    public static String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder stringBuffer = new StringBuilder();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c =='[') {
                count++;
                stack.push(String.valueOf(c));
            }else if(c == ']'){
                count--;
                String temp = "";
                String t = stack.pop();
                while (t.charAt(0) !='['){
                    temp = t + temp;
                    t = stack.pop();
                }
                String number = "";
                t = stack.pop();
                while (t.charAt(0) >= '0' &&  t.charAt(0) <= '9'){
                    number = t + number;
                    if(stack.isEmpty())break;
                    t = stack.pop();
                }

                if(t.charAt(0) < '0' ||  t.charAt(0) > '9'){
                    stack.push(t);
                }

                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < Integer.valueOf(number); j++){
                    sb.append(temp);
                }
                if(count >0){
                    stack.push(sb.toString());
                }else{
                    stringBuffer.append(sb.toString());
                }
            }else if(count == 0 && c>='a' && c<='z'){
                stringBuffer.append(c);
            }else{
                stack.push(String.valueOf(s.charAt(i)));
            }
        }
        return stringBuffer.toString();
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private static void fill(int[][] image, int sr, int sc, int color, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) return;
        image[sr][sc] = newColor;
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }


    public static int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i =0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    queue.add(new int[]{i,j});
                }else{
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] neibers = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};

        while (!queue.isEmpty()){
            int [] xy1 = queue.poll();
            int x1 = xy1[0];
            int y1 = xy1[1];
            for(int [] xy2 : neibers){
                int x2 = x1 + xy2[0];
                int y2 = y1 + xy2[1];
                if(x2 >=0 && x2 <= m -1 && y2 >=0 && y2 <= n - 1 && matrix[x2][y2] > matrix[x1][y1] + 1){
                    queue.add(new int[]{x2,y2});
                    matrix[x2][y2] = matrix[x1][y1] + 1;
                }
            }
        }
        return matrix;
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[]flag = new boolean[rooms.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        queue.add(0);
        set.add(0);

        while (!queue.isEmpty()){
            int roomNo = queue.poll();
            flag[roomNo] = true;
            for(int no : rooms.get(roomNo)){
                if(!set.contains(no)) {
                    set.add(no);
                    queue.add(no);
                }
            }
        }

        for(boolean f : flag){
            if(!f)return false;
        }
        return true;
    }


    public static int pivotIndex(int[] nums) {
        if(nums == null || nums.length < 3)return -1;
        for(int i = 0;i < nums.length ; i++){
            int sumLeft = 0;
            for(int j = 0; j < i;j++){
                sumLeft += nums[j];
            }
            int sumRight = 0;
            for(int k = i + 1;k < nums.length;k++){
                sumRight += nums[k];
            }

            if(sumLeft == sumRight){
                return i;
            }
        }
        return -1;
    }

    public static int dominantIndex(int[] nums) {
        if(nums == null || nums.length ==0)return -1;
        if(nums.length == 1)return 0;
        int max_num = -1;
        int max_num_index = -1;
        int second_max_num = -1;
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            if(max_num < num){
                if(max_num != -1){
                    second_max_num = max_num;
                }
                max_num = num;
                max_num_index = i;
            }else if(num > second_max_num){
                second_max_num = num;
            }

        }
        if(second_max_num ==0 || max_num / second_max_num >=2)return max_num_index;
        return -1;
    }

    public static int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0)return digits;
        for(int i = digits.length -1; i >= 0; i--){
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int [] ret = new int[digits.length + 1];
        ret[0] = 1;
        return ret;
    }


    public static int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        if(m <=0)return new int[0];
        int n = matrix[0].length;
        int [] ret = new int[m * n];
        ret[0] = matrix[0][0];
        ret[m * n - 1 ] = matrix[m - 1][n - 1];
        int x,y;
        if(n > 1){
            x = 0;
            y = 1;
        }else{
            x = 1;
            y = 0;
        }
        boolean flag = false;
        int index = 1;
        while(true){
            if(index >= m * n - 1)break;
            ret[index] = matrix[x][y];
            index++;
            if(!flag){
                y--;
                x++;
                if(x == m){
                    flag =!flag;
                    x--;
                    y+=2;
                }else if(y < 0){
                    flag =!flag;
                    y++;
                }
            }else{
                x--;
                y++;
                if(y == n){
                    flag = !flag;
                    x+=2;
                    y--;
                }else if(x < 0) {
                    flag = !flag;
                    x++;
                }
            }
        }
        return ret;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> integers = new ArrayList<>();
        int m = matrix.length;
        if(m <= 0)return integers;
        int n = matrix[0].length;
        int minX = 0, minY = 0 ,x = 0, y = 0;
        int maxX = m - 1 ,maxY = n - 1;
        int index = 0;
        int direction [] = new int[]{0,1};
        while(index < m * n){
            integers.add(matrix[x][y]);
            if(y == maxY && x == maxX && direction[0] == 1 && direction[1] == 0) {
                direction[0] = 0;
                direction[1] = -1;
                maxY--;
            }else if(x == minX && y >= maxY && direction[0] == 0  && direction[1] == 1){
                direction[0] = 1;
                direction[1] = 0;
                minX++;
            }else if(x == minX && y == minY && direction[0] == -1 && direction[1] == 0){
                direction[0] = 0;
                direction[1] = 1;
                minY++;
            }else if(x == maxX && y == minY && direction[0] ==0 && direction[1] == -1){
                direction[0] = -1;
                direction[1] = 0;
                maxX--;
            }
            x += direction[0];
            y += direction[1];
            index++;
        }
        return integers;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for(int i = 1; i <= numRows; i++){
            int index = 1;
            List<Integer> integers = new ArrayList<>();
            integers.add(1);
            while (index < i -1){
                integers.add(lists.get(i - 2).get(index) + lists.get(i - 2).get(index - 1));
                index ++ ;
            }
            if(i > 1) integers.add(1);
            lists.add(integers);
        }
        return lists;
    }

    public static String addBinary(String a, String b) {
        int a_len = a.length();
        int b_len = b.length();
        int max_len = Math.max(a.length(),b.length());
        char [] result = new char[max_len];
        int extra = 0;
        for(int i = max_len - 1; i >= 0; i--){
            char ac = '0';
            char bc = '0';
            if(a_len > 0)ac = a.charAt(a_len - 1);
            if(b_len > 0)bc = b.charAt(b_len - 1);

            if(ac =='0' && bc == '0'){
                result[i] = String.valueOf(extra).charAt(0);
                extra = 0;
            }else if(ac == '1' && bc == '1'){
                result[i] = String.valueOf(extra).charAt(0);
                extra = 1;
            }else if(extra  == 1){
                result[i] = '0';
                extra = 1;
            }else{
                result[i] = '1';
            }
            a_len--;
            b_len--;
        }
        if(extra == 1) return "1" + String.valueOf(result);
        return String.valueOf(result);
    }

    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)return "";
        int index = -1;
        StringBuilder stringBuilder = new StringBuilder();
        while(true){
            char s = '0';
            for(int i = 0; i < strs.length; i++){
                if(strs[i].length() < index + 2){
                    return stringBuilder.toString();
                }
                if(s == '0')s = strs[i].charAt(index + 1);
                else if(s != strs[i].charAt(index + 1))return stringBuilder.toString();
            }
            index++;
            stringBuilder.append(s);
        }
    }

    public static String reverseString(String s) {
        char [] arr = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while(start <= end){
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(arr);
    }

    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int total = 0;
        for(int i = 0; i < nums.length/2; i++){
            total = total + Math.min(nums[i * 2],nums[i * 2 + 1]);
        }
        return total;
    }

    public static int[] twoSum(int[] numbers, int target) {
        int [] ret = new int[]{-1,-1};
        for(int i = numbers.length -1; i > 0; i--) {
            if (target >= numbers[i] || i == 1) {
                ret[1] = i;
                ret[0] = i - 1;
                break;
            }
        }

        while (ret[0]>=0) {
            if (numbers[ret[0]] + numbers[ret[1]] == target) {
                ret[0] = ret[0] + 1;
                ret[1] = ret[1] + 1;
                return ret;
            }
            ret[0] = ret[0] - 1;
            if(ret[0] < 0){
                ret[1] = ret[1] - 1;
                ret[0] = ret[1] - 1;
            }
        }
        return ret;
    }


    public static int removeElement(int[] nums, int val) {
        int start = -1;
        int end = nums.length - 1;
        while(start < end){

            while(end >= 0 && nums[end] == val){
                end--;
            }

            if(end < 0 || start == end)break;

            start++;

            if(nums[start] == val){
                nums[start] = nums[end];
                nums[end] = val;
                end--;
            }

        }
        return start + 1;
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int current_len = 0;
        int max_len = 0;
        for(int i = 0; i < nums.length; i++){
            if (nums[i] == 1){
                current_len++;
            }else{
                if(current_len > max_len){
                    max_len = current_len;
                }
                current_len = 0;
            }
        }
        if(current_len > max_len)max_len = current_len;
        return max_len;
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int minLen = 0;
        int start = 0;
        int total = 0;
        while (start < nums.length - 1) {
            for (int i = start; i < nums.length; i++) {
                total = total + nums[i];
                if (total >= s) {
                    int currentLen = i - start + 1;
                    if (minLen == 0 || minLen > currentLen) {
                        minLen = i - start + 1;
                    }
                    break;
                }
            }
            start++;
            total = 0;
        }
        return minLen;
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        while (k > 0) {
            int temp = nums[nums.length - 1];
            for (int i = nums.length - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            k--;
            nums[0] = temp;
        }
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> integers = new ArrayList<>(rowIndex);
        for(int i = 1; i <= rowIndex  + 1; i++){
            integers.add(1);
            int mid = (i - 1) / 2;
            while (mid >= 1){
                integers.set(mid,integers.get(mid) + integers.get(mid-1));
                integers.set(i - mid -1,integers.get(mid));
                mid--;
            }
        }
        return integers;
    }


    public static String reverseWords(String s) {
        String []arrs = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = arrs.length - 1; i >= 0; i--){
            if (i < arrs.length -1 && arrs[i].length() > 0){
                stringBuilder.append(" ");
            }
            stringBuilder.append(arrs[i]);
        }
        return stringBuilder.toString();
    }

    public static String reverseWords2(String s) {
        String []arrs = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < arrs.length; i++){
            if (i > 0 && arrs[i].length() > 0){
                stringBuilder.append(" ");
            }
            for(int j = arrs[i].length() - 1; j >= 0; j--)
                stringBuilder.append(arrs[i].charAt(j));

        }
        return stringBuilder.toString();
    }

    public static int removeDuplicates(int[] nums) {
        int end = -1;
        for(int i = 0; i < nums.length; i++){
            if(i == 0){
                end++;
            } else if(nums[i] != nums[i - 1]){
                nums[end] = nums[i - 1];
                nums[end + 1] = nums[i];
                end++;
            }
        }
        return end + 1;
    }

    public static void moveZeroes(int[] nums) {
        int end = nums.length - 1;
        int start = 0;
        while (start < end){
            if(nums[start]==0){
                int index = start  + 1;
                while(index < nums.length && nums[index]==0){
                    index++;
                }
                if(index < nums.length) {
                    nums[start] = nums[index];
                    nums[index] = 0;
                }
            }
            start++;
        }
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i  = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static int singleNumber(int[] nums) {
        if(nums.length == 1)return nums[0];
        int start = 0;
        int end = nums.length - 1;
        while (start < nums.length - 1){
            boolean flag = false;
            for(int i = start + 1; i<= end; i++){
                if(nums[start] == nums[i]){
                    flag = true;
                    int temp = nums[end];
                    nums[end] = nums[i];
                    nums[i] = temp;
                    end--;
                    break;
                }
            }
            if(!flag)return nums[start];
            start++;
        }
        return nums[(nums.length + 1)/2];

        /**
         *  ^ 操作
         */
//
//        int result = 0;
//        for (int i = 0; i < nums.length; i++){
//            result = result ^ nums[i];
//        }
//        return result;
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums1.length;i++){
            for(int j = 0; j < nums2.length; j++){
                if(nums1[i] == nums2[j]){
                    set.add(nums1[i]);
                    break;
                }
            }
        }
        int [] ret =  new int[set.size()];
        int index = 0;
        for (int i : set){
            ret[index] = i;
            index++;
        }
        return ret;
    }

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true){
            if(set.contains(n))return false;
            set.add(n);
            String str = String.valueOf(n);
            int total = 0;
            for(int i = 0; i < str.length();i++){
                total += Math.pow(str.charAt(i) - 48,2);
            }
            if(total == 1)return true;
            n = total;
        }
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i],i);
        }
        for(int i = 0; i < nums.length;i++){
            Integer index = map.get(target - nums[i]);
            if(index != null && index != i){
                return new int[]{i,index};
            }
        }
        return null;
    }

    public static boolean isIsomorphic(String s, String t) {
        for(int i = 0; i < s.length();i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            int index1 = s.indexOf(c1,i + 1);
            int index2 = t.indexOf(c2,i + 1);
            while (index1 == index2 && index1 != -1){
                index1 = s.indexOf(c1,index1 + 1);
                index2 = t.indexOf(c2,index2 + 1);
            }
            if(index1 != index2)return false;
        }
        return true;
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0; i < list1.length; i++){
            map.put(list1[i],i);
        }

        int leastIndexSum = Integer.MAX_VALUE;
        List<String> result = new ArrayList<>();
        for(int i = 0; i < list2.length; i++){
            String res = list2[i];
            if(map.containsKey(res)){
                int sum = map.get(res) + i;
                if(sum < leastIndexSum){
                    result.clear();
                    leastIndexSum = sum;
                    result.add(res);
                }else if(sum == leastIndexSum){
                    result.add(res);
                }
            }
        }
        return result.toArray(new String[0]);
    }

    public static int firstUniqChar(String s) {
        Map<Character,List<Integer>> map = new HashMap<>();
        for(int i = 0;i<s.length(); i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,new ArrayList<>());
            }
            map.get(c).add(i);
        }
        for(int i = 0;i<s.length(); i++){
            if(map.get(s.charAt(i)).size() == 1)return i;
        }
        return -1;
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            if(!map.containsKey(nums1[i])){
                map.put(nums1[i],1);
            }else{
                map.put(nums1[i],map.get(nums1[i]) + 1);
            }
        }
        List<Integer> integers = new ArrayList<>();
        for(int i = 0; i < nums2.length; i++){
            if(map.containsKey(nums2[i])){
                integers.add(nums2[i]);
                int count = map.get(nums2[i]);
                if(count <= 1){
                    map.remove(nums2[i]);
                }else{
                    map.put(nums2[i],count - 1);
                }
            }
        }
        int []ret = new int[integers.size()];
        for(int i = 0; i<ret.length; i++){
            ret[i] = integers.get(i);
        }
        return ret;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer>map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                int index = map.get(nums[i]);
                if(i - index <=k)return true;
            }
            map.put(nums[i],i);
        }
        return false;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer,List<String>> map = new HashMap<>();
        int [] arr  = new int[26];
        for(String str : strs){
            Arrays.fill(arr,0);
            for(int i = 0; i < str.length();i++){
                arr[str.charAt(i)-'a'] +=1;
            }
            int key = Arrays.hashCode(arr);
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        List<List<String>>lists = new ArrayList<>(map.values());
        return lists;
    }

    public static boolean isValidSudoku(char[][] board) {

        for(int i = 0; i<9; i++){
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> columns = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0; j < 9;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(j/3);
                if(board[RowIndex + i%3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + i%3][ColIndex + j%3]))
                    return false;
            }
        }
        return true;
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String,List<TreeNode>> map = new HashMap<>();
        foreachTreeNode(root,map);
        List<TreeNode> dups = new ArrayList<>();
        for (List<TreeNode> group : map.values())
            if (group.size() > 1) dups.add(group.get(0));
        return dups;
    }

    private static String foreachTreeNode(TreeNode root, Map<String,List<TreeNode>> map){
        if(root == null)return "";
        String str = "(" + foreachTreeNode(root.left,map) + root.val + foreachTreeNode(root.right,map) +")";
        if(!map.containsKey(str))map.put(str,new ArrayList<>());
        map.get(str).add(root);
        return str;
    }

    public static int numJewelsInStones(String J, String S) {
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,1);
            }else{
                map.put(c,map.get(c) + 1);
            }
        }
        int total = 0;
        for(int i = 0; i < J.length(); i++){
            char c = J.charAt(i);
            if(map.containsKey(c)){
                total += map.get(c);
            }
        }
        return total;
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int maxLen = 0;
        for(int i = 0; i < s.length();i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,i);
            }else{
                int index = map.get(c);
                i = index;
                map.clear();
            }
            if(maxLen < map.size()){
                maxLen = map.size();
            }
        }
        return maxLen;
    }

    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,List<String>> mapAB = getSumMap(A,B);
        Map<Integer,List<String>> mapCD = getSumMap(C,D);

        int total = 0;
        for(Integer i : mapAB.keySet()){
            if(mapCD.containsKey(-i)){
                total += mapAB.get(i).size() * mapCD.get(-i).size();
            }
        }
        return total;
    }

    private static Map<Integer,List<String>> getSumMap(int []a,int []b){
        Map<Integer,List<String>> map = new HashMap<>();
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < b.length; j++){
                int sum = a[i] + b[j];
                String value = i +"_"+j;
                if(!map.containsKey(sum)){
                    map.put(sum,new ArrayList<>());
                }
                map.get(sum).add(value);
            }
        }
        return map;
    }


    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if(nums.length <= k){
            for(int num : nums)result.add(num);
        }else{

            Map<Integer,Integer> map = new HashMap<>();
            for(int num : nums){
                if(!map.containsKey(num)){
                    map.put(num,1);
                }else{
                    map.put(num,map.get(num) + 1);
                }
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> map.get(o2) - map.get(o1));
            for(int num : map.keySet()){
                queue.add(num);
            }
            for(int i = 0; i < k; i++){
                result.add(queue.poll());
            }
        }
        return result;
    }

    public static String longestPalindrome(String s) {
        char[] arrs = new char[s.length() * 2 + 2];
        int [] P = new int[arrs.length];
        arrs[0] = '$';
        arrs[1] = '#';
        int j = 2;
        for(int i = 0; i < s.length(); i++){
            arrs[j++] = s.charAt(i);
            arrs[j++] = '#';
        }

        int mx = 0;
        int id = 0;

        for(int i = 1; i < arrs.length; i++){
            if(mx > i){
                P[i] = Math.min(P[2 * id - i], mx - i);
            }else{
                P[i] = 1;
            }

            while (i + P[i] < arrs.length && arrs[i + P[i]] == arrs[i - P[i]])P[i]++;
            if(i + P[i] > mx){
                mx = i + P[i];
                id = i;
            }
        }

        int maxLen = -1;
        int maxIndex = 0;
        for(int i = 0; i < arrs.length; i++){
            if(P[i] - 1 > maxLen){
                maxLen = P[i] - 1;
                maxIndex = i;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = maxIndex - maxLen; i <= maxIndex + maxLen; i++){
            if(arrs[i] != '#')stringBuilder.append(arrs[i]);
        }
        return stringBuilder.toString();
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static int myAtoi(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(stringBuilder.length() == 0 && c != ' ' && c != '-' && c != '+' && (c < '0' || c > '9'))return 0;
            if(stringBuilder.length() > 0  && (c < '0' || c > '9'))break;
            if(c != ' ') stringBuilder.append(c);
        }
        if(stringBuilder.length() == 0 ||
                (stringBuilder.length() == 1 && (stringBuilder.charAt(0) =='-' || stringBuilder.charAt(0) =='+')))return 0;
        BigDecimal result = new BigDecimal(stringBuilder.toString());
        if(result.compareTo(new BigDecimal(Integer.MAX_VALUE)) >= 1)return Integer.MAX_VALUE;
        if(result.compareTo(new BigDecimal(Integer.MIN_VALUE)) <= -1)return Integer.MIN_VALUE;
        return result.intValue();
    }

    public boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber/10;
    }

    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r){
            maxArea = Math.max(maxArea,Math.min(height[l],height[r]) * (r - l));
            if(height[l] > height[r]){
                r++;
            }else{
                l++;
            }
        }
        return maxArea;
    }

    /**
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        return intToRoman(sb,num);
    }

    /**
     * nice job
     * @param num
     * @return
     */
    public static String intToRoman2(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

    private static String intToRoman(StringBuilder sb, int num){
        if(num == 0) return sb.toString();
        if(num / 1000 > 0){
            int count =  num / 1000;
            for (int i = 0; i < count; i++) {
                sb.append('M');
            }
            return intToRoman(sb,num - count * 1000);
        }else if(num / 100 > 0){
            int count = num / 100;
            if(count == 9){
                sb.append("CM");
            }else if(count > 5){
                sb.append('D');
                for(int i = 0; i < count - 5; i++){
                    sb.append('C');
                }
            }else if(count == 5){
                sb.append('D');
            }else if(count == 4){
                sb.append("CD");
            }else{
                for(int i = 0; i < count; i++){
                    sb.append('C');
                }
            }
            return intToRoman(sb,num - count * 100);
        }else if(num / 10 > 0){
            int count = num / 10;
            if(count == 9){
                sb.append("XC");
            }else if(count > 5){
                sb.append('L');
                for(int i = 0; i < count - 5; i++){
                    sb.append('X');
                }
            }else if(count == 5){
                sb.append('L');
            }else if(count == 4){
                sb.append("XL");
            }else{
                for(int i = 0; i < count; i++){
                    sb.append('X');
                }
            }
            return intToRoman(sb,num - count * 10);
        }else{
            if(num == 9){
                sb.append("IX");
            }else if(num > 5){
                sb.append('V');
                for(int i = 0; i < num - 5; i++){
                    sb.append('I');
                }
            }else if(num == 5){
                sb.append('V');
            }else if(num == 4){
                sb.append("IV");
            }else{
                for(int i = 0; i < num; i++){
                    sb.append('I');
                }
            }
            return intToRoman(sb,0);
        }
    }


    public static int romanToInt(String s) {
        char last = ' ';
        int total = 0;
        for(char c : s.toCharArray()){
            if(c == 'M') total += 1000;
            else if(c == 'D')total += 500;
            else if(c == 'C')total += 100;
            else if(c == 'L')total += 50;
            else if(c == 'X')total += 10;
            else if(c == 'V')total += 5;
            else if(c == 'I')total += 1;
            if(last =='I' && (c =='V' || c =='X')){
                total -= 2;
            }else if(last =='X' && (c =='L' || c =='C')) {
                total -= 20;
            }else if(last == 'C' && (c == 'D' || c == 'M')){
                total -= 200;
            }
            last = c;
        }
        return total;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1])continue;
            int res = - nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] == res) {
                    result.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    start++;
                    while (start < end && nums[start] == nums[start - 1]) start++;
                } else if (nums[start] + nums[end] < res) start++;
                else end--;
            }
        }
        return result;
    }


    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1])continue;
            int res = target - nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int total = nums[start] + nums[end] + nums[i];
                if(result == Integer.MAX_VALUE || Math.abs(result - target) > Math.abs(total - target)){
                    result = total;
                }
                if (nums[start] + nums[end] == res) return target;
                else if (nums[start] + nums[end] < res) start++;
                else end--;
            }
        }
        return result;
    }

    public static List<String> letterCombinations(String digits) {
        if(digits.isEmpty())return Collections.emptyList();
        String [] str = new String[]{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        return letterCombinationsRecursive(str,Arrays.asList(""),digits,0);
    }

    private static List<String> letterCombinationsRecursive(String [] str,List<String> list,String digits,int index){
        if(index >=digits.length())return list;
        List<String> res = new ArrayList<>();
        for(String s : list){
            for(char c : str[digits.charAt(index) - '2'].toCharArray()){
                res.add(s + c);
            }
        }
        return letterCombinationsRecursive(str,res,digits,index + 1);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1])continue;
            List<List<Integer>> res = threeSum(nums,target - nums[i],i + 1);
            if(!res.isEmpty()){
                for(List<Integer> integers : res){
                    integers.add(0,nums[i]);
                    result.add(integers);
                }
            }
        }
        return result;
    }

    private static List<List<Integer>> threeSum(int[] nums,int target,int index) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = index; i < nums.length; i++){
            if(i > index && nums[i] == nums[i - 1])continue;
            int res = target - nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] == res) {
                    List<Integer> list = new ArrayList<>(4);
                    list.add(nums[i]);
                    list.add(nums[start]);
                    list.add(nums[end]);
                    result.add(list);
                    start++;
                    while (start < end && nums[start] == nums[start - 1]) start++;
                } else if (nums[start] + nums[end] < res) start++;
                else end--;
            }
        }
        return result;
    }


    public static List<String> generateParenthesis(int n) {
        if(n == 1)return Arrays.asList("()");
        List<String> res = generateParenthesis(n - 1);
        Set<String> set = new HashSet<>();
        for(String str : res){
            set.add("()"+str);
            set.add("("+str+")");
            set.add(str+"()");
            int index = 0;
            int leftP = str.indexOf(")",0);
            while (leftP != -1) {
                if(str.indexOf("(",index) > leftP) {
                    set.add("(" + str.substring(0, leftP) + ")" + str.substring(leftP));
                }
                index = leftP + 1;
                leftP = str.indexOf(")",index);
            }
            index = str.length() - 1;
            int rightP = str.lastIndexOf("(");
            while (rightP != -1) {
                if(str.lastIndexOf(")",index) > rightP) {
                    set.add(str.substring(0, rightP) + "(" + str.substring(rightP) + ")");
                }
                index = rightP - 1;
                rightP = str.lastIndexOf("(",index);
            }
        }
        return new ArrayList<>(set);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)return null;
        ListNode ret = null;
        ListNode cur = null;
        ListNode minNode = null;
        int index = -1;
        while (true) {
            for (int i = 0; i < lists.length; i++) {
                ListNode node = lists[i];
                if(node == null)continue;
                if (minNode == null || minNode.val > node.val) {
                    minNode = node;
                    index = i;
                }
            }
            if(index == -1)break;
            if(ret == null){
                ret =  minNode;
                cur = minNode;
            }else{
                cur.next = minNode;
                cur = cur.next;
            }
            lists[index] = minNode.next;
            minNode.next = null;
            minNode = null;
            index = -1;
        }
        return ret;
    }


    public static void main(String[]args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        System.out.println(mergeKLists(new ListNode[]{node1,node2,node3}));
    }
}