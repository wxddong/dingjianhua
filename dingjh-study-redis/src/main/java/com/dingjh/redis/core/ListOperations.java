package com.dingjh.redis.core;

import java.util.List;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

public interface ListOperations {

	/**
	 * RPUSH key value [value ...]<br/>
	 * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。<br/>
	 * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，得出的结果列表为 a b c ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。<br/>
	 * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。<br/>
	 * 当 key 存在但不是列表类型时，返回一个错误。<br/>
	 * # 添加单个元素<br/>
	 * redis> RPUSH languages c<br/>
	 * (integer) 1<br/>
	 * # 添加重复元素<br/>
	 * redis> RPUSH languages c<br/>
	 * (integer) 2<br/>
	 * redis> LRANGE languages 0 -1 # 列表允许重复元素<br/>
	 * 1) "c"<br/>
	 * 2) "c"<br/>
	 * # 添加多个元素<br/>
	 * redis> RPUSH mylist a b c<br/>
	 * (integer) 3<br/>
	 * redis> LRANGE mylist 0 -1<br/>
	 * 1) "a"<br/>
	 * 2) "b"<br/>
	 * 3) "c"
	 * @param key
	 * @param string
	 * @return 
	 * Long 执行 RPUSH 操作后，表的长度。
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:22:28
	 */
	public  Long rpush(String key, String string);

	public  Long rpush(byte[] key, byte[] string);
	/**
	 * LPUSH key value [value ...]<br/>
	 * 将一个或多个值 value 插入到列表 key 的表头<br/>
	 * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。<br/>
	 * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。<br/>
	 * 当 key 存在但不是列表类型时，返回一个错误。<br/>
	 * # 加入单个元素<br/>
	 * redis> LPUSH languages python<br/>
	 * (integer) 1<br/>
	 * # 加入重复元素<br/>
	 * redis> LPUSH languages python<br/>
	 * (integer) 2<br/>
	 * redis> LRANGE languages 0 -1     # 列表允许重复元素<br/>
	 * 1) "python"<br/>
	 * 2) "python"<br/>
	 * # 加入多个元素<br/>
	 * redis> LPUSH mylist a b c<br/>
	 * (integer) 3<br/>
	 * redis> LRANGE mylist 0 -1<br/>
	 * 1) "c"<br/>
	 * 2) "b"<br/>
	 * 3) "a"
	 * @param key
	 * @param string
	 * @return 
	 * Long 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:26:47
	 */
	public  Long lpush(String key, String string);

	public  Long lpush(byte[] key, byte[] string);
	/**
	 * LLEN key<br/>
	 * 返回列表 key 的长度。<br/>
	 * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .<br/>
	 * 如果 key 不是列表类型，返回一个错误。<br/>
	 * # 空列表<br/>
	 * redis> LLEN job<br/>
	 * (integer) 0<br/>
	 * # 非空列表<br/>
	 * redis> LPUSH job "cook food"<br/>
	 * (integer) 1<br/>
	 * redis> LPUSH job "have lunch"<br/>
	 * (integer) 2<br/>
	 * redis> LLEN job<br/>
	 * (integer) 2<br/>
	 * @param key
	 * @return 
	 * Long 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:31:57
	 */
	public  Long llen(String key);

	public  Long llen(byte[] key);

	/**
	 * LRANGE key start stop<br/>
	 * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。<br/>
	 * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br/>
	 * 注意LRANGE命令和编程语言区间函数的区别<br/>
	 * 假如你有一个包含一百个元素的列表，对该列表执行 LRANGE list 0 10 ，结果是一个包含11个元素的列表，这表明 stop 下标也在 LRANGE 命令的取值范围之内(闭区间)，这和某些语言的区间函数可能不一致，比如Ruby的 Range.new 、 Array#slice 和Python的 range() 函数。<br/>
	 * 超出范围的下标<br/>
	 * 超出范围的下标值不会引起错误。<br/>
	 * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，那么 LRANGE 返回一个空列表。<br/>
	 * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。<br/>
	 * redis> RPUSH fp-language lisp<br/>
	 * (integer) 1<br/>
	 * redis> LRANGE fp-language 0 0<br/>
	 * 1) "lisp"<br/>
	 * redis> RPUSH fp-language scheme<br/>
	 * (integer) 2<br/>
	 * redis> LRANGE fp-language 0 1<br/>
	 * 1) "lisp"<br/>
	 * 2) "scheme"
	 * @param key
	 * @param start
	 * @param end
	 * @return 
	 * List<String> 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:36:54
	 */
	public  List<String> lrange(String key, long start, long end);

	public  List<byte[]> lrange(byte[] key, int start, int end);

