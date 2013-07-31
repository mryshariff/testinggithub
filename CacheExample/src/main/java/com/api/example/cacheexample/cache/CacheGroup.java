package com.api.example.cacheexample.cache;

import com.api.example.cacheexample.entities.Deal;
import com.api.example.cacheexample.util.Filter;
import com.api.example.cacheexample.util.JedisServer;
import com.api.example.cacheexample.util.Parameter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.ShardedJedis;

/**
 *
 * @author francocaramanico
 */
public class CacheGroup {
    private Gson gson = new Gson();
    private Type dealListType = new TypeToken<ArrayList<Deal>>() {}.getType();

    public CacheGroup() {
        this.generateCache();
    }
    
    private void generateCache() {
        List<Deal> originalDeals = Lists.newArrayList();
        originalDeals.add(new Deal(1L, "Test deal 1", "MX", "shopping"));
        originalDeals.add(new Deal(2L, "Test deal 2", "CL", "local"));
        originalDeals.add(new Deal(3L, "Test deal 3", "CL", "travel"));
        originalDeals.add(new Deal(4L, "Test deal 4", "MX", "local"));
        originalDeals.add(new Deal(5L, "Test deal 5", "CL", "shopping"));
        originalDeals.add(new Deal(6L, "Test deal 6", "PE", "shopping"));
        originalDeals.add(new Deal(7L, "Test deal 7", "CL", "shopping"));
        originalDeals.add(new Deal(8L, "Test deal 8", "MX", "local"));
        originalDeals.add(new Deal(9L, "Test deal 9", "MX", "shopping"));
        
        ShardedJedis jedis = JedisServer.getResource();       
        jedis.set("deals", gson.toJson(originalDeals));
        JedisServer.returnResource(jedis);
    }
    
    public void destroyJedisConnectionPool() {
        JedisServer.destroyPool();
    }

    public List<Deal> getGroup(Set<Parameter> parameters) throws Exception {
        ShardedJedis jedis = JedisServer.getResource();
        String currentKey = generateKey(parameters);
        
        if (jedis.exists(generateKey(parameters))) {
            System.out.println("The group already exist. Return data.");
            String dealsJson = jedis.get(currentKey);           
            List<Deal> deals = new Gson().fromJson(dealsJson, dealListType);
            JedisServer.returnResource(jedis);
            return deals;
        }
        System.out.println("The group don't exist in cache.");
        String allDealsJson = jedis.get("deals");
        List<Deal> deals = new Gson().fromJson(allDealsJson, dealListType);

        List<Deal> group = Lists.newArrayList(Collections2.filter(deals, new Filter(parameters)));
        jedis.set(generateKey(parameters), gson.toJson(group));
        JedisServer.returnResource(jedis);
        return group;
    }

    private String generateKey(Set<Parameter> parameters) {
        return parameters.toString();
    }
}
