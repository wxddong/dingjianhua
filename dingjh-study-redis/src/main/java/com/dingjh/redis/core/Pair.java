package com.dingjh.redis.core;
/**
 * 键值对
 * @ClassName: Pair
 * @Description: TODO()
 * @param <K>
 * @param <V>
 * @author dingjianhua
 * @date 2014-12-6 上午2:28:01
 */
public class Pair<K, V> {  
    private K key;  
    private V value;  
    public Pair(K key, V value) {  
        this.key = key;  
        this.value = value;  
    }  

    public K getKey() {  
        return key;  
    }  

    public void setKey(K key) {  
        this.key = key;  
    }  

    public V getValue() {  
        return value;  
    }  

    public void setValue(V value) {  
        this.value = value;  
    }  
}  

