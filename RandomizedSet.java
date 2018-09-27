package com.leetcode;

import java.util.*;

public class RandomizedSet {

    private Map<Integer,Integer> map;
    private List<Integer> list;
    private int size;


    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        size = 0;
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
       if(map.containsKey(val))return false;
       map.put(val,size);
       list.add(val);
       size++;
       return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))return false;
        int index = map.remove(val);
        if(index == size - 1){
            list.remove(size - 1);
        }else{
            map.put(list.get(size-1),index);
            list.set(index,list.remove(size-1));
        }
        size--;
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(new Random().nextInt(list.size()));
    }
}
