package com.api.example.cacheexample.util;

/**
 *
 * @author francocaramanico
 */
public class Parameter implements Comparable<Parameter> {
    
    private String name;
    private Class type;
    private Object value;

    public Parameter(String name, Class type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Parameter{" + "name=" + name + ", type=" + type + ", value=" + value + '}';
    }

    @Override
    public int compareTo(Parameter o) {
        return getName().compareToIgnoreCase(o.getName());
    }
    
}
