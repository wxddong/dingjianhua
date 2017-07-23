package com.dingjh.redis.core;

import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedisPipeline;


public class DefaultStringOperations extends BaseOperations implements StringOperations {
    /** 
     * 批量的 {@link #setString(String, String)} 
     * @param pairs 键值对数组{数组第一个元素为key，第二个元素为value} 
     * @return 操作状态的集合 
     */  
    public List<Object> batchSetString(final List<Pair<String, String>> pairs) {  
        return new Executor<List<Object>>() {  
            @Override  
            List<Object> execute() {  
                ShardedJedisPipeline pipeline = redisDataSource.getClient().pipelined();  
                for (Pair<String, String> pair : pairs) {  
                    pipeline.set(pair.getKey(), pair.getValue());  
                }  
                return pipeline.syncAndReturnAll();  
            }  
        }.callback();  
    }  

    /** 
     * 批量的 {@link #getString(String)} 
     * @param keys key数组 
     * @return value的集合 
     */  
    public List<String> batchGetString(final String[] keys) {  
        return new Executor<List<String>>() {  
            @Override  
            List<String> execute() {  
                ShardedJedisPipeline pipeline = redisDataSource.getClient().pipelined();  
                List<String> result = new ArrayList<String>(keys.length);  
                List<Response<String>> responses = new ArrayList<Response<String>>(keys.length);  
                for (String key : keys) {  
                    responses.add(pipeline.get(key));  
                }  
                pipeline.sync();  
                for (Response<String> resp : responses) {  
                    result.add(resp.get());  
                }  
                return result;  
            }  
        }.callback();  
    }  
	/*
	 * (非 Javadoc)
	 * <p>Title: setnx</p>
	 * <p>Description: </p>
	 * @param key
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#setnx(byte[], byte[])
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:41:59
	 
	 */
	@Override
	public Long setnx(final byte[] key,final byte[] value) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().setnx(key, value);  
            }  
        }.callback();  
	}
	
	/*
	 * (非 Javadoc)
	 * <p>Title: setnx</p>
	 * <p>Description: </p>
	 * @param key
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#setnx(java.lang.String, java.lang.String)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:42:24
	 
	 */
	@Override
	public Long setnx(final String key,final String value) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().setnx(key, value);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: setex</p>
	 * <p>Description: </p>
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#setex(java.lang.String, int, java.lang.String)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:42:31
	 
	 */
	@Override
	public String setex(final String key,final int seconds,final String value) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().setex(key, seconds, value);  
            }  
        }.callback();  
	}


	/*
	 * (非 Javadoc)
	 * <p>Title: setex</p>
	 * <p>Description: </p>
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#setex(byte[], int, byte[])
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:41:21
	 
	 */
	@Override
	public String setex(final byte[] key,final int seconds,final byte[] value) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().setex(key, seconds, value);  
            }  
        }.callback();  
	}
	
    /*
     * (非 Javadoc)
     * <p>Title: decrBy</p>
     * <p>Description: </p>
     * @param key
     * @param integer
     * @return
     * @see com.dingjh.redis.core.StringOperations#decrBy(java.lang.String, long)
     * @author dingjianhua
     * @date 2017年4月20日 上午9:41:02
     
     */
	@Override
	public Long decrBy(final String key,final long integer) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().decrBy(key, integer);  
            }  
        }.callback();  
	}


	/*
	 * (非 Javadoc)
	 * <p>Title: decrBy</p>
	 * <p>Description: </p>
	 * @param key
	 * @param integer
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#decrBy(byte[], long)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:41:29
	 
	 */
	@Override
	public Long decrBy(final byte[] key,final long integer) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().decrBy(key, integer);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: decr</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#decr(java.lang.String)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:41:34
	 
	 */
	@Override
	public Long decr(final String key) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().decr(key);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: decr</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#decr(byte[])
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:41:40
	 
	 */
	@Override
	public Long decr(final byte[] key) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().decr(key);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: incrBy</p>
	 * <p>Description: </p>
	 * @param key
	 * @param integer
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#incrBy(byte[], long)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:42:47
	 
	 */
	@Override
	public Long incrBy(final byte[] key,final long integer) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().decr(key);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: incrBy</p>
	 * <p>Description: </p>
	 * @param key
	 * @param integer
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#incrBy(java.lang.String, long)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:42:53
	 
	 */
	@Override
	public Long incrBy(final String key,final long integer) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().incrBy(key, integer);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: incr</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#incr(java.lang.String)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:42:58
	 
	 */
	@Override
	public Long incr(final String key) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().incr(key);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: incr</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#incr(byte[])
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:43:03
	 
	 */
	@Override
	public Long incr(final byte[] key) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().incr(key);  
            }  
        }.callback();  
	}
	
	/*
	 * (非 Javadoc)
	 * <p>Title: append</p>
	 * <p>Description: </p>
	 * @param key
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#append(java.lang.String, java.lang.String)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:43:08
	 
	 */
	@Override
	public Long append(final String key,final String value) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().append(key, value);  
            }  
        }.callback();  
	}


	/*
	 * (非 Javadoc)
	 * <p>Title: append</p>
	 * <p>Description: </p>
	 * @param key
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#append(byte[], byte[])
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:43:12
	 
	 */
	@Override
	public Long append(final byte[] key,final byte[] value) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().append(key, value);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: substr</p>
	 * <p>Description: </p>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#substr(byte[], int, int)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:43:19
	 
	 */
	@Override
	public byte[] substr(final byte[] key,final int start,final int end) {
        return new Executor<byte[]>() {  
            @Override  
            byte[] execute() {  
                return redisDataSource.getClient().substr(key, start, end);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: setbit</p>
	 * <p>Description: </p>
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#setbit(java.lang.String, long, boolean)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:43:23
	 
	 */
	@Override
	public boolean setbit(final String key,final long offset,final boolean value) {
        return new Executor<Boolean>() {  
            @Override  
            Boolean execute() {  
                return redisDataSource.getClient().setbit(key, offset, value);  
            }  
        }.callback();  
	}
	
	/*
	 * (非 Javadoc)
	 * <p>Title: setrange</p>
	 * <p>Description: </p>
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#setrange(java.lang.String, long, java.lang.String)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:43:40
	 
	 */
	@Override
	public long setrange(final String key,final long offset,final String value) {
        return new Executor<Long>() {  
            @Override  
            Long execute() {  
                return redisDataSource.getClient().setrange(key, offset, value);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: getrange</p>
	 * <p>Description: </p>
	 * @param key
	 * @param startOffset
	 * @param endOffset
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#getrange(java.lang.String, long, long)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:43:45
	 
	 */
	@Override
	public String getrange(final String key,final long startOffset,final long endOffset) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().getrange(key, startOffset, endOffset);  
            }  
        }.callback();  
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: getSet</p>
	 * <p>Description: </p>
	 * @param key
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#getSet(java.lang.String, java.lang.String)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:43:50
	 
	 */
	@Override
	public String getSet(final String key,final String value) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().getSet(key, value);  
            }  
        }.callback();  
	}

    /*
     * (非 Javadoc)
     * <p>Title: getSet</p>
     * <p>Description: </p>
     * @param key
     * @param value
     * @return
     * @see com.dingjh.redis.core.StringOperations#getSet(byte[], byte[])
     * @author dingjianhua
     * @date 2017年4月20日 上午9:43:55
     
     */
    @Override
	public byte[] getSet(final byte[] key,final byte[] value) {
        return new Executor<byte[]>() {  
            @Override  
            byte[] execute() {  
                return redisDataSource.getClient().getSet(key, value);  
            }  
        }.callback();  
    }


	/*
	 * (非 Javadoc)
	 * <p>Title: substr</p>
	 * <p>Description: </p>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#substr(java.lang.String, int, int)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:44:00
	 
	 */
	@Override
	public String substr(final String key,final int start,final int end) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().substr(key, start, end);  
            }  
        }.callback();  
	}
	
	/*
	 * (非 Javadoc)
	 * <p>Title: getbit</p>
	 * <p>Description: </p>
	 * @param key
	 * @param offset
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#getbit(java.lang.String, long)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:44:06
	 
	 */
	@Override
	public boolean getbit(final String key,final long offset) {
        return new Executor<Boolean>() {  
            @Override  
            Boolean execute() {  
                return redisDataSource.getClient().getbit(key, offset);  
            }  
        }.callback();  
	}
    
    /* (非 Javadoc)
	 * @param key
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#set(java.lang.String, java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-6 上午2:04:24
	 */
    @Override
	public String set(final String key,final String value) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().set(key, value);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * @param key
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#set(byte[], byte[])
	 * @author dingjianhua
	 * @date 2014-12-6 上午2:04:24
	 */
    @Override
	public String set(final byte[] key,final byte[] value) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().set(key, value);  
            }  
        }.callback();  
    }

    /* (非 Javadoc)
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#get(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-6 上午2:04:24
	 */
    @Override
	public String get(final String key) {
        return new Executor<String>() {  
            @Override  
            String execute() {  
                return redisDataSource.getClient().get(key);  
            }  
        }.callback();  
    }
    
    /* (非 Javadoc)
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.StringOperations#get(byte[])
	 * @author dingjianhua
	 * @date 2014-12-6 上午2:04:24
	 */
    @Override
	public byte[] get(final byte[] key) {
        return new Executor<byte[]>() {  
            @Override  
            byte[] execute() {  
                return redisDataSource.getClient().get(key);  
            }  
        }.callback();  
    }


}
