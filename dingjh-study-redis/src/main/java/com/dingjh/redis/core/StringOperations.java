package com.dingjh.redis.core;

import java.util.List;

public interface StringOperations {
    /** 
     * 批量的 {@link #getString(String)} 
     * @param keys key数组 
     * @return value的集合 
     */  
    public List<String> batchGetString(final String[] keys);
    /** 
     * 批量的 {@link #setString(String, String)} 
     * @param pairs 键值对数组{数组第一个元素为key，第二个元素为value} 
     * @return 操作状态的集合 
     */  
    public List<Object> batchSetString(final List<Pair<String, String>> pairs);

	/**
	 * SETNX key value<br/>
	 * 将 key 的值设为 value ，当且仅当 key 不存在。<br/>
	 * 若给定的 key 已经存在，则 SETNX 不做任何动作。<br/>
	 * SETNX 是『SET if Not eXists』(如果不存在，则 SET)的简写。
	 * @param key
	 * @param value
	 * @return 
	 * Long 设置成功，返回 1 。设置失败，返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:27:22
	 */
	public  Long setnx(final byte[] key, final byte[] value);

	/**
	 * SETNX key value<br/>
	 * 将 key 的值设为 value ，当且仅当 key 不存在。<br/>
	 * 若给定的 key 已经存在，则 SETNX 不做任何动作<br/>
	 * redis> EXISTS job                # job 不存在<br/>
	 * (integer) 0<br/>
	 * redis> SETNX job "programmer"    # job 设置成功<br/>
	 * (integer) 1<br/>
	 * redis> SETNX job "code-farmer"   # 尝试覆盖 job ，失败<br/>
	 * (integer) 0<br/>
	 * redis> GET job                   # 没有被覆盖<br/>
	 * "programmer"
	 * @param key
	 * @param value
	 * @return 
	 * Long 设置成功，返回 1 。设置失败，返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:29:41
	 */
	public  Long setnx(final String key, final String value);

	/**
	 * SETNX key value seconds<br/>
	 * 将 key 的值设为 value ,有效期seconds，当且仅当 key 不存在。<br/>
	 * @param key
	 * @param seconds
	 * @param value
	 * @return 
	 * String 
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:30:26
	 */
	public  String setex(final String key, final int seconds, final String value);

	/**
	 * SETNX key value seconds<br/>
	 * 将 key 的值设为 value ,有效期seconds，当且仅当 key 不存在。
	 * @param key
	 * @param seconds
	 * @param value
	 * @return 
	 * String 
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:28:19
	 */
	public  String setex(final byte[] key, final int seconds, final byte[] value);

	/**
	 * DECRBY key decrement<br/>
	 * 将 key 所储存的值减去减量 decrement 。<br/>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。<br/>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
	 * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br/>
	 * 关于更多递增(increment) / 递减(decrement)操作的更多信息，请参见 INCR 命令。<br/>
	 * # 对已存在的 key 进行 DECRBY<br/>
	 * redis> SET count 100<br/>
	 * OK<br/>
	 * redis> DECRBY count 20<br/>
	 * (integer) 80<br/>
	 * # 对不存在的 key 进行DECRBY<br/>
	 * redis> EXISTS pages<br/>
	 * (integer) 0<br/>
	 * redis> DECRBY pages 10<br/>
	 * (integer) -10<br/>
	 * @param key
	 * @param integer
	 * @return 
	 * Long 
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:33:13
	 */
	public  Long decrBy(final String key, final long integer);

	public  Long decrBy(final byte[] key, final long integer);

	/**
	 * DECR key
	 * 将 key 中储存的数字值减一。<br/>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。<br/>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
	 * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br/>
	 * 关于递增(increment) / 递减(decrement)操作的更多信息，请参见 INCR 命令。<br/>
	 * # 对存在的数字值 key 进行 DECR<br/>
	 * redis> SET failure_times 10<br/>
	 * OK<br/>
	 * redis> DECR failure_times<br/>
	 * (integer) 9<br/>
	 * # 对不存在的 key 值进行 DECR<br/>
	 * redis> EXISTS count<br/>
	 * (integer) 0<br/>
	 * redis> DECR count<br/>
	 * (integer) -1<br/>
	 * # 对存在但不是数值的 key 进行 DECR<br/>
	 * redis> SET company YOUR_CODE_SUCKS.LLC<br/>
	 * OK<br/>
	 * redis> DECR company<br/>
	 * (error) ERR value is not an integer or out of range<br/>
	 * @param key
	 * @return 
	 * Long 执行 DECR 命令之后 key 的值。
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:37:07
	 */
	public  Long decr(final String key);

	public  Long decr(final byte[] key);

	public  Long incrBy(final byte[] key, final long integer);

