package com.dingjh.redis.core;

import java.util.List;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

public class DefaultListOperations extends BaseOperations implements ListOperations{
	/* (非 Javadoc)
	 * <p>Title: rpush</p>
	 * <p>Description: </p>
	 * @param key
	 * @param string
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#rpush(java.lang.String, java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long rpush(final String key,final String string) {
		return new Executor<Long>(){
			@Override
			 Long execute() {
			  return redisDataSource.getClient().rpush(key, string);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: rpush</p>
	 * <p>Description: </p>
	 * @param key
	 * @param string
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#rpush(byte[], byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long rpush(final byte[] key,final byte[] string) {
		return new Executor<Long>(){
			@Override
			 Long execute() {
			  return redisDataSource.getClient().rpush(key, string);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * <p>Title: lpush</p>
	 * <p>Description: </p>
	 * @param key
	 * @param string
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lpush(java.lang.String, java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long lpush(final String key,final String string) {
		return new Executor<Long>(){
			@Override
			 Long execute() {
			  return redisDataSource.getClient().lpush(key, string);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: lpush</p>
	 * <p>Description: </p>
	 * @param key
	 * @param string
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lpush(byte[], byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long lpush(final byte[] key,final byte[] string) {
		return new Executor<Long>(){
			@Override
			 Long execute() {
			  return redisDataSource.getClient().lpush(key, string);
			}
		}.callback();
	}

	
	/* (非 Javadoc)
	 * <p>Title: llen</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#llen(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long llen(final String key) {
		return new Executor<Long>(){
			@Override
			 Long execute() {
			  return redisDataSource.getClient().llen(key);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: llen</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#llen(byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long llen(final byte[] key) {
		return new Executor<Long>(){
			@Override
			 Long execute() {
			  return redisDataSource.getClient().llen(key);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * <p>Title: lrange</p>
	 * <p>Description: </p>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lrange(java.lang.String, long, long)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public List<String> lrange(final String key,final long start,final long end) {
		return new Executor<List<String>>(){
			@Override
			List<String> execute() {
			  return redisDataSource.getClient().lrange(key, start, end);
			}
		}.callback();
	}
	

	/* (非 Javadoc)
	 * <p>Title: lrange</p>
	 * <p>Description: </p>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lrange(byte[], int, int)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public List<byte[]> lrange(final byte[] key,final int start,final int end) {
		return new Executor<List<byte[]>>(){
			@Override
			List<byte[]> execute() {
			  return redisDataSource.getClient().lrange(key, start, end);
			}
		}.callback();
	}


	/* (非 Javadoc)
	 * <p>Title: ltrim</p>
	 * <p>Description: </p>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#ltrim(java.lang.String, long, long)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public String ltrim(final String key,final long start,final long end) {
		return new Executor<String>(){
			@Override
			String execute() {
			  return redisDataSource.getClient().ltrim(key, start, end);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: ltrim</p>
	 * <p>Description: </p>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#ltrim(byte[], int, int)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public String ltrim(final byte[] key,final int start,final int end) {
		return new Executor<String>(){
			@Override
			String execute() {
			  return redisDataSource.getClient().ltrim(key, start, end);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * <p>Title: lindex</p>
	 * <p>Description: </p>
	 * @param key
	 * @param index
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lindex(java.lang.String, long)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public String lindex(final String key,final long index) {
		return new Executor<String>(){
			@Override
			String execute() {
			  return redisDataSource.getClient().lindex(key, index);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: lindex</p>
	 * <p>Description: </p>
	 * @param key
	 * @param index
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lindex(byte[], int)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public byte[] lindex(final byte[] key,final int index) {
		return new Executor<byte[]>(){
			@Override
			byte[] execute() {
			  return redisDataSource.getClient().lindex(key, index);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * <p>Title: lset</p>
	 * <p>Description: </p>
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lset(java.lang.String, long, java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public String lset(final String key,final long index,final String value) {
		return new Executor<String>(){
			@Override
			String execute() {
			  return redisDataSource.getClient().lset(key, index, value);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: lset</p>
	 * <p>Description: </p>
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lset(byte[], int, byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public String lset(final byte[] key,final int index,final byte[] value) {
		return new Executor<String>(){
			@Override
			String execute() {
			  return redisDataSource.getClient().lset(key, index, value);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * <p>Title: lrem</p>
	 * <p>Description: </p>
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lrem(java.lang.String, long, java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long lrem(final String key,final long count,final String value) {
		return new Executor<Long>(){
			@Override
			Long execute() {
			  return redisDataSource.getClient().lrem(key, count, value);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: lrem</p>
	 * <p>Description: </p>
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lrem(byte[], int, byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long lrem(final byte[] key,final int count,final byte[] value) {
		return new Executor<Long>(){
			@Override
			Long execute() {
			  return redisDataSource.getClient().lrem(key, count, value);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * <p>Title: lpop</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lpop(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public String lpop(final String key) {
	    return new Executor<String>(){
			@Override
			String execute() {
			  return redisDataSource.getClient().lpop(key);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: lpop</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#lpop(byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public byte[] lpop(final byte[] key) {
		return new Executor<byte[]>(){
			@Override
			byte[] execute() {
			  return redisDataSource.getClient().lpop(key);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * <p>Title: rpop</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#rpop(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public String rpop(final String key) {
		return new Executor<String>(){
			@Override
			String execute() {
			  return redisDataSource.getClient().rpop(key);
			}
		}.callback();
	}


	/* (非 Javadoc)
	 * <p>Title: rpop</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#rpop(byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public byte[] rpop(final byte[] key) {
		return new Executor<byte[]>(){
			@Override
			byte[] execute() {
			  return redisDataSource.getClient().rpop(key);
			}
		}.callback();
	}
	
	/* (非 Javadoc)
	 * <p>Title: linsert</p>
	 * <p>Description: </p>
	 * @param key
	 * @param where
	 * @param pivot
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#linsert(java.lang.String, redis.clients.jedis.BinaryClient.LIST_POSITION, java.lang.String, java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
    @Override
	public Long linsert(final String key,final LIST_POSITION where,final String pivot,final String value) {
		return new Executor<Long>(){
			@Override
			Long execute() {
			  return redisDataSource.getClient().linsert(key, where, pivot, value);
			}
		}.callback();
    }
    
	/* (非 Javadoc)
	 * <p>Title: linsert</p>
	 * <p>Description: </p>
	 * @param key
	 * @param where
	 * @param pivot
	 * @param value
	 * @return
	 * @see com.dingjh.redis.core.ListOperations#linsert(byte[], redis.clients.jedis.BinaryClient.LIST_POSITION, byte[], byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:18:30
	 */
	@Override
	public Long linsert(final byte[] key,final LIST_POSITION where,final byte[] pivot,final byte[] value) {
		return new Executor<Long>(){
			@Override
			Long execute() {
			  return redisDataSource.getClient().linsert(key, where, pivot, value);
			}
		}.callback();
	}

}
