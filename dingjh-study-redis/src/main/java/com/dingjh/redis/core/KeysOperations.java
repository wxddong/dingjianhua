package com.dingjh.redis.core;

import java.util.List;
import redis.clients.jedis.SortingParams;

public interface KeysOperations {
	public  void disconnect();
    /** 
     * 删除模糊匹配的key 
     * @param likeKey 模糊匹配的key 
     * @return 删除成功的条数 
     */  
    public long delKeysLike(final String likeKey);
    /** 
     * 一个跨jvm的id生成器，利用了redis原子性操作的特点 
     * @param key id的key 
     * @return 返回生成的Id 
     */  
    public long makeId(final String key);

	/**
	 * 返回 key 所储存的值的类型。
	 * @param key
	 * @return 
	 * String  none (key不存在) string (字符串) list (列表) set (集合) zset (有序集) hash (哈希表)
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:39:10
	 */
	public  String type(final String key);

	/**
	 * 返回 key 所储存的值的类型。
	 * @Title: type
	 * @param key
	 * @return 
	 * String none (key不存在) string (字符串) list (列表) set (集合) zset (有序集) hash (哈希表)
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:50:05
	 */
	public  String type(final byte[] key);

	/**
	 * 在某段时间后实现<br/>
	 * EXPIRE key seconds<br/>
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。<br/>
	 * 在 Redis 中，带有生存时间的 key 被称为『易失的』(volatile)。<br/>
	 * 生存时间可以通过使用 DEL 命令来删除整个 key 来移除，或者被 SET 和 GETSET 命令覆写(overwrite)，这意味着，如果一个命令只是修改(alter)一个带生存时间的 key 的值而不是用一个新的 key 值来代替(replace)它的话，那么生存时间不会被改变。<br/>
	 * 比如说，对一个 key 执行 INCR 命令，对一个列表进行 LPUSH 命令，或者对一个哈希表执行 HSET 命令，这类操作都不会修改 key 本身的生存时间。<br/>
	 * 另一方面，如果使用 RENAME 对一个 key 进行改名，那么改名后的 key 的生存时间和改名前一样。<br/>
	 * RENAME 命令的另一种可能是，尝试将一个带生存时间的 key 改名成另一个带生存时间的 another_key ，这时旧的 another_key (以及它的生存时间)会被删除，然后旧的 key 会改名为 another_key ，因此，新的 another_key 的生存时间也和原本的 key 一样。<br/>
	 * 使用 PERSIST 命令可以在不删除 key 的情况下，移除 key 的生存时间，让 key 重新成为一个『持久的』(persistent) key 。<br/>
	 * 更新生存时间<br/>
	 * 可以对一个已经带有生存时间的 key 执行 EXPIRE 命令，新指定的生存时间会取代旧的生存时间。<br/>
	 * 过期时间的精确度<br/>
	 * 在 Redis 2.4 版本中，过期时间的延迟在 1 秒钟之内 —— 也即是，就算 key 已经过期，但它还是可能在过期之后一秒钟之内被访问到，而在新的 Redis 2.6 版本中，延迟被降低到 1 毫秒之内。<br/>
	 * Redis 2.1.3 之前的不同之处<br/>
	 * 在 Redis 2.1.3 之前的版本中，修改一个带有生存时间的 key 会导致整个 key 被删除，这一行为是受当时复制(replication)层的限制而作出的，现在这一限制已经被修复。
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public  Long expire(final String key,final int seconds);

	/**
	 * 在某段时间后实现<br/>
	 * EXPIRE key seconds<br/>
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。<br/>
	 * 在 Redis 中，带有生存时间的 key 被称为『易失的』(volatile)。<br/>
	 * 生存时间可以通过使用 DEL 命令来删除整个 key 来移除，或者被 SET 和 GETSET 命令覆写(overwrite)，这意味着，如果一个命令只是修改(alter)一个带生存时间的 key 的值而不是用一个新的 key 值来代替(replace)它的话，那么生存时间不会被改变。<br/>
	 * 比如说，对一个 key 执行 INCR 命令，对一个列表进行 LPUSH 命令，或者对一个哈希表执行 HSET 命令，这类操作都不会修改 key 本身的生存时间。<br/>
	 * 另一方面，如果使用 RENAME 对一个 key 进行改名，那么改名后的 key 的生存时间和改名前一样。<br/>
	 * RENAME 命令的另一种可能是，尝试将一个带生存时间的 key 改名成另一个带生存时间的 another_key ，这时旧的 another_key (以及它的生存时间)会被删除，然后旧的 key 会改名为 another_key ，因此，新的 another_key 的生存时间也和原本的 key 一样。<br/>
	 * 使用 PERSIST 命令可以在不删除 key 的情况下，移除 key 的生存时间，让 key 重新成为一个『持久的』(persistent) key 。<br/>
	 * 更新生存时间<br/>
	 * 可以对一个已经带有生存时间的 key 执行 EXPIRE 命令，新指定的生存时间会取代旧的生存时间。<br/>
	 * 过期时间的精确度<br/>
	 * 在 Redis 2.4 版本中，过期时间的延迟在 1 秒钟之内 —— 也即是，就算 key 已经过期，但它还是可能在过期之后一秒钟之内被访问到，而在新的 Redis 2.6 版本中，延迟被降低到 1 毫秒之内。<br/>
	 * Redis 2.1.3 之前的不同之处<br/>
	 * 在 Redis 2.1.3 之前的版本中，修改一个带有生存时间的 key 会导致整个 key 被删除，这一行为是受当时复制(replication)层的限制而作出的，现在这一限制已经被修复。
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public  Long expire(final byte[] key,final int seconds);

	/**
	 * EXPIREAT key timestamp<br/>
	 * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置生存时间。<br/>
	 * 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。<br/>
	 * @param key
	 * @param unixTime
	 * @return 如果生存时间设置成功，返回 1 。
	 * 当 key 不存在或没办法设置生存时间，返回 0 。
	 */
	public  Long expireAt(final String key,final long unixTime);

