package com.api.example.cacheexample;

import com.api.example.cacheexample.cache.CacheGroup;
import com.api.example.cacheexample.entities.Deal;
import com.api.example.cacheexample.util.Parameter;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;


public class App  {
    
    public static void main(String[] args) throws Exception {
        CacheGroup cacheGroup = new CacheGroup();
        
        Set<Parameter> parameters = Sets.newTreeSet();
        parameters.add(new Parameter("countryCode", String.class, "CL"));
        parameters.add(new Parameter("channel", String.class, "shopping"));
        List<Deal> group = cacheGroup.getGroup(parameters);
        System.out.println("First request: " + group);
        
        Set<Parameter> parameters2 = Sets.newTreeSet();    
        parameters2.add(new Parameter("channel", String.class, "shopping"));
        parameters2.add(new Parameter("countryCode", String.class, "CL"));
        List<Deal> group2 = cacheGroup.getGroup(parameters2);
        System.out.println("Second request: " + group2);
        
        Set<Parameter> parameters3 = Sets.newTreeSet();   
        parameters3.add(new Parameter("channel", String.class, "local"));
        parameters3.add(new Parameter("countryCode", String.class, "MX"));
        List<Deal> group3 = cacheGroup.getGroup(parameters3);
        System.out.println("Third request: " + group3);
        
        Set<Parameter> parameters4 = Sets.newTreeSet();  
        parameters4.add(new Parameter("channel", String.class, "shopping"));
        parameters4.add(new Parameter("countryCode", String.class, "CL"));
        List<Deal> group4 = cacheGroup.getGroup(parameters4);
        System.out.println("Fourth request: " + group4);
        
        Set<Parameter> parameters5 = Sets.newTreeSet();  
        parameters5.add(new Parameter("countryCode", String.class, "MX"));
        parameters5.add(new Parameter("channel", String.class, "local"));
        List<Deal> group5 = cacheGroup.getGroup(parameters5);
        System.out.println("Fifth request: " + group5);
        
        cacheGroup.destroyJedisConnectionPool();
    }           
    
}
