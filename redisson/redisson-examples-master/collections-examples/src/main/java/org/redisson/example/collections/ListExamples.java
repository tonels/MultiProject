/**
 * list 操作
 */
package org.redisson.example.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

public class ListExamples {

    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        
        RList<String> list = redisson.getList("myList");
        list.add("1");
        list.add("2");
        list.add("3");
        
        list.contains("1");
        
        String valueAtIndex = list.get(3);
        
        for (String string : list) {
            // iteration through bulk loaded values
        }
        
        boolean removedValue = list.remove("1");
        list.removeAll(Arrays.asList("1", "2", "3"));
        list.containsAll(Arrays.asList("4", "1", "0"));
        
        List<String> secondList = new ArrayList<>();
        secondList.add("4");
        secondList.add("5");
        list.addAll(secondList);

        // fetch all objects
        List<String> allValues = list.readAll();

        list.addAfter("3", "7");
        list.addBefore("4", "6");
        
        // use fast* methods when previous value is not required
        list.fastSet(1, "6");
        list.fastRemove(3);
        
        redisson.shutdown();
    }
    
}
