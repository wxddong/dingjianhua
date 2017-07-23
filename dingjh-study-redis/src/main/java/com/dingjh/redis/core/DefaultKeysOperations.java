package com.dingjh.redis.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;

public class DefaultKeysOperations  extends BaseOperations implements KeysOperations{
    /** 
     * 删除模糊匹配的key 
     * @param likeKey 模糊匹配的key 
     * @return 删除成功的条数 
     */  
    public long delKeysLike(final String likeKey) {  
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                Collection<Jedis> jedisC = redisDataSource.getClient().getAllShards();
                Iterator<Jedis> iter = jedisC.iterator();  
                long count = 0;  
                while (iter.hasNext()) {  
                    Jedis jedis = iter.next();  
                    Set<String> keys = jedis.keys(likeKey + "*");  
                    count += jedis.del(keys.toArray(new String[keys.size()]));  
                }  
                return count;  
            }  
        }.callback();  
    }  
	
    /* (非 Javadoc)
	 * <p>Title: disconnect</p>
	 * <p>Description: </p>
	 * @see com.dingjh.redis.core.KeysOperations#disconnect()
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public void disconnect() {
        ShardedJedis shardedJedis = redisDataSource.getClient();
        shardedJedis.disconnect();
    }

    /* (非 Javadoc)
	 * <p>Title: type</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#type(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public String type(final String key) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().type(key);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * <p>Title: type</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#type(byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public String type(final byte[] key) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().type(key);  
            }  
        }.callback();  
    }


    /* (非 Javadoc)
	 * <p>Title: expire</p>
	 * <p>Description: </p>
	 * @param key
	 * @param seconds
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#expire(java.lang.String, int)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Long expire(final String key,final int seconds) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().expire(key, seconds);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * <p>Title: expire</p>
	 * <p>Description: </p>
	 * @param key
	 * @param seconds
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#expire(byte[], int)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Long expire(final byte[] key,final int seconds) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().expire(key, seconds);  
            }  
        }.callback();  
    }

    /* (非 Javadoc)
	 * <p>Title: expireAt</p>
	 * <p>Description: </p>
	 * @param key
	 * @param unixTime
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#expireAt(java.lang.String, long)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Long expireAt(final String key,final long unixTime) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().expireAt(key,unixTime);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * <p>Title: expireAt</p>
	 * <p>Description: </p>
	 * @param key
	 * @param unixTime
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#expireAt(byte[], long)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Long expireAt(final byte[] key,final long unixTime) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().expireAt(key,unixTime);  
            }  
        }.callback();  
    }

    
    /* (非 Javadoc)
	 * <p>Title: ttl</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#ttl(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Long ttl(final String key) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().ttl(key);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * <p>Title: ttl</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#ttl(byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Long ttl(final byte[] key) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().ttl(key);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * <p>Title: del</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#del(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Long del(final String key) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().del(key);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * <p>Title: exists</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#exists(byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Boolean exists(final byte[] key) {
        return new Executor<Boolean>() {  
            @Override  
            Boolean execute() {  
                return redisDataSource.getClient().exists(key);  
            }  
        }.callback();  
    }
    
    
    /* (非 Javadoc)
	 * <p>Title: exists</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#exists(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public Boolean exists(final String key) {
        return new Executor<Boolean>() {  
            @Override  
            Boolean execute() {  
                return redisDataSource.getClient().exists(key);  
            }  
        }.callback();  
    }
    
    public List<byte[]> sort(final byte[] key) {
        return new Executor<List<byte[]>>() {  
            @Override  
            List<byte[]> execute() {  
                return redisDataSource.getClient().sort(key);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * <p>Title: sort</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#sort(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public List<String> sort(final String key) {
        return new Executor<List<String>>() {  
            @Override  
            List<String> execute() {  
                return redisDataSource.getClient().sort(key);  
            }  
        }.callback();  
    }
    /* (非 Javadoc)
	 * <p>Title: sort</p>
	 * <p>Description: </p>
	 * @param key
	 * @param sortingParameters
	 * @return
	 * @see com.dingjh.redis.core.KeysOperations#sort(java.lang.String, redis.clients.jedis.SortingParams)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:18:56
	 */
    @Override
	public List<String> sort(final String key,final SortingParams sortingParameters) {
        return new Executor<List<String>>() {  
            @Override  
            List<String> execute() {  
                return redisDataSource.getClient().sort(key, sortingParameters);
            }  
        }.callback();  
    }
    
    public List<byte[]> sort(final byte[] key,final SortingParams sortingParameters) {
        return new Executor<List<byte[]>>() {  
            @Override  
            List<byte[]> execute() {  
                return redisDataSource.getClient().sort(key, sortingParameters);
            }  
        }.callback();  
    }
    
    /** 
     * 一个跨jvm的id生成器，利用了redis原子性操作的特点 
     * @param key id的key 
     * @return 返回生成的Id 
     */  
    public long makeId(final String key) {  
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                long id = redisDataSource.getClient().incr(key);  
                if ((id + 75807) >= Long.MAX_VALUE) {  
                    // 避免溢出，重置，getSet命令之前允许incr插队，75807就是预留的插队空间  
                	redisDataSource.getClient().getSet(key, "0");  
                }  
                return id;  
            }  
        }.callback();  
    }  


}
