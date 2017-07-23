package com.dingjh.redis.core;

import java.util.Collection;
import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

public class DefaultSystemOperations extends BaseOperations implements SystemOperations{

	/* (非 Javadoc)
	 * @param shardedJedisPipeline
	 * @return
	 * @see com.dingjh.redis.core.SystemOperations#pipelined(redis.clients.jedis.ShardedJedisPipeline)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:00:01
	 */
	@Override
	public List<Object> pipelined(final ShardedJedisPipeline shardedJedisPipeline) {
		return new Executor<List<Object>>() {
			@Override
			List<Object> execute() {
				ShardedJedis shardedJedis = redisDataSource.getClient();
				if (shardedJedis == null) {
					return  null;
				}
				return shardedJedisPipeline.syncAndReturnAll();
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.SystemOperations#getShard(byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:00:01
	 */
	@Override
	public Jedis getShard(final byte[] key) {
		return new Executor<Jedis>() {
			@Override
			Jedis execute() {
				return redisDataSource.getClient().getShard(key);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.SystemOperations#getShard(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:00:01
	 */
	@Override
	public Jedis getShard(final String key) {
		return new Executor<Jedis>() {
			@Override
			Jedis execute() {
				return redisDataSource.getClient().getShard(key);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.SystemOperations#getShardInfo(byte[])
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:00:01
	 */
	@Override
	public JedisShardInfo getShardInfo(final byte[] key) {
		return new Executor<JedisShardInfo>() {
			@Override
			JedisShardInfo execute() {
				return redisDataSource.getClient().getShardInfo(key);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.SystemOperations#getShardInfo(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:00:01
	 */
	@Override
	public JedisShardInfo getShardInfo(final String key) {
		return new Executor<JedisShardInfo>() {
			@Override
			JedisShardInfo execute() {
				return redisDataSource.getClient().getShardInfo(key);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * @param key
	 * @return
	 * @see com.dingjh.redis.core.SystemOperations#getKeyTag(java.lang.String)
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:00:01
	 */
	@Override
	public String getKeyTag(final String key) {
		return new Executor<String>() {
			@Override
			String execute() {
				return redisDataSource.getClient().getKeyTag(key);
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * @return
	 * @see com.dingjh.redis.core.SystemOperations#getAllShardInfo()
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:00:01
	 */
	@Override
	public Collection<JedisShardInfo> getAllShardInfo() {
		return new Executor<Collection<JedisShardInfo>>() {
			@Override
			Collection<JedisShardInfo> execute() {
				return redisDataSource.getClient().getAllShardInfo();
			}
		}.callback();
	}

	/* (非 Javadoc)
	 * @return
	 * @see com.dingjh.redis.core.SystemOperations#getAllShards()
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:00:01
	 */
	@Override
	public Collection<Jedis> getAllShards() {
		return new Executor<Collection<Jedis>>() {
			@Override
			Collection<Jedis> execute() {
				return redisDataSource.getClient().getAllShards();
			}
		}.callback();
	}
}
