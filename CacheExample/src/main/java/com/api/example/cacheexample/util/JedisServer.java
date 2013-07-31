package com.api.example.cacheexample.util;

import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.pool.impl.GenericObjectPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 *
 * @author Mauricio Charif <mcharif at groupon.com>
 */
public class JedisServer {
    private static List<JedisShardInfo> shards;
    private static ShardedJedisPool pool;
    
    static {
        shards = Lists.newArrayList();
        shards.add(new JedisShardInfo("localhost", 6379));
        shards.add(new JedisShardInfo("localhost", 6380));
        cleanCache();
        pool = new ShardedJedisPool(new GenericObjectPool.Config(), shards);
    }
    
    public static ShardedJedis getResource() {
        return pool.getResource();
    }
    
    public static void returnResource(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }
    
    public static void destroyPool() {
        pool.destroy();
    }
    
    private static void cleanCache() {
        Jedis j = new Jedis(shards.get(0));
        j.connect();
        j.flushAll();
        j.disconnect();
        j = new Jedis(shards.get(1));
        j.connect();
        j.flushAll();
        j.disconnect();
    }
    
    private JedisServer() {
    }
}
