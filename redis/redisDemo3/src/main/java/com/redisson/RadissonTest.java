package com.redisson;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.RedissonNode;
import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RadissonTest {

    private static final Logger logger = LoggerFactory.getLogger(RadissonTest.class);

    @Test
    public void mapsLocalCache2() {
        Config config = new Config();
        config.useSingleServer().setAddress("http://192.168.1.140:6379");
        config.useSingleServer().setDatabase(15);
        RedissonClient client = Redisson.create(config);

        LocalCachedMapOptions options = LocalCachedMapOptions.defaults().maxIdle(10 * 1000).timeToLive(10 * 1000);
        RLocalCachedMap<String, Integer> map = client.getLocalCachedMap("op", options);

        map.put("1", 1);
        map.put("2", 2);

        map.fastPut("3", 4);
        map.fastPut("4", 8);

        System.out.println("KEY BEFORE: " + map.get("op"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("KEY AFTER: " + map.get("op"));

        map.destroy();
    }

    // 刷新 redis
    @Test
    public void createInstance() {

        Vector<Object> objects = new Vector<>();
        int connections = 10;
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
//        c.setUseLinuxNativeEpoll(true);
//        c.useClusterServers()
        c.useSingleServer().setTimeout(10000000)
                .setAddress(host)
                .setConnectionPoolSize(connections).setConnectionMinimumIdleSize(connections)
                .setDatabase(15);
//        .addNodeAddress(host)
//        .setMasterConnectionPoolSize(connections).setMasterConnectionMinimumIdleSize(connections);
        c.setCodec(StringCodec.INSTANCE);

        RedissonClient r = Redisson.create(c);
        RKeys keys = r.getKeys();
        System.out.println(keys.getKeys());
        keys.flushdb();
    }

    @Test
    public void mapsItemChange2() {
        Config config = new Config();
        config.useSingleServer().setAddress("http://192.168.1.140:6379");
        RedissonClient client = Redisson.create(config);

        LocalCachedMapOptions options = LocalCachedMapOptions.defaults().maxIdle(10 * 1000).timeToLive(10 * 1000);
        RLocalCachedMap<String, Integer> map = client.getLocalCachedMap("myMap", options);
        Integer value = map.get("k1");
        System.out.println("Value Before: " + value);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        value = map.get("test");
        System.out.println("Value After: " + value);
    }

    // SortedSetExamples
    @Test
    public void SortedSetExamples() {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host);
        RedissonClient client = Redisson.create(c);

        RSortedSet<String> sortedSet = client.getSortedSet("mySortedSet");
        sortedSet.add("1");
        sortedSet.add("2");
        sortedSet.add("3");

        for (String string : sortedSet) {
            // iteration through bulk loaded values
        }

        String firstValue = sortedSet.first();
        String lastValue = sortedSet.last();

        boolean removedValue = sortedSet.remove("1");
        sortedSet.removeAll(Arrays.asList("1", "2", "3"));
        sortedSet.containsAll(Arrays.asList("4", "1", "0"));

        client.shutdown();
    }

    // SortedSetExamples
    // test 123,4934,321,value
    @Test
    public void BucketExamples() {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient client = Redisson.create(c);

        RBucket<String> bucket = client.getBucket("test");
        bucket.set("123");
        boolean isUpdated = bucket.compareAndSet("123", "4934");
        String prevObject = bucket.getAndSet("321");
        boolean isSet = bucket.trySet("901");
        long objectSize = bucket.size();

        // set with expiration
        bucket.set("value", 10, TimeUnit.SECONDS);
        boolean isNewSet = bucket.trySet("nextValue", 10, TimeUnit.SECONDS);

        client.shutdown();
    }

    // Lock 实现
    @Test
    public void LockExamples() throws InterruptedException {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient client = Redisson.create(c);

        RLock lock = client.getLock("lock");
        lock.lock(5, TimeUnit.SECONDS);

        Thread t = new Thread() {
            public void run() {
                RLock lock1 = client.getLock("lock");
                lock1.lock();
//                lock1.unlock();
            }

            ;
        };

        t.start();
        t.join();

        lock.unlock();

        client.shutdown();
    }

    // 分布式锁
    @Test
    public void testGetLock() throws Exception {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        RLock lock = redisson.getLock("TEST");
        try {
            lock.lock();
            logger.info("Request Thread - " + Thread.currentThread().getName() + " locked and begun...");
            Thread.sleep(5000); // 5 sec
            logger.info("Request Thread - " + Thread.currentThread().getName() + " ended successfully...");
        } catch (Exception ex) {
            logger.error("Error occurred");
        } finally {
            lock.unlock();
            logger.info("Request Thread - " + Thread.currentThread().getName() + " unlocked...");
        }
    }

    // HyperLogLogExamples 实现,不重复元素的个数
    @Test
    public void HyperLogLogExamples() {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        RHyperLogLog<String> hyperLogLog = redisson.getHyperLogLog("hyperLogLog");
        hyperLogLog.add("1");
        hyperLogLog.add("2");
        hyperLogLog.add("3");
        hyperLogLog.addAll(Arrays.asList("10", "20", "30"));

        RHyperLogLog<String> hyperLogLog1 = redisson.getHyperLogLog("hyperLogLog1");
        hyperLogLog1.add("4");
        hyperLogLog1.add("5");
        hyperLogLog1.add("6");

        RHyperLogLog<String> hyperLogLog2 = redisson.getHyperLogLog("hyperLogLog2");
        hyperLogLog1.add("4");
        hyperLogLog1.add("5");
        hyperLogLog1.add("6");

        hyperLogLog2.mergeWith(hyperLogLog1.getName());
        hyperLogLog2.countWith(hyperLogLog1.getName());

        redisson.shutdown();
    }

    // TopicExamples 实现,
    @Test
    public void TopicExamples() throws InterruptedException {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        CountDownLatch latch = new CountDownLatch(1);

        RTopic<String> topic = redisson.getTopic("topic2");
        topic.addListener(new MessageListener<String>() {
            @Override
            public void onMessage(CharSequence channel, String msg) {
                latch.countDown();
            }
        });

        topic.publish("msg");
        latch.await();

        redisson.shutdown();
    }

    // AtomicLongExamples 实现,
    @Test
    public void AtomicLongExamples() throws InterruptedException {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        RAtomicLong atomicLong = redisson.getAtomicLong("myLong");
        atomicLong.getAndDecrement();
        atomicLong.getAndIncrement();

        atomicLong.addAndGet(10L);
        atomicLong.compareAndSet(29, 412);

        atomicLong.decrementAndGet();
        atomicLong.incrementAndGet();

        atomicLong.getAndAdd(302);
        atomicLong.getAndDecrement();
        atomicLong.getAndIncrement();

        redisson.shutdown();
    }

    // AtomicDoubleExamples 实现,
    @Test
    public void AtomicDoubleExamples() throws InterruptedException {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        RAtomicDouble atomicDouble = redisson.getAtomicDouble("myDouble");
        atomicDouble.getAndDecrement();
        atomicDouble.getAndIncrement();

        atomicDouble.addAndGet(10.323);
        atomicDouble.compareAndSet(29.4, 412.91);

        atomicDouble.decrementAndGet();
        atomicDouble.incrementAndGet();

        atomicDouble.getAndAdd(302.00);
        atomicDouble.getAndDecrement();
        atomicDouble.getAndIncrement();

        redisson.shutdown();
    }

    // BinaryStreamExamples 实现,
    @Test
    public void BinaryStreamExamples() throws InterruptedException, IOException {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        RBinaryStream stream = redisson.getBinaryStream("myStream");

        byte[] values = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        stream.trySet(values);
        stream.set(values);

        InputStream is = stream.getInputStream();
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = is.read()) != -1) {
            sb.append((char) ch);
        }
        String str = sb.toString();

        OutputStream os = stream.getOutputStream();
        for (int i = 0; i < values.length; i++) {
            byte c1 = values[i];
            os.write(c1);
        }

        redisson.shutdown();
    }

    // GeoExamples 实现,
    @Test
    public void GeoExamples() throws InterruptedException, IOException {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        RGeo<String> geo = redisson.getGeo("myGeo");
        GeoEntry entry = new GeoEntry(13.361389, 38.115556, "Palermo");
        geo.add(entry);
        geo.add(15.087269, 37.502669, "Catania");

        Double dist = geo.dist("Palermo", "Catania", GeoUnit.METERS);

        Map<String, GeoPosition> pos = geo.pos("Palermo", "Catania");

        List<String> cities = geo.radius(15, 37, 200, GeoUnit.KILOMETERS);
        List<String> allNearCities = geo.radius("Palermo", 10, GeoUnit.KILOMETERS);

        Map<String, Double> citiesWithDistance = geo.radiusWithDistance(15, 37, 200, GeoUnit.KILOMETERS);
        Map<String, Double> allNearCitiesDistance = geo.radiusWithDistance("Palermo", 10, GeoUnit.KILOMETERS);

        Map<String, GeoPosition> citiesWithPosition = geo.radiusWithPosition(15, 37, 200, GeoUnit.KILOMETERS);
        Map<String, GeoPosition> allNearCitiesPosition = geo.radiusWithPosition("Palermo", 10, GeoUnit.KILOMETERS);

        redisson.shutdown();
    }


    // ExecutorServiceExamples 实现,
    @Test
    public void ExecutorServiceExamples() throws InterruptedException, IOException {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        RedissonNodeConfig nodeConfig = new RedissonNodeConfig(c);
        nodeConfig.setExecutorServiceWorkers(Collections.singletonMap("myExecutor", 1));
        RedissonNode node = RedissonNode.create(nodeConfig);
        node.start();

        RExecutorService e = redisson.getExecutorService("myExecutor");

        // 执行任务
//        e.execute(new RunnableTask());
//        e.submit(new CallableTask());

        e.shutdown();
        node.shutdown();
    }

    // SchedulerServiceExamples 实现,
    @Test
    public void SchedulerServiceExamples() throws InterruptedException, IOException {
        String host = "http://192.168.1.140:6379";
        Config c = new Config();
        c.useSingleServer()
                .setTimeout(10000000)
                .setAddress(host).setDatabase(15);
        RedissonClient redisson = Redisson.create(c);

        RedissonNodeConfig nodeConfig = new RedissonNodeConfig();
        nodeConfig.setExecutorServiceWorkers(Collections.singletonMap("myExecutor", 5));
        RedissonNode node = RedissonNode.create(nodeConfig);
        node.start();

        RScheduledExecutorService e = redisson.getExecutorService("myExecutor");
//        e.schedule(new RunnableTask(), 10, TimeUnit.SECONDS);
//        e.schedule(new CallableTask(), 4, TimeUnit.MINUTES);
//
//        e.schedule(new RunnableTask(), CronSchedule.of("10 0/5 * * * ?"));
//        e.schedule(new RunnableTask(), CronSchedule.dailyAtHourAndMinute(10, 5));
//        e.schedule(new RunnableTask(), CronSchedule.weeklyOnDayAndHourAndMinute(12, 4, Calendar.MONDAY, Calendar.FRIDAY));

        e.shutdown();
        node.shutdown();
    }


}