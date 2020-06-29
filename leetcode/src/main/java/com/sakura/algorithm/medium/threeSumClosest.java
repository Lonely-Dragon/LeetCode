package com.sakura.algorithm.medium;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

public class threeSumClosest {

    public static void main(String[] args) {
//        int[] num = {2,2,-2,-2,6};
//        int[] num = {0,2,1,-3};
//        int[] num = {1, 1, 1, 0};
//        int[] num = {-1, 2, 1, -4};
        int[] num = {82,-16,-60,-48,32,-30,0,-89,70,-46,-82,-58,66,41,-96,-55,-49,-87,-33,-65,9,14,81,-11,80,-93,79,-63,-61,-16,22,-31,75,12,17,-6,37,-2,-89,-66,3,-95,-74,58,-95,22,11,-20,-36,60,-75,46,-52,-61,-28,7,-50,-45,93,-91,-43,35,-99,-39,53,-54,-98,-4,13,-90,23,-4,-65,29,85,-76,-64,81,32,-97,51,12,-82,-31,81,-2,-83,-9,89,-37,-23,-66,-91,-15,-98,-74,94,30,-2,-70,13,19,-77,-42,33,-70,25,77,47,-70,-70,60,-63,-4,83,13,-78,-23,28,-86,-11,-16,-57,-84,51,-48,-63,-15,29,56,-25,73,-69,23,28,90,96,41,65,-22,-43,-68,-77,31,69,-84,23,-63,-18,20,-93,-17,-48,-73,14,-95,98,-64,-12,-45,14,7,51,-61,89,-48,-23,2,-56,84,-2,27,74,-39,-18,-65,79,-36,-76,-30,44,78,-76,37,88,0,32,55,-51,23,-9,68,26,15,66,66,-56,-42,56,28,33,-32,-23,-36,-12,-76,-12,42,12,40,69,54,82,-22,-7,46,-46
        };
        int target = 270;
        System.out.println(threeSumClosest(num, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        int ori = target;
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        int min = 9999;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] += Math.abs(min);
        }
        int size = nums.length;
        target += Math.abs(min) * 3;
//        System.out.println(target);
//        System.out.println(JSON.toJSONString(nums));
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp1 = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int tmp2 = tmp1 - nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int tmp3 = nums[k];
                    set.add(tmp2-tmp3);
                }
            }
        }
        list.addAll(set);
        Comparator<Integer> comparator = new Comparator<Integer> () {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer a = Math.abs(o1);
                Integer b = Math.abs(o2);
                return a.compareTo(b);
            }
        };
//        System.out.println(JSON.toJSONString(list));
        int res = ori;
        if (ori >= 0) {
            Collections.sort(list,comparator);
            System.out.println(JSON.toJSONString(list));
            for (Integer integer : list) {
                res = ori - integer;
                return res;
            }
        } else {
            
            Collections.sort(list,comparator);
//            System.out.println(JSON.toJSONString(list));
//            Collections.reverse(list);
//            System.out.println(JSON.toJSONString(list));
            for (int i = 0; i <nums.length; i++) {
                int integer = list.get(i);
                    res = target - Math.abs(min)*3 - integer;
                    return res;
            }
        }
        return res;
    }

    /**
     * 分开2正2负错开计算，容量不够大，会出现考虑不全的情况出现
     * [1,1,-1,-1,3]
     * 3
     */
    public static int threeSumClosest2(int[] nums, int target) {
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        List<Integer> zheng = new ArrayList<>();
        List<Integer> fu = new ArrayList<>();
        int[] distance = {9999, 9999, 9999, 9999};
        int[] tar = {0, 0, 0, 0};
        for (int num : nums) {
            int tmp = num - target;
            if (tmp >= 0){
                zheng.add(num);
            }
            if (tmp < 0){
                fu.add(num);
            }
            if (tmp >= 0) {
                for (int i = 0; i < 2; i++) {
                    int i1 = distance[i];
                    if (i1 > tmp) {
                        if (distance[0] < distance[1]) {
                            tar[1] = num;
                            distance[1] = tmp;
                            break;
                        } else {
                            tar[0] = num;
                            distance[0] = tmp;
                            break;
                        }
                    }
                }
            } else {
                for (int i = 2; i < 4; i++) {
                    int i1 = distance[i];
                    if (Math.abs(i1) > Math.abs(tmp)) {
                        if (distance[2] < distance[3]) {
                            tar[3] = num;
                            distance[3] = tmp;
                            break;
                        } else {
                            tar[2] = num;
                            distance[2] = tmp;
                            break;
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int i : distance) {
            sum += i;
        }
        double pj = sum * 1.0 / nums.length;
        System.out.println(sum);
        System.out.println(pj);
        System.out.println(JSONObject.toJSONString(distance));
        System.out.println(JSONObject.toJSONString(tar));
        if (zheng.size() == 0) {
            int size = fu.size();
            Collections.sort(fu);
            return fu.get(size - 1) + fu.get(size - 2) + fu.get(size - 3);
        }
        if (fu.size() == 0) {
            Collections.sort(zheng);
            return zheng.get(0) + zheng.get(1) + zheng.get(2);
        }
        int res = target;
        int dis = 9999;
        for (int i = 0; i < tar.length; i++) {
            int a = 0;
            for (int j = 0; j < tar.length; j++) {
                if (i != j){
                    a += tar[j];
                }
            }
            if (dis > Math.abs(target - a)) {
                res = a;
                dis = Math.abs(target - a);
            }
        }
        return res;
//        return (distance[0] + distance[1] + Math.abs(distance[2]) - distance[1] - Math.abs(distance[2]) - Math.abs(distance[3])) < 0 ? tar[0] + tar[1] + tar[2] : tar[1] + tar[2] + tar[3];
    }

}
