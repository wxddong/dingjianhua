package com.dingjh.redis.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedisPipeline;

public class DefaultHashOperations extends BaseOperations implements HashOperations {
    /** 
     * 返回哈希表 key 中给定域 field 的值。 如果哈希表 key 存在，同时设置这个 key 的生存时间 
     * @param key key 
     * @param field 域 
     * @param expire 生命周期，单位为秒 
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。 
     */  
    public String hashGet(final String key, final String field, final int expire) {  
        return new Executor<String>() {  
            @Override  
            String execute() {  
                Pipeline pipeline =redisDataSource.getClient().getShard(key).pipelined();  
                Response<String> result = pipeline.hget(key, field);  
                pipeline.expire(key, expire);  
                pipeline.sync();  
                return result.get();  
            }  
        }.callback();  
    }

    /** 
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。同时设置这个 key 的生存时间 
     * @param key key 
     * @param hash field-value的map 
     * @param expire 生命周期，单位为秒 
     * @return 如果命令执行成功，返回 OK 。当 key 不是哈希表(hash)类型时，返回一个错误。 
     */  
    public String hashMultipleSet(final String key, final Map<String, String> hash, final int expire) {  
        return new Executor<String>() {  
            @Override  
            String execute() {  
                Pipeline pipeline = redisDataSource.getClient().getShard(key).pipelined();  
                Response<String> result = pipeline.hmset(key, hash);  
                pipeline.expire(key, expire);  
                pipeline.sync();  
                return result.get();  
            }  
        }.callback();  
    }  
	
    /** 
     * 批量的{@link #hashGetAll(String)} 
     * @param keys key的数组 
     * @return 执行结果的集合 
     */  
    public List<Map<String, String>> batchHashGetAll(final String... keys) {  
        return new Executor<List<Map<String, String>>>() {  
			@Override
			List<Map<String, String>> execute() {
				ShardedJedisPipeline pipeline = redisDataSource.getClient().pipelined();
				List<Map<String, String>> result = new ArrayList<Map<String, String>>(keys.length);
				List<Response<Map<String, String>>> responses = new ArrayList<Response<Map<String, String>>>(
						keys.length);
				for (String key : keys) {
					responses.add(pipeline.hgetAll(key));
				}
				pipeline.sync();
				for (Response<Map<String, String>> resp : responses) {
					result.add(resp.get());
				}
				return result;
			}  
        }.callback();  
    }
    
    /** 
     * 批量的{@link #hashMultipleSet(String, Map)}，在管道中执行 
     * @param pairs 多个hash的多个field 
     * @return 操作状态的集合 
     */  
    public List<Object> batchHashMultipleSet(final List<Pair<String, Map<String, String>>> pairs) {  
        return new Executor<List<Object>>() {  
            @Override  
            List<Object> execute() {  
                ShardedJedisPipeline pipeline = redisDataSource.getClient().pipelined();  
                for (Pair<String, Map<String, String>> pair : pairs) {  
                    pipeline.hmset(pair.getKey(), pair.getValue());  
                }  
                return pipeline.syncAndReturnAll();  
            }  
        }.callback();  
    }  
  
    /** 
     * 批量的{@link #hashMultipleSet(String, Map)}，在管道中执行 
     * @param data Map<String, Map<String, String>>格式的数据 
     * @return 操作状态的集合 
     */  
    public List<Object> batchHashMultipleSet(final Map<String, Map<String, String>> data) {  
        return new Executor<List<Object>>() {  
            @Override  
            List<Object> execute() {  
                ShardedJedisPipeline pipeline =redisDataSource.getClient().pipelined();  
                for (Map.Entry<String, Map<String, String>> iter : data.entrySet()) {  
                    pipeline.hmset(iter.getKey(), iter.getValue());  
                }  
                return pipeline.syncAndReturnAll();  
            }  
        }.callback();  
    }
    
    /** 
     * 返回哈希表 key 中，一个或多个给定域的值。如果给定的域不存在于哈希表，那么返回一个 nil 值。 
     * 同时设置这个 key 的生存时间 
     * @param key key 
     * @param fields field的数组 
     * @param expire 生命周期，单位为秒 
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。 
     */  
    public List<String> hashMultipleGet(final String key, final int expire, final String... fields) {  
        return new Executor<List<String>>() {  
            @Override  
            List<String> execute() {  
                Pipeline pipeline = redisDataSource.getClient().getShard(key).pipelined();  
                Response<List<String>> result = pipeline.hmget(key, fields);  
                pipeline.expire(key, expire);  
                pipeline.sync();  
                return result.get();  
            }  
        }.callback();  
    }  