	/**
	 * LTRIM key start stop<br/>
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。<br/>
	 * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。<br/>
	 * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br/>
	 * 当 key 不是列表类型时，返回一个错误。<br/>
	 * LTRIM 命令通常和 LPUSH 命令或 RPUSH 命令配合使用，举个例子：<br/>
	 * LPUSH log newest_log<br/>
	 * LTRIM log 0 99<br/>
	 * 这个例子模拟了一个日志程序，每次将最新日志 newest_log 放到 log 列表中，并且只保留最新的 100 项。注意当这样使用 LTRIM 命令时，时间复杂度是O(1)，因为平均情况下，每次只有一个元素被移除。<br/>
	 * 注意LTRIM命令和编程语言区间函数的区别<br/>
	 * 假如你有一个包含一百个元素的列表 list ，对该列表执行 LTRIM list 0 10 ，结果是一个包含11个元素的列表，这表明 stop 下标也在 LTRIM 命令的取值范围之内(闭区间)，这和某些语言的区间函数可能不一致，比如Ruby的 Range.new 、 Array#slice 和Python的 range() 函数。<br/>
	 * 超出范围的下标<br/>
	 * 超出范围的下标值不会引起错误。<br/>
	 * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start > stop ， LTRIM 返回一个空列表(因为 LTRIM 已经将整个列表清空)。<br/>
	 * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。<br/>
	 * # 情况 1： 常见情况， start 和 stop 都在列表的索引范围之内<br/>
	 * redis> LRANGE alpha 0 -1       # alpha 是一个包含 5 个字符串的列表<br/>
	 * 1) "h"<br/>
	 * 2) "e"<br/>
	 * 3) "l"<br/>
	 * 4) "l"<br/>
	 * 5) "o"<br/>
	 * redis> LTRIM alpha 1 -1        # 删除 alpha 列表索引为 0 的元素<br/>
	 * OK<br/>
	 * redis> LRANGE alpha 0 -1       # "h" 被删除了<br/>
	 * 1) "e"<br/>
	 * 2) "l"<br/>
	 * 3) "l"<br/>
	 * 4) "o"<br/>
	 * # 情况 2： stop 比列表的最大下标还要大<br/>
	 * redis> LTRIM alpha 1 10086     # 保留 alpha 列表索引 1 至索引 10086 上的元素<br/>
	 * OK<br/>
	 * redis> LRANGE alpha 0 -1       # 只有索引 0 上的元素 "e" 被删除了，其他元素还在<br/>
	 * 1) "l"<br/>
	 * 2) "l"<br/>
	 * 3) "o"<br/>
	 * # 情况 3： start 和 stop 都比列表的最大下标要大，并且 start < stop<br/>
	 * redis> LTRIM alpha 10086 123321<br/>
	 * OK<br/>
	 * redis> LRANGE alpha 0 -1        # 列表被清空<br/>
	 * (empty list or set)<br/>
	 * # 情况 4： start 和 stop 都比列表的最大下标要大，并且 start > stop<br/>
	 * redis> RPUSH new-alpha "h" "e" "l" "l" "o"     # 重新建立一个新列表<br/>
	 * (integer) 5<br/>
	 * redis> LRANGE new-alpha 0 -1<br/>
	 * 1) "h"<br/>
	 * 2) "e"<br/>
	 * 3) "l"<br/>
	 * 4) "l"<br/>
	 * 5) "o"<br/>
	 * redis> LTRIM new-alpha 123321 10086    # 执行 LTRIM<br/>
	 * OK<br/>
	 * redis> LRANGE new-alpha 0 -1           # 同样被清空<br/>
	 * (empty list or set)<br/>
	 * @param key<br/>
	 * @param start<br/>
	 * @param end<br/>
	 * @return 
	 * String 命令执行成功时，返回 ok 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:38:02
	 */
	public  String ltrim(String key, long start, long end);

	public  String ltrim(byte[] key, int start, int end);

	/**
	 * LINDEX key index<br/>
	 * 返回列表 key 中，下标为 index 的元素。<br/>
	 * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br/>
	 * 如果 key 不是列表类型，返回一个错误。<br/>
	 * redis> LPUSH mylist "World"<br/>
	 * (integer) 1<br/>
	 * redis> LPUSH mylist "Hello"<br/>
	 * (integer) 2<br/>
	 * redis> LINDEX mylist 0<br/>
	 * "Hello"<br/>
	 * redis> LINDEX mylist -1<br/>
	 * "World"<br/>
	 * redis> LINDEX mylist 3        # index不在 mylist 的区间范围内<br/>
	 * (nil)
	 * @param key
	 * @param index
	 * @return 
	 * String 列表中下标为 index 的元素。如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:48:58
	 */
	public  String lindex(String key, long index);

	public  byte[] lindex(byte[] key, int index);

