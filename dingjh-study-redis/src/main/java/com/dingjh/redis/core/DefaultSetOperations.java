package com.dingjh.redis.core;

import java.util.List;
import java.util.Set;

public class DefaultSetOperations extends BaseOperations implements SetOperations {

	public Long sadd(final String key, final String member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return this.redisDataSource.getClient().sadd(key, member);
			}
		}.callback();
	}

	public Long sadd(final byte[] key, final byte[] member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return this.redisDataSource.getClient().sadd(key, member);
			}

		}.callback();
	}

	/**
	 * SMEMBERS key 返回集合 key 中的所有成员。<br/>
	 * 不存在的 key 被视为空集合。<br/>
	 * # key 不存在或集合为空<br/>
	 * redis> EXISTS not_exists_key<br/>
	 * (integer) 0<br/>
	 * redis> SMEMBERS not_exists_key<br/>
	 * (empty list or set)<br/>
	 * # 非空集合<br/>
	 * redis> SADD language Ruby Python Clojure<br/>
	 * (integer) 3<br/>
	 * redis> SMEMBERS language<br/>
	 * 1) "Python"<br/>
	 * 2) "Ruby"<br/>
	 * 3) "Clojure"
	 * 
	 * @param key
	 * @return Set<String> 集合中的所有成员。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:26:55
	 */
	public Set<String> smembers(final String key) {
		return new Executor<Set<String>>() {
			@Override
			Set<String> execute() {
				return this.redisDataSource.getClient().smembers(key);
			}
		}.callback();
	}

	public Boolean sismember(final byte[] key, final byte[] member) {
		return new Executor<Boolean>() {
			@Override
			Boolean execute() {
				return this.redisDataSource.getClient().sismember(key, member);
			}
		}.callback();
	}

	public Set<byte[]> smembers(final byte[] key) {
		return new Executor<Set<byte[]>>() {
			@Override
			Set<byte[]> execute() {
				return this.redisDataSource.getClient().smembers(key);
			}
		}.callback();
	}

	/**
	 * SREM key member [member ...]<br/>
	 * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。<br/>
	 * 当 key 不是集合类型，返回一个错误。<br/>
	 * # 测试数据<br/>
	 * redis> SMEMBERS languages<br/>
	 * 1) "c"<br/>
	 * 2) "lisp"<br/>
	 * 3) "python"<br/>
	 * 4) "ruby"<br/>
	 * # 移除单个元素<br/>
	 * redis> SREM languages ruby<br/>
	 * (integer) 1<br/>
	 * # 移除不存在元素<br/>
	 * redis> SREM languages non-exists-language<br/>
	 * (integer) 0<br/>
	 * # 移除多个元素<br/>
	 * redis> SREM languages lisp python c<br/>
	 * (integer) 3<br/>
	 * redis> SMEMBERS languages<br/>
	 * (empty list or set)
	 * 
	 * @param key
	 * @param member
	 * @return Long 被成功移除的元素的数量，不包括被忽略的元素。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:28:51
	 */
	public Long srem(final String key,final String member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return this.redisDataSource.getClient().srem(key, member);
			}
		}.callback();
	}

	public Long srem(final byte[] key,final byte[] member) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return this.redisDataSource.getClient().srem(key, member);
			}
		}.callback();
	}

	/**
	 * SPOP key<br/>
	 * 移除并返回集合中的一个随机元素。<br/>
	 * 如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用 SRANDMEMBER 命令。<br/>
	 * 被移除的随机元素。<br/>
	 * 当 key 不存在或 key 是空集时，返回 nil 。<br/>
	 * redis> SMEMBERS db<br/>
	 * 1) "MySQL"<br/>
	 * 2) "MongoDB"<br/>
	 * 3) "Redis"<br/>
	 * redis> SPOP db<br/>
	 * "Redis"<br/>
	 * redis> SMEMBERS db<br/>
	 * 1) "MySQL"<br/>
	 * 2) "MongoDB"<br/>
	 * redis> SPOP db<br/>
	 * "MySQL"<br/>
	 * redis> SMEMBERS db<br/>
	 * 1) "MongoDB"
	 * 
	 * @param key
	 * @return String
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:31:38
	 */
	public String spop(final String key) {
		return new Executor<String>() {
			@Override
			String execute() {
				return this.redisDataSource.getClient().spop(key);
			}
		}.callback();
	}

	public byte[] spop(final byte[] key) {
		return new Executor<byte[]>() {
			@Override
			byte[] execute() {
				return this.redisDataSource.getClient().spop(key);
			}
		}.callback();
	}

	/**
	 * SCARD key<br/>
	 * 返回集合 key 的基数(集合中元素的数量)。<br/>
	 * redis> SADD tool pc printer phone<br/>
	 * (integer) 3<br/>
	 * redis> SCARD tool # 非空集合<br/>
	 * (integer) 3<br/>
	 * redis> DEL tool<br/>
	 * (integer) 1<br/>
	 * redis> SCARD tool # 空集合<br/>
	 * (integer) 0<br/>
	 * 
	 * @param key
	 * @return Long 集合的基数。当 key 不存在时，返回 0 。
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:33:21
	 */
	public Long scard(final String key) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return this.redisDataSource.getClient().scard(key);
			}
		}.callback();
	}

	public Long scard(final byte[] key) {
		return new Executor<Long>() {
			@Override
			Long execute() {
				return this.redisDataSource.getClient().scard(key);
			}
		}.callback();
	}

	/**
	 * SRANDMEMBER key [count]<br/>
	 * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。<br/>
	 * 从 Redis 2.6 版本开始， SRANDMEMBER 命令接受可选的 count 参数：<br/>
	 * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count
	 * 大于等于集合基数，那么返回整个集合。<br/>
	 * 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。<br/>
	 * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。<br/>
	 * # 添加元素<br/>
	 * redis> SADD fruit apple banana cherry<br/>
	 * (integer) 3<br/>
	 * # 只给定 key 参数，返回一个随机元素<br/>
	 * redis> SRANDMEMBER fruit<br/>
	 * "cherry"<br/>
	 * redis> SRANDMEMBER fruit<br/>
	 * "apple"<br/>
	 * # 给定 3 为 count 参数，返回 3 个随机元素<br/>
	 * # 每个随机元素都不相同<br/>
	 * redis> SRANDMEMBER fruit 3<br/>
	 * 1) "apple"<br/>
	 * 2) "banana"<br/>
	 * 3) "cherry"<br/>
	 * # 给定 -3 为 count 参数，返回 3 个随机元素<br/>
	 * # 元素可能会重复出现多次<br/>
	 * redis> SRANDMEMBER fruit -3<br/>
	 * 1) "banana"<br/>
	 * 2) "cherry"<br/>
	 * 3) "apple"<br/>
	 * redis> SRANDMEMBER fruit -3<br/>
	 * 1) "apple"<br/>
	 * 2) "apple"<br/>
	 * 3) "cherry"<br/>
	 * # 如果 count 是整数，且大于等于集合基数，那么返回整个集合<br/>
	 * redis> SRANDMEMBER fruit 10<br/>
	 * 1) "apple"<br/>
	 * 2) "banana"<br/>
	 * 3) "cherry"<br/>
	 * # 如果 count 是负数，且 count 的绝对值大于集合的基数<br/>
	 * # 那么返回的数组的长度为 count 的绝对值<br/>
	 * redis> SRANDMEMBER fruit -10<br/>
	 * 1) "banana"<br/>
	 * 2) "apple"<br/>
	 * 3) "banana"<br/>
	 * 4) "cherry"<br/>
	 * 5) "apple"<br/>
	 * 6) "apple"<br/>
	 * 7) "cherry"<br/>
	 * 8) "apple"<br/>
	 * 9) "apple"<br/>
	 * 10) "banana"<br/>
	 * # SRANDMEMBER 并不会修改集合内容<br/>
	 * redis> SMEMBERS fruit<br/>
	 * 1) "apple"<br/>
	 * 2) "cherry"<br/>
	 * 3) "banana"<br/>
	 * # 集合为空时返回 nil 或者空数组<br/>
	 * redis> SRANDMEMBER not-exists<br/>
	 * (nil)<br/>
	 * redis> SRANDMEMBER not-eixsts 10<br/>
	 * (empty list or set)<br/>
	 * 
	 * @param key
	 * @return String 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。如果提供了 count
	 *         参数，那么返回一个数组；如果集合为空，返回空数组。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:35:03
	 */
	public String srandmember(final String key){
		return new Executor<String>() {
			@Override
			String execute() {
				return this.redisDataSource.getClient().srandmember(key);
			}
		}.callback();
	}

	/**
	 * SRANDMEMBER key [count]<br/>
	 * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。<br/>
	 * 从 Redis 2.6 版本开始， SRANDMEMBER 命令接受可选的 count 参数：<br/>
	 * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count
	 * 大于等于集合基数，那么返回整个集合。<br/>
	 * 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。<br/>
	 * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。<br/>
	 * # 添加元素<br/>
	 * redis> SADD fruit apple banana cherry<br/>
	 * (integer) 3<br/>
	 * # 只给定 key 参数，返回一个随机元素<br/>
	 * redis> SRANDMEMBER fruit<br/>
	 * "cherry"<br/>
	 * redis> SRANDMEMBER fruit<br/>
	 * "apple"<br/>
	 * # 给定 3 为 count 参数，返回 3 个随机元素<br/>
	 * # 每个随机元素都不相同<br/>
	 * redis> SRANDMEMBER fruit 3<br/>
	 * 1) "apple"<br/>
	 * 2) "banana"<br/>
	 * 3) "cherry"<br/>
	 * # 给定 -3 为 count 参数，返回 3 个随机元素<br/>
	 * # 元素可能会重复出现多次<br/>
	 * redis> SRANDMEMBER fruit -3<br/>
	 * 1) "banana"<br/>
	 * 2) "cherry"<br/>
	 * 3) "apple"<br/>
	 * redis> SRANDMEMBER fruit -3<br/>
	 * 1) "apple"<br/>
	 * 2) "apple"<br/>
	 * 3) "cherry"<br/>
	 * # 如果 count 是整数，且大于等于集合基数，那么返回整个集合<br/>
	 * redis> SRANDMEMBER fruit 10<br/>
	 * 1) "apple"<br/>
	 * 2) "banana"<br/>
	 * 3) "cherry"<br/>
	 * # 如果 count 是负数，且 count 的绝对值大于集合的基数<br/>
	 * # 那么返回的数组的长度为 count 的绝对值<br/>
	 * redis> SRANDMEMBER fruit -10<br/>
	 * 1) "banana"<br/>
	 * 2) "apple"<br/>
	 * 3) "banana"<br/>
	 * 4) "cherry"<br/>
	 * 5) "apple"<br/>
	 * 6) "apple"<br/>
	 * 7) "cherry"<br/>
	 * 8) "apple"<br/>
	 * 9) "apple"<br/>
	 * 10) "banana"<br/>
	 * # SRANDMEMBER 并不会修改集合内容<br/>
	 * redis> SMEMBERS fruit<br/>
	 * 1) "apple"<br/>
	 * 2) "cherry"<br/>
	 * 3) "banana"<br/>
	 * # 集合为空时返回 nil 或者空数组<br/>
	 * redis> SRANDMEMBER not-exists<br/>
	 * (nil)<br/>
	 * redis> SRANDMEMBER not-eixsts 10<br/>
	 * (empty list or set)<br/>
	 * 
	 * @param key
	 * @param count
	 * @return List<String> 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。如果提供了 count
	 *         参数，那么返回一个数组；如果集合为空，返回空数组。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:43:45
	 */
	public List<String> srandmember(final String key,final int count) {
		return new Executor<List<String>>() {
			@Override
			List<String> execute() {
				return this.redisDataSource.getClient().srandmember(key, count);
			}
		}.callback();
	}

	public byte[] srandmember(final byte[] key) {
		return new Executor<byte[]>() {
			@Override
			byte[] execute() {
				return this.redisDataSource.getClient().srandmember(key);
			}
		}.callback();
	}

}