	/*
	 * (非 Javadoc)
	 * <p>Title: hexists</p>
	 * <p>Description: </p>
	 * @param key
	 * @param field
	 * @return
	 * @see com.dingjh.redis.core.HashOperations#hexists(java.lang.String, java.lang.String)
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:47:22
	 
	 */
	@Override
	public Boolean hexists(final String key, final String field) {
		return new Executor<Boolean>() {
			@Override
			Boolean execute() {
				return redisDataSource.getClient().hexists(key, field);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hexists</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hexists(byte[], byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Boolean hexists(final byte[] key, final byte[] field) {
		return new Executor<Boolean>() {
			@Override
			Boolean execute() {
				return redisDataSource.getClient().hexists(key, field);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hdel</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hdel(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hdel(final String key, final String field) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hdel(key, field);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hdel</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hdel(byte[], byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hdel(final byte[] key, final byte[] field) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hdel(key, field);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hlen</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hlen(java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hlen(final String key) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hlen(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hlen</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hlen(byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hlen(final byte[] key) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hlen(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hkeys</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hkeys(java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Set<String> hkeys(final String key) {
		return new Executor<Set<String>>() {
			@Override
			Set<String> execute() {
				return redisDataSource.getClient().hkeys(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hkeys</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hkeys(byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Set<byte[]> hkeys(final byte[] key) {
		return new Executor<Set<byte[]>>() {
			@Override
			Set<byte[]> execute() {
				return redisDataSource.getClient().hkeys(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hvals</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hvals(java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public List<String> hvals(final String key) {
		return new Executor<List<String>>() {
			@Override
			List<String> execute() {
				return redisDataSource.getClient().hvals(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hvals</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hvals(byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Collection<byte[]> hvals(final byte[] key) {
		return new Executor<Collection<byte[]>>() {
			@Override
			Collection<byte[]> execute() {
				return redisDataSource.getClient().hvals(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hgetAll</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hgetAll(java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Map<String, String> hgetAll(final String key) {
		return new Executor<Map<String, String>>() {
			@Override
			Map<String, String> execute() {
				return redisDataSource.getClient().hgetAll(key);
			}
		}.callback();
	}
	
    /** 
     * 返回哈希表 key 中，所有的域和值。在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。 
     * 同时设置这个 key 的生存时间 
     * @param key key 
     * @param expire 生命周期，单位为秒 
     * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。 
     */  
    public Map<String, String> hashGetAll(final String key, final int expire) {  
        return new Executor<Map<String, String>>() {  
            @Override  
            Map<String, String> execute() {  
                Pipeline pipeline = redisDataSource.getClient().getShard(key).pipelined();  
                Response<Map<String, String>> result = pipeline.hgetAll(key);  
                pipeline.expire(key, expire);  
                pipeline.sync();  
                return result.get();  
            }  
        }.callback();  
    }  
    
    
	@Override
	public Map<String, String> hashGetAll(final String key) {
        return new Executor<Map<String, String>>() {
			@Override
            Map<String, String> execute() {  
                Pipeline pipeline = redisDataSource.getClient().getShard(key).pipelined();  
                Response<Map<String, String>> result = pipeline.hgetAll(key);  
                pipeline.sync();  
                return result.get();  
            }  
        }.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hgetAll</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hgetAll(byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Map<byte[], byte[]> hgetAll(final byte[] key) {
		return new Executor<Map<byte[], byte[]>>() {
			@Override
			Map<byte[], byte[]> execute() {
				return redisDataSource.getClient().hgetAll(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hset</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hset(java.lang.String,
	 * java.lang.String, java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hset(final String key, final String field, final String value) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hset(key, field, value);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hget</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hget(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public String hget(final String key, final String field) {
		return new Executor<String>() {
			@Override
			String execute() {
				return redisDataSource.getClient().hget(key, field);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hsetnx</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hsetnx(java.lang.String,
	 * java.lang.String, java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hsetnx(final String key,final String field,final String value) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hsetnx(key, field, value);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hmset</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param hash
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hmset(java.lang.String,
	 * java.util.Map)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public String hmset(final String key,final Map<String, String> hash) {
		return new Executor<String>() {
			@Override
			String execute() {
				return redisDataSource.getClient().hmset(key, hash);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hmset</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param hash
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hmset(byte[], java.util.Map)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public String hmset(final byte[] key,final Map<byte[], byte[]> hash) {
		return new Executor<String>() {
			@Override
			String execute() {
				return redisDataSource.getClient().hmset(key, hash);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hmget</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param fields
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hmget(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public List<String> hmget(final String key,final String... fields) {
		return new Executor<List<String>>() {
			@Override
			List<String> execute() {
				return redisDataSource.getClient().hmget(key, fields);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hmget</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param fields
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hmget(byte[], byte)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public List<byte[]> hmget(final byte[] key,final byte[]... fields) {
		return new Executor<List<byte[]>>() {
			@Override
			List<byte[]> execute() {
				return redisDataSource.getClient().hmget(key, fields);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hincrBy</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hincrBy(java.lang.String,
	 * java.lang.String, long)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hincrBy(final String key,final String field,final long value) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hincrBy(key, field,value);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hincrBy</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hincrBy(byte[], byte[], long)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hincrBy(final byte[] key,final byte[] field,final long value) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hincrBy(key,field,value);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: hset</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param field
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.HashOperations#hset(byte[], byte[], byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午4:36:42
	 */
	@Override
	public Long hset(final byte[] key,final byte[] field,final byte[] value) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hset(key, field, value);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: hget</p>
	 * <p>Description: </p>
	 * @param key
	 * @param field
	 * @return
	 * @see com.dingjh.redis.core.HashOperations#hget(byte[], byte[])
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:46:31
	 
	 */
	@Override
	public byte[] hget(final byte[] key,final byte[] field) {
		return new Executor<byte[]>() {
			@Override
			byte[] execute() {
				return redisDataSource.getClient().hget(key, field);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc)
	 * <p>Title: hsetnx</p>
	 * <p>Description: </p>
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.HashOperations#hsetnx(byte[], byte[], byte[])
	 * @author dingjianhua
	 * @date 2017年4月20日 上午9:46:26
	 
	 */
	@Override
	public Long hsetnx(final byte[] key,final byte[] field,final byte[] value) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().hsetnx(key, field, value);
			}
		}.callback();
	}
}
