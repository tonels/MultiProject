package org.redisson.example.objects;

import org.redisson.Redisson;
import org.redisson.api.*;

import java.util.concurrent.ExecutionException;

public class BatchExamples {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RBatch batch = redisson.createBatch(BatchOptions.defaults());

        // hash hash : test1:1:2
        batch.getMap("test1").fastPutAsync("1", "2");
        batch.getMap("test2").fastPutAsync("2", "3");
        batch.getMap("test3").putAsync("2", "5");
        RFuture<Long> future = batch.getAtomicLong("counter").incrementAndGetAsync();
        batch.getAtomicLong("counter").incrementAndGetAsync();

        // result could be acquired through RFuture object returned by batched method
        // or 
        // through result list by corresponding index
        future.whenComplete((res, exception) -> {
            // ...
        });

        BatchResult<?> res = batch.execute();
        Long counter = (Long) res.getResponses().get(3);

        future.get().equals(counter);

        redisson.shutdown();
    }

}
