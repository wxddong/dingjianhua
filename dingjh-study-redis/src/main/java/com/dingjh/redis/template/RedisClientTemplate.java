package com.dingjh.redis.template;

import com.dingjh.redis.core.DefaultHashOperations;
import com.dingjh.redis.core.DefaultKeysOperations;
import com.dingjh.redis.core.DefaultListOperations;
import com.dingjh.redis.core.DefaultSetOperations;
import com.dingjh.redis.core.DefaultSortedSetOperations;
import com.dingjh.redis.core.DefaultStringOperations;
import com.dingjh.redis.core.DefaultSystemOperations;
import com.dingjh.redis.core.HashOperations;
import com.dingjh.redis.core.KeysOperations;
import com.dingjh.redis.core.ListOperations;
import com.dingjh.redis.core.SetOperations;
import com.dingjh.redis.core.SortedSetOperations;
import com.dingjh.redis.core.StringOperations;
import com.dingjh.redis.core.SystemOperations;

public class RedisClientTemplate { 
    public HashOperations opsForHash(){
        return new DefaultHashOperations();
    }
    
    public KeysOperations opsForKey(){
        return new DefaultKeysOperations();
    }
    public SystemOperations opsForSys(){
        return new DefaultSystemOperations();
    }

    public ListOperations opsForList(){
        return new DefaultListOperations();
    }

    public StringOperations opsForString(){
        return new DefaultStringOperations();
    }
    
    public SetOperations opsForSet(){
         return new DefaultSetOperations();
    }
    
    public SortedSetOperations opsForSortedSet(){
        return new DefaultSortedSetOperations();
    }

}