	/**
	 * LSET key index value<br/>
	 * 将列表 key 下标为 index 的元素的值设置为 value 。<br/>
	 * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。<br/>
	 * 关于列表下标的更多信息，请参考 LINDEX 命令。<br/>
	 * # 对空列表(key 不存在)进行 LSET<br/>
	 * redis> EXISTS list<br/>
	 * (integer) 0<br/>
	 * redis> LSET list 0 item<br/>
	 * (error) ERR no such key<br/>
	 * # 对非空列表进行 LSET<br/>
	 * redis> LPUSH job "cook food"<br/>
	 * (integer) 1<br/>
	 * redis> LRANGE job 0 0<br/>
	 * 1) "cook food"<br/>
	 * redis> LSET job 0 "play game"<br/>
	 * OK<br/>
	 * redis> LRANGE job  0 0<br/>
	 * 1) "play game"<br/>
	 * # index 超出范围<br/>
	 * redis> LLEN list                    # 列表长度为 1<br/>
	 * (integer) 1<br/>
	 * redis> LSET list 3 'out of range'<br/>
	 * (error) ERR index out of range<br/>
	 * @param key
	 * @param index
	 * @param value
	 * @return 
	 * String 
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:53:24
	 */
	public  String lset(String key, long index, String value);

	public  String lset(byte[] key, int index, byte[] value);

	/**
	 * LREM key count value<br/>
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素。<br/>
	 * count 的值可以是以下几种：<br/>
	 * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。<br/>
	 * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。<br/>
	 * count = 0 : 移除表中所有与 value 相等的值<br/>
	 * @param key
	 * @param count
	 * @param value
	 * @return 
	 * Long 被移除元素的数量。因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午5:57:54
	 */
	public  Long lrem(String key, long count, String value);

	public  Long lrem(byte[] key, int count, byte[] value);

	/**
	 * 移除并返回列表 key 的头元素。<br/>
	 * redis> LLEN course<br/>
	 * (integer) 0<br/>
	 * redis> RPUSH course algorithm001<br/>
	 * (integer) 1<br/>
	 * redis> RPUSH course c++101<br/>
	 * (integer) 2<br/>
	 * redis> LPOP course  # 移除头元素<br/>
	 * "algorithm001"<br/>
	 * @param key
	 * @return 
	 * String 列表的头元素。当 key 不存在时，返回 nil 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:00:40
	 */
	public  String lpop(String key);

	/**
	 * 移除并返回列表 key 的头元素。<br/>
	 * @Title: lpop
	 * @Description: TODO()
	 * @param key
	 * @return 
	 * byte[] 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:17:51
	 */
	public  byte[] lpop(byte[] key);

	/**
	 * RPOP key
	 * 移除并返回列表 key 的尾元素。<br/>
	 * redis> RPUSH mylist "one"<br/>
	 * (integer) 1<br/>
	 * redis> RPUSH mylist "two"<br/>
	 * (integer) 2<br/>
	 * redis> RPUSH mylist "three"<br/>
	 * (integer) 3<br/>
	 * redis> RPOP mylist           # 返回被弹出的元素<br/>
	 * "three"<br/>
	 * redis> LRANGE mylist 0 -1    # 列表剩下的元素<br/>
	 * 1) "one"<br/>
	 * 2) "two"<br/>
	 * @param key<br/>
	 * @return 
	 * String 列表的尾元素。当 key 不存在时，返回 nil 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:03:07
	 */
	public  String rpop(String key);

	public  byte[] rpop(byte[] key);

	/**
	 * LINSERT key BEFORE|AFTER pivot value<br/>
	 * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。<br/>
	 * 当 pivot 不存在于列表 key 时，不执行任何操作。<br/>
	 * 当 key 不存在时， key 被视为空列表，不执行任何操作。<br/>
	 * 如果 key 不是列表类型，返回一个错误。<br/>
	 * redis> RPUSH mylist "Hello"<br/>
	 * (integer) 1<br/>
	 * redis> RPUSH mylist "World"<br/>
	 * (integer) 2<br/>
	 * redis> LINSERT mylist BEFORE "World" "There"<br/>
	 * (integer) 3<br/>
	 * redis> LRANGE mylist 0 -1<br/>
	 * 1) "Hello"<br/>
	 * 2) "There"<br/>
	 * 3) "World"<br/>
	 * # 对一个非空列表插入，查找一个不存在的 pivot<br/>
	 * redis> LINSERT mylist BEFORE "go" "let's"<br/>
	 * (integer) -1                                    # 失败<br/>
	 * # 对一个空列表执行 LINSERT 命令<br/>
	 * redis> EXISTS fake_list<br/>
	 * (integer) 0<br/>
	 * redis> LINSERT fake_list BEFORE "nono" "gogogog"<br/>
	 * (integer) 0                                      # 失败<br/>
	 * @param key
	 * @param where
	 * @param pivot
	 * @param value
	 * @return 
	 * Long 如果命令执行成功，返回插入操作完成之后，列表的长度。如果没有找到 pivot ，返回 -1 。如果 key 不存在或为空列表，返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:13:53
	 */
	public  Long linsert(String key, LIST_POSITION where, String pivot, String value);

	public  Long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value);

}