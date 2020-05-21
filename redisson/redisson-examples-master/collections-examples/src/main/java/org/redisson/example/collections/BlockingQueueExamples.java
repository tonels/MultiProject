/**
 * 队列操作
 * 第一个队列已删除
 */
package org.redisson.example.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;

public class BlockingQueueExamples {

    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RBlockingQueue<String> queue = redisson.getBlockingQueue("myQueue");
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        
        queue.contains("1");
        queue.peek();
        queue.poll();
        queue.element();
        
        for (String string : queue) {
            // iteration through bulk loaded values
        }
        
        boolean removedValue = queue.remove("1");
        queue.removeAll(Arrays.asList("1", "2", "3"));
        queue.containsAll(Arrays.asList("4", "1", "0"));
        
        List<String> secondList = new ArrayList<>();
        secondList.add("4");
        secondList.add("5");
        queue.addAll(secondList);

        RQueue<String> secondQueue = redisson.getQueue("mySecondQueue");
        
        queue.pollLastAndOfferFirstTo(secondQueue.getName());
        
        
        
        Thread t = new Thread(() -> {
            try {
                String element = queue.poll(10, TimeUnit.SECONDS);
                
                String secondElement = queue.take();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        t.start();
        t.join();
        
        redisson.shutdown();
    }
    
}
