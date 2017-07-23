package com.dingjh.redis.core;

import java.util.Set;
import redis.clients.jedis.Tuple;

public interface SortedSetOperations {

	/**
	 * ZCOUNT key min max<br/>
	 * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。<br/>
	 * 关于参数 min 和 max 的详细使用方法，请参考 ZRANGEBYSCORE 命令。<br/>
	 * redis> ZRANGE salary 0 -1 WITHSCORES    # 测试数据<br/>
	 * 1) "jack"<br/>
	 * 2) "2000"<br/>
	 * 3) "peter"<br/>
	 * 4) "3500"<br/>
	 * 5) "tom"<br/>
	 * 6) "5000"<br/>
	 * redis> ZCOUNT salary 2000 5000          # 计算薪水在 2000-5000 之间的人数<br/>
	 * (integer) 3<br/>
	 * redis> ZCOUNT salary 3000 5000          # 计算薪水在 3000-5000 之间的人数<br/>
	 * (integer) 2
	 * @param key
	 * @param min
	 * @param max
	 * @return 
	 * Long score 值在 min 和 max 之间的成员的数量。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:22:26
	 */
	public  Long zcount(byte[] key, double min, double max);

	public  Set<Tuple> zrangeWithScores(byte[] key, int start, int end);

	public  Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end);

	public  Set<byte[]> zrevrange(byte[] key, int start, int end);

	public  Long zrevrank(byte[] key, byte[] member);

	/**
	 * ZADD key score member [[score member] [score member] ...]<br/>
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。<br/>
	 * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。<br/>
	 * score 值可以是整数值或双精度浮点数。<br/>
	 * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br/>
	 * 当 key 存在但不是有序集类型时，返回一个错误。<br/>
	 * 对有序集的更多介绍请参见 sorted set 。<br/>
	 * # 添加单个元素<br/>
	 * redis> ZADD page_rank 10 google.com<br/>
	 * (integer) 1<br/>
	 * # 添加多个元素<br/>
	 * redis> ZADD page_rank 9 baidu.com 8 bing.com<br/>
	 * (integer) 2<br/>
	 * redis> ZRANGE page_rank 0 -1 WITHSCORES<br/>
	 * 1) "bing.com"<br/>
	 * 2) "8"<br/>
	 * 3) "baidu.com"<br/>
	 * 4) "9"<br/>
	 * 5) "google.com"<br/>
	 * 6) "10"<br/>
	 * # 添加已存在元素，且 score 值不变<br/>
	 * redis> ZADD page_rank 10 google.com<br/>
	 * (integer) 0<br/>
	 * redis> ZRANGE page_rank 0 -1 WITHSCORES  # 没有改变<br/>
	 * 1) "bing.com"<br/>
	 * 2) "8"<br/>
	 * 3) "baidu.com"<br/>
	 * 4) "9"<br/>
	 * 5) "google.com"<br/>
	 * 6) "10"<br/>
	 * # 添加已存在元素，但是改变 score 值<br/>
	 * redis> ZADD page_rank 6 bing.com<br/>
	 * (integer) 0<br/>
	 * redis> ZRANGE page_rank 0 -1 WITHSCORES  # bing.com 元素的 score 值被改变<br/>
	 * 1) "bing.com"<br/>
	 * 2) "6"<br/>
	 * 3) "baidu.com"<br/>
	 * ) "9"<br/>
	 * 5) "google.com"<br/>
	 * 6) "10"
	 * @param key
	 * @param score
	 * @param member
	 * @return 
	 * Long 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:55:40
	 */
	public  Long zadd(String key, double score, String member);

	/**
	 * ZRANGE key start stop [WITHSCORES]<br/>
	 * 返回有序集 key 中，指定区间内的成员。<br/>
	 * 其中成员的位置按 score 值递增(从小到大)来排序。<br/>
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。<br/>
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。<br/>
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。<br/>
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。<br/>
	 * 超出范围的下标并不会引起错误。<br/>
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。<br/>
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。<br/>
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。<br/>
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。<br/>
	 * redis > ZRANGE salary 0 -1 WITHSCORES             # 显示整个有序集成员<br/>
	 * 1) "jack"<br/>
	 * 2) "3500"<br/>
	 * 3) "tom"<br/>
	 * 4) "5000"<br/>
	 * 5) "boss"<br/>
	 * 6) "10086"<br/>
	 * redis > ZRANGE salary 1 2 WITHSCORES              # 显示有序集下标区间 1 至 2 的成员<br/>
	 * 1) "tom"<br/>
	 * 2) "5000"<br/>
	 * 3) "boss"<br/>
	 * 4) "10086"<br/>
	 * redis > ZRANGE salary 0 200000 WITHSCORES         # 测试 end 下标超出最大下标时的情况<br/>
	 * 1) "jack"<br/>
	 * 2) "3500"<br/>
	 * 3) "tom"<br/>
	 * 4) "5000"<br/>
	 * 5) "boss"<br/>
	 * 6) "10086"<br/>
	 * redis > ZRANGE salary 200000 3000000 WITHSCORES   # 测试当给定区间不存在于有序集时的情况<br/>
	 * (empty list or set)
	 * @param key
	 * @param start
	 * @param end
	 * @return 
	 * Set<String> 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:59:43
	 */
	public  Set<String> zrange(String key, int start, int end);

	/**
	 * ZREM key member [member ...]<br/>
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。<br/>
	 * 当 key 存在但不是有序集类型时，返回一个错误。<br/>
	 * # 测试数据<br/>
	 * redis> ZRANGE page_rank 0 -1 WITHSCORES<br/>
	 * 1) "bing.com"<br/>
	 * 2) "8"<br/>
	 * 3) "baidu.com"<br/>
	 * 4) "9"<br/>
	 * 5) "google.com"<br/>
	 * 6) "10"<br/>
	 * # 移除单个元素<br/>
	 * redis> ZREM page_rank google.com<br/>
	 * (integer) 1<br/>
	 * redis> ZRANGE page_rank 0 -1 WITHSCORES<br/>
	 * 1) "bing.com"<br/>
	 * 2) "8"<br/>
	 * 3) "baidu.com"<br/>
	 * 4) "9"<br/>
	 * # 移除多个元素<br/>
	 * redis> ZREM page_rank baidu.com bing.com<br/>
	 * (integer) 2<br/>
	 * redis> ZRANGE page_rank 0 -1 WITHSCORES<br/>
	 * (empty list or set)<br/>
	 * # 移除不存在元素<br/>
	 * redis> ZREM page_rank non-exists-element<br/>
	 * (integer) 0
	 * @param key
	 * @param member
	 * @return 
	 * Long  被成功移除的成员的数量，不包括被忽略的成员
	 * @author dingjianhua
	 * @date 2014-12-5 下午7:03:03
	 */
	public  Long zrem(String key, String member);

	/**
	 * ZINCRBY key increment member<br/>
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment 。<br/>
	 * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。<br/>
	 * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。<br/>
	 * 当 key 不是有序集类型时，返回一个错误。<br/>
	 * score 值可以是整数值或双精度浮点数<br/>
	 * redis> ZSCORE salary tom<br/>
	 * "2000"<br/>
	 * redis> ZINCRBY salary 2000 tom   # tom 加薪啦！<br/>
	 * "4000"
	 * @param key
	 * @param score
	 * @param member
	 * @return 
	 * Double member 成员的新 score 值，以字符串形式表示。
	 * @author dingjianhua
	 * @date 2014-12-5 下午7:05:19
	 */
	public  Double zincrby(String key, double score, String member);

	/**
	 * ZRANK key member<br/>
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。<br/>
	 * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。<br/>
	 * 使用 ZREVRANK 命令可以获得成员按 score 值递减(从大到小)排列的排名。<br/>
	 * redis> ZRANGE salary 0 -1 WITHSCORES        # 显示所有成员及其 score 值<br/>
	 * 1) "peter"<br/>
	 * 2) "3500"<br/>
	 * 3) "tom"<br/>
	 * 4) "4000"<br/>
	 * 5) "jack"<br/>
	 * 6) "5000"<br/>
	 * redis> ZRANK salary tom                     # 显示 tom 的薪水排名，第二<br/>
	 * (integer) 1
	 * @param key
	 * @param member
	 * @return 
	 * Long 如果 member 是有序集 key 的成员，返回 member 的排名。如果 member 不是有序集 key 的成员，返回 nil 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午7:07:01
	 */
	public  Long zrank(String key, String member);

	public  Long zrank(byte[] key, byte[] member);

	/**
	 * ZREVRANK key member<br/>
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。<br/>
	 * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。<br/>
	 * 使用 ZRANK 命令可以获得成员按 score 值递增(从小到大)排列的排名。<br/>
	 * redis 127.0.0.1:6379> ZRANGE salary 0 -1 WITHSCORES     # 测试数据<br/>
	 * 1) "jack"<br/>
	 * 2) "2000"<br/>
	 * 3) "peter"<br/>
	 * 4) "3500"<br/>
	 * 5) "tom"<br/>
	 * 6) "5000"<br/>
	 * redis> ZREVRANK salary peter     # peter 的工资排第二<br/>
	 * (integer) 1<br/>
	 * redis> ZREVRANK salary tom       # tom 的工资最高<br/>
	 * (integer) 0
	 * @param key
	 * @param member
	 * @return 
	 * Long 如果 member 是有序集 key 的成员，返回 member 的排名。如果 member 不是有序集 key 的成员，返回 nil 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午7:08:32
	 */
	public  Long zrevrank(String key, String member);

	/**
	 * ZREVRANGE key start stop [WITHSCORES]<br/>
	 * 返回有序集 key 中，指定区间内的成员。<br/>
	 * 其中成员的位置按 score 值递减(从大到小)来排列。<br/>
	 * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order)排列。<br/>
	 * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。<br/>
	 * redis> ZRANGE salary 0 -1 WITHSCORES        # 递增排列<br/>
	 * 1) "peter"<br/>
	 * 2) "3500"<br/>
	 * 3) "tom"<br/>
	 * 4) "4000"<br/>
	 * 5) "jack"<br/>
	 * 6) "5000"<br/>
	 * redis> ZREVRANGE salary 0 -1 WITHSCORES     # 递减排列<br/>
	 * 1) "jack"<br/>
	 * 2) "5000"<br/>
	 * 3) "tom"<br/>
	 * 4) "4000"<br/>
	 * 5) "peter"<br/>
	 * 6) "3500"
	 * @param start
	 * @param end
	 * @return 
	 * Set<String> 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:49:33
	 */
	public  Set<String> zrevrange(String key, int start, int end);

	public  Set<Tuple> zrangeWithScores(String key, int start, int end);

	public  Set<Tuple> zrevrangeWithScores(String key, int start, int end);

	/**
	 * 返回有序集 key 的基数<br/>
	 * redis > ZADD salary 2000 tom    # 添加一个成员<br/>
	 * (integer) 1<br/>
	 * redis > ZCARD salary<br/>
	 * (integer) 1<br/>
	 * redis > ZADD salary 5000 jack   # 再添加一个成员<br/>
	 * (integer) 1<br/>
	 * redis > ZCARD salary<br/>
	 * (integer) 2<br/>
	 * redis > EXISTS non_exists_key   # 对不存在的 key 进行 ZCARD 操作<br/>
	 * (integer) 0<br/>
	 * redis > ZCARD non_exists_key<br/>
	 * (integer) 0<br/>
	 * @param key
	 * @return 
	 * Long 当 key 存在且是有序集类型时，返回有序集的基数。当 key 不存在时，返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:51:48
	 */
	public  Long zcard(String key);

	public  Double zscore(String key, String member);

	public  Long zcount(String key, double min, double max);

	public  Set<String> zrangeByScore(String key, double min, double max);

	/**
	 * ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]<br/>
	 * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列。<br/>
	 * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order )排列。<br/>
	 * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。<br/>
	 * redis > ZADD salary 10086 jack<br/>
	 * (integer) 1<br/>
	 * redis > ZADD salary 5000 tom<br/>
	 * (integer) 1<br/>
	 * redis > ZADD salary 7500 peter<br/>
	 * (integer) 1<br/>
	 * redis > ZADD salary 3500 joe<br/>
	 * (integer) 1<br/>
	 * redis > ZREVRANGEBYSCORE salary +inf -inf   # 逆序排列所有成员<br/>
	 * 1) "jack"<br/>
	 * 2) "peter"<br/>
	 * 3) "tom"<br/>
	 * 4) "joe"<br/>
	 * redis > ZREVRANGEBYSCORE salary 10000 2000  # 逆序排列薪水介于 10000 和 2000 之间的成员<br/>
	 * 1) "peter"<br/>
	 * 2) "tom"<br/>
	 * 3) "joe"<br/>
	 * @param key
	 * @param max
	 * @param min
	 * @return 
	 * Set<String> 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午7:11:34
	 */
	public  Set<String> zrevrangeByScore(String key, double max, double min);

	public  Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);

	public  Set<String> zrangeByScore(String key, double min, double max, int offset, int count);

	public  Set<Tuple> zrangeByScoreWithScores(String key, double min, double max);

	public  Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min);

	public  Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

	public  Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count);

	/**
	 * ZREMRANGEBYRANK key start stop
	 * 移除有序集 key 中，指定排名(rank)区间内的所有成员。
	 * 区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * @param key
	 * @param start
	 * @param end
	 * @return 
	 * Long 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午7:14:13
	 */
	public  Long zremrangeByRank(String key, int start, int end);

	public  Long zremrangeByScore(String key, double start, double end);

	public  Long zadd(byte[] key, double score, byte[] member);

	public  Double zincrby(byte[] key, double score, byte[] member);

	public  Long zcard(byte[] key);

	public  Double zscore(byte[] key, byte[] member);

	public  Long zremrangeByRank(byte[] key, int start, int end);

	public  Long zremrangeByScore(byte[] key, double start, double end);

	public  Set<byte[]> zrangeByScore(byte[] key, double min, double max);

	public  Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count);

	public  Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max);

	public  Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count);

	public  Set<byte[]> zrevrangeByScore(byte[] key, double max, double min);

	public  Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count);

	public  Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min);

	public  Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count);

}