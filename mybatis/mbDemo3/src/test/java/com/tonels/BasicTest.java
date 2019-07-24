package com.tonels;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.rmi.MarshalledObject;
import java.util.*;
import java.util.stream.Collectors;

public class BasicTest {
    public static void main(String[] args) {

        List<Integer> listLength = getListLength(1, 13, 3);
        System.out.println(listLength);

        List<Integer> parSum = getParStart(1, 13, 4);
        System.out.println(parSum);

        List<Integer> parMax = getParMax(1, 13, 4);
        System.out.println(parMax);

        Map<Integer, List<Integer>> map = get(1, 13, 3, 4);
        System.out.println(map);


    }

    public static List<Integer> getListLength(int start,int end,int step){
        int i = 0;
        int temp = 0;
        List<Integer> list = Lists.newArrayList();
        do {
            i += 1;
            temp = start + (i-1)*step;
            if (temp <= end){
                list.add(temp);
            }
        }while (temp <=end);
        return list ;
    }

    // 获取每个分区起始值
    public static List<Integer> getParStart(int start, int end, int partitionCount){
        BigDecimal startDe = new BigDecimal(start);
        BigDecimal endDe = new BigDecimal(end);
        BigDecimal partitionCountDe = new BigDecimal(partitionCount);

        BigDecimal count = endDe.subtract(startDe).add(new BigDecimal(1));
        int i = count.divide(partitionCountDe).setScale(0, BigDecimal.ROUND_UP).intValue();

        // 每个分区起始值,list
        List<Integer> list = Lists.newArrayList();
        for (int j = 0; j <=i; j++) {
            Integer i1 = Integer.valueOf(start + partitionCount * j);
            if (i1 <= Integer.valueOf(end)){
                list.add(i1);
            }
        }
        return list;
    }
    // 获取每个分区极限值
    public static List<Integer> getParMax(int start, int end, int partitionCount){

        List<Integer> parSum = getParStart(start, end, partitionCount);

        List<Integer> collect = parSum.stream().map(e -> (e + 2)).collect(Collectors.toList());
        return collect;
    }



    public static Map<Integer,List<Integer>> get(int start,int end,int step,int partitionCount){

        List<Integer> oriList = getListLength(start, end, step);
        List<Integer> parStart = getParStart(start, end, partitionCount);

        Map<Integer,List<Integer>> map = Maps.newHashMap();

        for (int i = 0; i < parStart.size(); i++) {
            List<Integer> list = Lists.newArrayList();
            for (int j = 0; j < oriList.size(); j++) {
                if (oriList.get(j) < (parStart.get(i) + partitionCount)){
                    list.add(oriList.get(j));
                }
            }
            map.put(i,list);
        }

        Collection<List<Integer>> values = map.values();
        for (List<Integer> value : values) {

        }
        return map;
    }

}
