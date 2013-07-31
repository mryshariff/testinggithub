package com.api.example.cacheexample.util;

import com.api.example.cacheexample.cache.CacheGroup;
import com.google.common.base.Predicate;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio Charif <mcharif at groupon.com>
 */
public class Filter implements Predicate {
    private final Set<Parameter> parameters;
    
    public Filter(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean apply(Object businessObj) {
        for (Parameter param: parameters) {
            try {
                Object bussinesObjValue = new PropertyDescriptor(param.getName(), businessObj.getClass()).getReadMethod().invoke(businessObj);
                if (!bussinesObjValue.getClass().equals(param.getType()) || !bussinesObjValue.equals(param.getValue())) {
                    return false;
                }
            } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(CacheGroup.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }
}
