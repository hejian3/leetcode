package com.leetcode;

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
        System.out.println(nums);
    }


    public static void main(String[]args){
        moveZeroes(new int[]{0,1,0,3,12});

        moveZeroes(new int[]{0,0,1});
    }
}