/**
 * todo 这里运行异常
 * ERR Error running script (call to f_d404aba30301167784efa4539108cd4cc9b948c4):
 * @user_script:1: user_script:1: Capacity of queue redisson_bqs:{myQueue} has not been set .
 * channel: [id: 0xc6727083, L:/127.0.0.1:55265 - R:127.0.0.1/127.0.0.1:6379] command: (EVAL), params: [local value = redis.call('get', KEYS[1]); assert(value ~= false,
 * 'Capacity of queue ' .. KEYS[1] .. ..., 2, redisson_bqs:{myQueue}, myQueue, 1, PooledUnsafeDirectByteBuf(ridx: 0, widx: 3, cap: 256)
 */
package org.redisson.example.collections;

import org.redisson.Redisson;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RedissonClient;

public class BoundedBlockingQueueExamples {

    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RBoundedBlockingQueue<String> queue = redisson.getBoundedBlockingQueue("myQueue");
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        
        queue.trySetCapacity(5);
        
        Thread t = new Thread(() -> {
            try {
                String element = queue.take();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        t.start();
        
        queue.put("6");
        
        redisson.shutdown();
    }
    
}
