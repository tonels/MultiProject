/**
 * todo 异常
 * ERROR org.redisson.client.handler.CommandsQueue -
 * Exception occured. Channel: [id: 0x5115123f, L:/127.0.0.1:55930 - R:127.0.0.1/127.0.0.1:6379]
 */
package org.redisson.example.collections;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

public class BucketExamples {

    public static void main(String[] args) {
        RedissonClient redisson = Redisson.create();
        
        RBucket<String> bucket = redisson.getBucket("test");
        bucket.set("123");
        boolean isUpdated = bucket.compareAndSet("123", "4934");
        String prevObject = bucket.getAndSet("321");
        boolean isSet = bucket.trySet("901");
        long objectSize = bucket.size();
        
        // set with expiration
        bucket.set("value", 10, TimeUnit.SECONDS);
        boolean isNewSet = bucket.trySet("nextValue", 10, TimeUnit.SECONDS);
        
        redisson.shutdown();
    }
    
}
