package com.dingjh.redis.connection;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
	/*
	 * 获取连接
	 */
    public ShardedJedis getClient();
    
    public void returnResource(boolean broken);
}