	/**
	 * INCRBY key integer<br/>
	 * 将 key 所储存的值加上增量 integer 。<br/>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。<br/>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
	 * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br/>
	 * 关于递增(increment) / 递减(decrement)操作的更多信息，参见 INCR 命令。<br/>
	 * # key 存在且是数字值<br/>
	 * redis> SET rank 50<br/>
	 * OK<br/>
	 * redis> INCRBY rank 20<br/>
	 * (integer) 70<br/>
	 * redis> GET rank<br/>
	 * "70"<br/>
	 * # key 不存在时<br/>
	 * redis> EXISTS counter<br/>
	 * (integer) 0<br/>
	 * redis> INCRBY counter 30<br/>
	 * (integer) 30<br/>
	 * redis> GET counter<br/>
	 * "30"<br/>
	 * # key 不是数字值时<br/>
	 * redis> SET book "long long ago..."<br/>
	 * OK<br/>
	 * redis> INCRBY book 200<br/>
	 * (error) ERR value is not an integer or out of range<br/>
	 * @param key
	 * @param integer
	 * @return 
	 * Long 加上 increment 之后， key 的值。
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:40:09
	 */
	public  Long incrBy(final String key, final long integer);

	/**
	 * INCR key<br/>
	 * 将 key 中储存的数字值增一。<br/>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。<br/>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
	 * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br/>
	 * 这是一个针对字符串的操作，因为 Redis 没有专用的整数类型，所以 key 内储存的字符串被解释为十进制 64 位有符号整数来执行 INCR 操作。<br/>
	 * redis> SET page_view 20<br/>
	 * OK<br/>
	 * redis> INCR page_view<br/>
	 * (integer) 21<br/>
	 * redis> GET page_view    # 数字值在 Redis 中以字符串的形式保存<br/>
	 * "21"<br/>
	 * @param key
	 * @return 
	 * Long 执行 INCR 命令之后 key 的值。
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:43:42
	 */
	public  Long incr(final String key);

	public  Long incr(final byte[] key);

	/**
	 * APPEND key value<br/>
	 * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。<br/>
	 * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。<br/>
	 * # 对不存在的 key 执行 APPEND<br/>
	 * redis> EXISTS myphone               # 确保 myphone 不存在<br/>
	 * (integer) 0<br/>
	 * redis> APPEND myphone "nokia"       # 对不存在的 key 进行 APPEND ，等同于 SET myphone "nokia"<br/>
	 * (integer) 5                         # 字符长度<br/>
	 * # 对已存在的字符串进行 APPEND<br/>
	 * redis> APPEND myphone " - 1110"     # 长度从 5 个字符增加到 12 个字符<br/>
	 * (integer) 12<br/>
	 * redis> GET myphone<br/>
	 * "nokia - 1110"
	 * @param key
	 * @param value
	 * @return 
	 * Long 追加 value 之后， key 中字符串的长度。
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:45:58
	 */
	public  Long append(final String key, final String value);

	public  Long append(final byte[] key, final byte[] value);

	public  byte[] substr(final byte[] key, final int start, final int end);

	public  boolean setbit(final String key, final long offset, final boolean value);

	public  long setrange(final String key, final long offset, final String value);

	public  String getrange(final String key, final long startOffset, final long endOffset);

	/**
	 * GETSET key value<br/>
	 * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。<br/>
	 * 当 key 存在但不是字符串类型时，返回一个错误。<br/>
	 * redis> GETSET db mongodb    # 没有旧值，返回 nil<br/>
	 * (nil)<br/>
	 * redis> GET db<br/>
	 * "mongodb"<br/>
	 * redis> GETSET db redis      # 返回旧值 mongodb<br/>
	 * "mongodb"<br/>
	 * redis> GET db<br/>
	 * "redis"<br/>
	 * @param key
	 * @param value
	 * @return 
	 * String 返回给定 key 的旧值。当 key 没有旧值时，也即是， key 不存在时，返回 nil 。
	 * @author dingjianhua
	 * @date 2014-12-6 上午2:01:04
	 */
	public  String getSet(final String key, final String value);

	public  byte[] getSet(final byte[] key, final byte[] value);

	public  String substr(final String key, final int start, final int end);

	/**
	 * GETBIT key offset<br/>
	 * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。<br/>
	 * 当 offset 比字符串值的长度大，或者 key 不存在时，返回 0 。<br/>
	 * @param key
	 * @param offset
	 * @return 
	 * boolean 
	 * @author dingjianhua
	 * @date 2014-12-6 上午1:59:44
	 */
	public  boolean getbit(final String key, final long offset);

