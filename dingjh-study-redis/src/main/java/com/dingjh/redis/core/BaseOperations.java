package com.dingjh.redis.core;

import com.dingjh.redis.connection.RedisDataSource;
import com.dingjh.redis.connection.RedisDataSourceImpl;

public abstract class BaseOperations {
	protected RedisDataSource redisDataSource = new RedisDataSourceImpl();
}
