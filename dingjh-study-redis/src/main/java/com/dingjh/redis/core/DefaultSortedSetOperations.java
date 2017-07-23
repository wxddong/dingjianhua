package com.dingjh.redis.core;

import java.util.Set;
import redis.clients.jedis.Tuple;

public class DefaultSortedSetOperations extends BaseOperations implements SortedSetOperations {
	/*
	 * (非 Javadoc) <p>Title: zcount</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zcount(byte[], double,
	 * double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zcount(final byte[] key, final double min, final double max) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zcount(key, min, max);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrangeWithScores(byte[],
	 * int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrangeWithScores(final byte[] key, final int start, final int end) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrangeWithScores(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrevrangeWithScores(byte[],
	 * int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrevrangeWithScores(final byte[] key, final int start, final int end) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrevrangeWithScores(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrange</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrevrange(byte[], int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<byte[]> zrevrange(final byte[] key, final int start, final int end) {
		return new Executor<Set<byte[]>>() {
			@Override
			Set<byte[]> execute() {
				return redisDataSource.getClient().zrevrange(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrank</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrevrank(byte[], byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zrevrank(final byte[] key, final byte[] member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zrevrank(key, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zadd</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param score
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zadd(java.lang.String,
	 * double, java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zadd(final String key, final double score, final String member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zadd(key, score, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrange</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrange(java.lang.String,
	 * int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<String> zrange(final String key, final int start, final int end) {
		return new Executor<Set<String>>() {
			@Override
			Set<String> execute() {
				return redisDataSource.getClient().zrange(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrem</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrem(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zrem(final String key, final String member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zrem(key, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zincrby</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param score
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zincrby(java.lang.String,
	 * double, java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Double zincrby(final String key, final double score, final String member) {
		return new Executor<Double>() {
			@Override
			Double execute() {
				return redisDataSource.getClient().zincrby(key, score, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrank</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrank(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zrank(final String key, final String member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zrank(key, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrank</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrank(byte[], byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zrank(final byte[] key, final byte[] member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zrank(key, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrank</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrevrank(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zrevrank(final String key, final String member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zrevrank(key, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrange</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrevrange(java.lang.String,
	 * int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<String> zrevrange(final String key, final int start, final int end) {
		return new Executor<Set<String>>() {
			@Override
			Set<String> execute() {
				return redisDataSource.getClient().zrevrange(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrangeWithScores(java.lang.String
	 * , int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrangeWithScores(final String key, final int start, final int end) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrangeWithScores(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrevrangeWithScores(java.lang
	 * .String, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrevrangeWithScores(final String key, final int start, final int end) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrevrangeWithScores(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zcard</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zcard(java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zcard(final String key) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zcard(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zscore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zscore(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Double zscore(final String key, final String member) {
		return new Executor<Double>() {
			@Override
			Double execute() {
				return redisDataSource.getClient().zscore(key, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zcount</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zcount(java.lang.String,
	 * double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zcount(final String key, final double min, final double max) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zcount(key, min, max);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrangeByScore(java.lang.String,
	 * double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<String> zrangeByScore(final String key, final double min, final double max) {
		return new Executor<Set<String>>() {
			@Override
			Set<String> execute() {
				return redisDataSource.getClient().zrangeByScore(key, min, max);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param max
	 * 
	 * @param min
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrevrangeByScore(java.lang.String
	 * , double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<String> zrevrangeByScore(final String key,final double max,final double min) {
		return new Executor<Set<String>>() {
			@Override
			Set<String> execute() {
				return redisDataSource.getClient().zrevrangeByScore(key, max, min);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param max
	 * 
	 * @param min
	 * 
	 * @param offset
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrevrangeByScore(java.lang.String
	 * , double, double, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<String> zrevrangeByScore(final String key,final double max,final double min,final int offset,final int count) {
		return new Executor<Set<String>>() {
			@Override
			Set<String> execute() {
				return redisDataSource.getClient().zrevrangeByScore(key, max, min, offset, count);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @param offset
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrangeByScore(java.lang.String,
	 * double, double, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<String> zrangeByScore(final String key,final double min,final double max,final int offset,final int count) {
		return new Executor<Set<String>>() {
			@Override
			Set<String> execute() {
				return redisDataSource.getClient().zrangeByScore(key, min, max, offset, count);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeByScoreWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrangeByScoreWithScores(java
	 * .lang.String, double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key,final double min,final double max) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrangeByScoreWithScores(key, min, max);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeByScoreWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param max
	 * 
	 * @param min
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrevrangeByScoreWithScores(java
	 * .lang.String, double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key,final double max,final double min) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrevrangeByScoreWithScores(key, max, min);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeByScoreWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @param offset
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrangeByScoreWithScores(java
	 * .lang.String, double, double, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key,final double min,final double max,final int offset,final int count) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrangeByScoreWithScores(key, min, max, offset, count);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeByScoreWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param max
	 * 
	 * @param min
	 * 
	 * @param offset
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrevrangeByScoreWithScores(java
	 * .lang.String, double, double, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min,
			final int offset, final int count) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zremrangeByRank</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zremrangeByRank(java.lang.String
	 * , int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zremrangeByRank(final String key, final int start, final int end) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zremrangeByRank(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zremrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zremrangeByScore(java.lang.String
	 * , double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zremrangeByScore(final String key, final double start, final double end) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zremrangeByScore(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zadd</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param score
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zadd(byte[], double,
	 * byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zadd(final byte[] key, final double score, final byte[] member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zadd(key, score, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zincrby</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param score
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zincrby(byte[], double,
	 * byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Double zincrby(final byte[] key, final double score, final byte[] member) {
		return new Executor<Double>() {
			@Override
			Double execute() {
				return redisDataSource.getClient().zincrby(key, score, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zcard</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zcard(byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zcard(final byte[] key) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zcard(key);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zscore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param member
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zscore(byte[], byte[])
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Double zscore(final byte[] key, final byte[] member) {
		return new Executor<Double>() {
			@Override
			Double execute() {
				return redisDataSource.getClient().zscore(key, member);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zremrangeByRank</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zremrangeByRank(byte[],
	 * int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zremrangeByRank(final byte[] key, final int start, final int end) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zremrangeByRank(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zremrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param start
	 * 
	 * @param end
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zremrangeByScore(byte[],
	 * double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Long zremrangeByScore(final byte[] key, final double start, final double end) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return redisDataSource.getClient().zremrangeByScore(key, start, end);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrangeByScore(byte[],
	 * double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max) {
		return new Executor<Set<byte[]>>() {
			@Override
			Set<byte[]> execute() {
				return redisDataSource.getClient().zrangeByScore(key, min, max);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @param offset
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrangeByScore(byte[],
	 * double, double, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count) {
		return new Executor<Set<byte[]>>() {
			@Override
			Set<byte[]> execute() {
				return redisDataSource.getClient().zrangeByScore(key, min, max, offset, count);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeByScoreWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrangeByScoreWithScores(byte[],
	 * double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrangeByScoreWithScores(key, min, max);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrangeByScoreWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @param offset
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrangeByScoreWithScores(byte[],
	 * double, double, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrangeByScoreWithScores(key, min, max, offset, count);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param max
	 * 
	 * @param min
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrevrangeByScore(byte[],
	 * double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<byte[]> zrevrangeByScore(final byte[] key, final double max, final double min) {
		return new Executor<Set<byte[]>>() {
			@Override
			Set<byte[]> execute() {
				return redisDataSource.getClient().zrevrangeByScore(key, max, min);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeByScore</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param max
	 * 
	 * @param min
	 * 
	 * @param offset
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see com.dingjh.redis.core.SortedSetOperations#zrevrangeByScore(byte[],
	 * double, double, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<byte[]> zrevrangeByScore(final byte[] key, final double max, final double min, final int offset,
			final int count) {
		return new Executor<Set<byte[]>>() {
			@Override
			Set<byte[]> execute() {
				return redisDataSource.getClient().zrevrangeByScore(key, max, min, offset, count);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeByScoreWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param max
	 * 
	 * @param min
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrevrangeByScoreWithScores(byte
	 * [], double, double)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double max, final double min) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrevrangeByScoreWithScores(key, max, min);
			}
		}.callback();
	}

	/*
	 * (非 Javadoc) <p>Title: zrevrangeByScoreWithScores</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param max
	 * 
	 * @param min
	 * 
	 * @param offset
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see
	 * com.dingjh.redis.core.SortedSetOperations#zrevrangeByScoreWithScores(byte
	 * [], double, double, int, int)
	 * 
	 * @author dingjianhua
	 * 
	 * @date 2014-12-5 下午7:57:24
	 */
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double max, final double min,
			final int offset, final int count) {
		return new Executor<Set<Tuple>>() {
			@Override
			Set<Tuple> execute() {
				return redisDataSource.getClient().zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		}.callback();
	}

}
