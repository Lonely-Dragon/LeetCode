package com.sakura.algorithm.simple;
import	java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class TwoSum {

    public static void main(String[] args) {
//        int[] nums = {2,7,11,15};
        int target = 6;
//        int[] nums = {-3,4,3,90};
        int[] nums = {3,2,4};
        System.out.println(JSON.toJSONString(twoSum(nums, target)));
        
    }

    public static int[] twoSum(int[] nums, int target) {
        int control = 0;
        if (nums.length == 2) {
            int[] res = {0,1};
            return res;
        }
        TreeMap<Integer,Integer> tree = new TreeMap();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            tree.put(num,i);
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(tree.containsKey(target-num)   ){
                int ind2 = tree.get(target-num);
                if(ind2!=i){
                    int[] nu = {i, ind2};
                    return nu;
                }
            }
        }
        int[] nu = {0, 0};
        return nu;
    }
    
}