	/**
	 * SET key value [EX seconds] [PX milliseconds] [NX|XX]<br/>
	 * 将字符串值 value 关联到 key 。<br/>
	 * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。<br/>
	 * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。<br/>
	 * 可选参数<br/>
	 * 从 Redis 2.6.12 版本开始， SET 命令的行为可以通过一系列参数来修改：<br/>
	 * EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 SETEX key second value 。<br/>
	 * PX millisecond ：设置键的过期时间为 millisecond 毫秒。 SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。<br/>
	 * NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。<br/>
	 * XX ：只在键已经存在时，才对键进行设置操作。<br/>
	 * 因为 SET 命令可以通过参数来实现和 SETNX 、 SETEX 和 PSETEX 三个命令的效果，所以将来的 Redis 版本可能会废弃并最终移除 SETNX 、 SETEX 和 PSETEX 这三个命令。<br/>
	 * # 对不存在的键进行设置<br/>
	 * redis 127.0.0.1:6379> SET key "value"<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> GET key<br/>
	 * "value"<br/>
	 * # 对已存在的键进行设置<br/>
	 * redis 127.0.0.1:6379> SET key "new-value"<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> GET key<br/>
	 * "new-value"<br/>
	 * # 使用 EX 选项<br/>
	 * redis 127.0.0.1:6379> SET key-with-expire-time "hello" EX 10086<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> GET key-with-expire-time<br/>
	 * "hello"<br/>
	 * redis 127.0.0.1:6379> TTL key-with-expire-time<br/>
	 * (integer) 10069<br/>
	 * # 使用 PX 选项<br/>
	 * redis 127.0.0.1:6379> SET key-with-pexpire-time "moto" PX 123321<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> GET key-with-pexpire-time<br/>
	 * "moto"<br/>
	 * redis 127.0.0.1:6379> PTTL key-with-pexpire-time<br/>
	 * (integer) 111939<br/>
	 * # 使用 NX 选项<br/>
	 * redis 127.0.0.1:6379> SET not-exists-key "value" NX<br/>
	 * OK      # 键不存在，设置成功<br/>
	 * redis 127.0.0.1:6379> GET not-exists-key<br/>
	 * "value"<br/>
	 * redis 127.0.0.1:6379> SET not-exists-key "new-value" NX<br/>
	 * (nil)   # 键已经存在，设置失败<br/>
	 * redis 127.0.0.1:6379> GEt not-exists-key<br/>
	 * "value" # 维持原值不变<br/>
	 * # 使用 XX 选项<br/>
	 * redis 127.0.0.1:6379> EXISTS exists-key<br/>
	 * (integer) 0<br/>
	 * redis 127.0.0.1:6379> SET exists-key "value" XX<br/>
	 * (nil)   # 因为键不存在，设置失败<br/>
	 * redis 127.0.0.1:6379> SET exists-key "value"<br/>
	 * OK      # 先给键设置一个值<br/>
	 * redis 127.0.0.1:6379> SET exists-key "new-value" XX<br/>
	 * OK      # 设置新值成功<br/>
	 * redis 127.0.0.1:6379> GET exists-key<br/>
	 * "new-value"<br/>
	 * # NX 或 XX 可以和 EX 或者 PX 组合使用<br/>
	 * redis 127.0.0.1:6379> SET key-with-expire-and-NX "hello" EX 10086 NX<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> GET key-with-expire-and-NX<br/>
	 * "hello"<br/>
	 * redis 127.0.0.1:6379> TTL key-with-expire-and-NX<br/>
	 * (integer) 10063<br/>
	 * redis 127.0.0.1:6379> SET key-with-pexpire-and-XX "old value"<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> SET key-with-pexpire-and-XX "new value" PX 123321<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> GET key-with-pexpire-and-XX<br/>
	 * "new value"<br/>
	 * redis 127.0.0.1:6379> PTTL key-with-pexpire-and-XX<br/>
	 * (integer) 112999<br/>
	 * # EX 和 PX 可以同时出现，但后面给出的选项会覆盖前面给出的选项<br/>
	 * redis 127.0.0.1:6379> SET key "value" EX 1000 PX 5000000<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> TTL key<br/>
	 * (integer) 4993  # 这是 PX 参数设置的值<br/>
	 * redis 127.0.0.1:6379> SET another-key "value" PX 5000000 EX 1000<br/>
	 * OK<br/>
	 * redis 127.0.0.1:6379> TTL another-key<br/>
	 * (integer) 997   # 这是 EX 参数设置的值<br/>
	 * @param key
	 * @param value
	 * @return 在 Redis 2.6.12 版本以前， SET 命令总是返回 OK 。
	 * 从 Redis 2.6.12 版本开始， SET 在设置操作成功完成时，才返回 OK 。
	 * 如果设置了 NX 或者 XX ，但因为条件没达到而造成设置操作未执行，那么命令返回空批量回复（NULL Bulk Reply）。
	 */
	public  String set(final String key, final String value);

	public  String set(final byte[] key, final byte[] value);

	/**
	 *返回 key 所关联的字符串值。<br/>
	 *如果 key 不存在那么返回特殊值 nil 。<br/>
	 *假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
	 * @param key
	 * @return 当 key 不存在时，返回 nil ，否则，返回 key 的值。如果 key 不是字符串类型，那么返回一个错误。# 
	 */
	public  String get(final String key);

	public  byte[] get(final byte[] key);

}