package com.dingjh.redis.connection;

import com.dingjh.redis.config.StudyShardedJedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisDataSourceImpl implements RedisDataSource {
	private static ThreadLocal<ShardedJedis> jedisLocal = new ThreadLocal<ShardedJedis>();
	private static ShardedJedisPool pool;
	static {
		pool = StudyShardedJedisPool.getShardedJedisPool();
	}

	@Override
	public ShardedJedis getClient() {
		ShardedJedis jedis = jedisLocal.get();
		if (jedis == null) {
			jedis = pool.getResource();
			jedisLocal.set(jedis);
		}
		return jedis;
	}

	/**
	 * 关闭连接
	 * @Title: returnResource
	 * @param broken
	 *            是否发生异常 void
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-4 下午7:53:50
	 */
	@Override
	public void returnResource(boolean broken) {
		ShardedJedis jedis = jedisLocal.get();
		if (jedis != null) {
			if (broken) {
				pool.returnBrokenResource(jedis);
			} else {
				pool.returnResource(jedis);
			}
			jedisLocal.set(null);
		}
	}

}
