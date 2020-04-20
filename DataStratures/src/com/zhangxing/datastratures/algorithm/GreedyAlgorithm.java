package com.zhangxing.datastratures.algorithm;

import java.util.*;

/**
 * @author zhangxing
 * @Description: 贪心算法解决集合覆盖代码实现
 * @date 2020/4/17 18:46
 * 遍历所有的广播电台，找到一个覆盖了最多未覆盖的地区的电台
 * 将这个电台加入到一个集合中，想办法把该电台所覆盖的地区在下次比较时去掉
 * 重复上述步骤直至覆盖全部地区
 */
public class GreedyAlgorithm {
    private static HashSet<String> allAreas;
    private static HashMap<String, HashSet> broadCasts;
    private static ArrayList<String> selectedBroadCasts = new ArrayList<String>();


    public static void main(String[] args) {
        initBroadCast();
        initAllAreas(broadCasts);
        System.out.println(allAreas);
        greedyAlgorithm();
    }

    private static void greedyAlgorithm() {
        //保证在一次遍历过程中能够覆盖最大未覆盖地区对应的电台的key
        String maxKey = null;
        HashSet<String> tempSet = new HashSet<String>();
        while (allAreas.size() != 0) {
            maxKey = null;
            for (String key : broadCasts.keySet()) {
                //每进行一次循环，tempSet需要clear
                tempSet.clear();
                //当前的key能覆盖的地区
                HashSet areas = broadCasts.get(key);
                tempSet.addAll(areas);
                //求交集。并将交集赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还要多。就要更新maxKey
                //本语句体现了贪婪的特性
                if (tempSet.size() > 0 &&
                        (maxKey==null || tempSet.size() > broadCasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selectedBroadCasts.add(maxKey);
                //将maxKey指向的广播电台所覆盖的地区从allAreas删去
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }
        System.out.println("得到的结果：" + selectedBroadCasts);

    }

    private static HashMap<String, HashSet> initBroadCast() {
        //创建广播电台
        broadCasts = new HashMap<String, HashSet>();
        HashSet<String> set1 = new HashSet<String>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        HashSet<String> set2 = new HashSet<String>();
        set2.add("北京");
        set2.add("广州");
        set2.add("深圳");

        HashSet<String> set3 = new HashSet<String>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        HashSet<String> set4 = new HashSet<String>();
        set4.add("上海");
        set4.add("天津");

        HashSet<String> set5 = new HashSet<String>();
        set5.add("杭州");
        set5.add("大连");

        broadCasts.put("K1", set1);
        broadCasts.put("K2", set2);
        broadCasts.put("K3", set3);
        broadCasts.put("K4", set4);
        broadCasts.put("K5", set5);
        return broadCasts;
    }

    private static void initAllAreas(HashMap<String, HashSet> broadCasts) {
        allAreas = new HashSet<String>();
        Iterator<Map.Entry<String, HashSet>> iterator = broadCasts.entrySet().iterator();
        while (iterator.hasNext()) {
            HashSet set = iterator.next().getValue();
            ArrayList list = new ArrayList<String>(set);
            for (int i = 0; i < list.size(); i++) {
                if (!allAreas.contains(list.get(i))) {
                    allAreas.add((String) list.get(i));
                }
            }
        }
    }
}