	/**
	 * EXPIREAT key timestamp<br/>
	 * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置生存时间。<br/>
	 * 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。<br/>
	 * @param key
	 * @param unixTime
	 * @return 如果生存时间设置成功，返回 1 。
	 * 当 key 不存在或没办法设置生存时间，返回 0 。
	 */
	public  Long expireAt(final byte[] key,final long unixTime);

	/**
	 * TTL key
	 * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。<br/>
	 * 当 key 不存在时，返回 -2 。<br/>
	 * 当 key 存在但没有设置剩余生存时间时，返回 -1 。<br/>
	 * 否则，以秒为单位，返回 key 的剩余生存时间。<br/>
	 * 在 Redis 2.8 以前，当 key 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1 。
	 * @param key
	 * @return 
	 * Long 
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:45:33
	 */
	public  Long ttl(final String key);

	/**
	 * 
	 * TTL key
	 * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。<br/>
	 * 当 key 不存在时，返回 -2 。<br/>
	 * 当 key 存在但没有设置剩余生存时间时，返回 -1 。<br/>
	 * 否则，以秒为单位，返回 key 的剩余生存时间。<br/>
	 * 在 Redis 2.8 以前，当 key 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1 。
	 * @param key
	 * @return 
	 * Long 
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:51:59
	 */
	public  Long ttl(byte[] key);

	/**
	 * DEL key [key ...]
	 * 删除给定的一个或多个 key 。<br/>
	 * 不存在的 key 会被忽略。<br/>
	 * @param key
	 * @return  被删除 key 的数量
	 * Long 
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:47:42
	 */
	public  Long del(String key);

	/**
	 * 检查给定 key 是否存在。
	 * @param key
	 * @return 
	 * Boolean 若 key 存在，返回 1 ，否则返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:49:15
	 */
	public  Boolean exists(byte[] key);

	/**
	 * 检查给定 key 是否存在。
	 * @param key
	 * @return 
	 * Boolean 若 key 存在，返回 1 ，否则返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:49:15
	 */
	public  Boolean exists(String key);

	/**
	 * SORT key [BY pattern] [LIMIT offset count] [GET pattern [GET pattern ...]] [ASC | DESC] [ALPHA] [STORE destination]<br/>
	 * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。<br/>
	 * 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。<br/>
	 * @param key
	 * @return 
	 * List<String>  最简单的 SORT 使用方法是 SORT key 和 SORT key DESC ：<br/>
	 * SORT key 返回键值从小到大排序的结果。<br/>
	 * SORT key DESC 返回键值从大到小排序的结果。<br/>
	 * 假设 today_cost 列表保存了今日的开销金额， 那么可以用 SORT 命令对它进行排序：
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:52:34
	 */
	public  List<String> sort(final String key);
	
	/**
	 * 
	 * @param key
	 * @return 
	 * List<byte[]> 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:21:43
	 */
	public List<byte[]> sort(final byte[] key);
	/**
	 *key 根据条件排序
	 * @param key
	 * @param sortingParameters
	 * @return 
	 * List<String> 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:54:04
	 */
	public  List<String> sort(final String key,final SortingParams sortingParameters);
	
	/**
	 * 
	 * @param key
	 * @param sortingParameters
	 * @return 
	 * List<byte[]> 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:21:48
	 */
	public List<byte[]> sort(final byte[] key,final SortingParams sortingParameters);
	

}