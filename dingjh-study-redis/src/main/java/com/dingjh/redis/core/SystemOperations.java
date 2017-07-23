package com.dingjh.redis.core;

import java.util.Collection;
import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPipeline;

public interface SystemOperations {

	public abstract List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline);

	public abstract Jedis getShard(byte[] key);

	public abstract Jedis getShard(String key);

	public abstract JedisShardInfo getShardInfo(byte[] key);

	public abstract JedisShardInfo getShardInfo(String key);

	public abstract String getKeyTag(String key);

	public abstract Collection<JedisShardInfo> getAllShardInfo();

	public abstract Collection<Jedis> getAllShards();

}