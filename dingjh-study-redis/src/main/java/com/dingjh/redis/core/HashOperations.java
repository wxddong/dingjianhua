package com.dingjh.redis.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @ClassName: HashOperations
 * @Description: Hash操作
 * 
 * @author dingjianhua
 * @date 2017年4月19日 下午3:13:42
 
 */
public interface HashOperations {
    /** 
     * 返回哈希表 key 中给定域 field 的值。 如果哈希表 key 存在，同时设置这个 key 的生存时间 
     * @param key key 
     * @param field 域 
     * @param expire 生命周期，单位为秒 
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。 
     */  
    public String hashGet(final String key, final String field, final int expire);
    /** 
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。同时设置这个 key 的生存时间 
     * @param key key 
     * @param hash field-value的map 
     * @param expire 生命周期，单位为秒 
     * @return 如果命令执行成功，返回 OK 。当 key 不是哈希表(hash)类型时，返回一个错误。 
     */  
    public String hashMultipleSet(final String key, final Map<String, String> hash, final int expire);
    /** 
     * 返回哈希表 key 中，一个或多个给定域的值。如果给定的域不存在于哈希表，那么返回一个 nil 值。 
     * 同时设置这个 key 的生存时间 
     * @param key key 
     * @param fields field的数组 
     * @param expire 生命周期，单位为秒 
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。 
     */  
    public List<String> hashMultipleGet(final String key, final int expire, final String... fields);
    /** 
     * 批量的{@link #hashMultipleSet(String, Map)}，在管道中执行 
     * @param pairs 多个hash的多个field 
     * @return 操作状态的集合 
     */  
    public List<Object> batchHashMultipleSet(final List<Pair<String, Map<String, String>>> pairs);
    /** 
     * 批量的{@link #hashMultipleSet(String, Map)}，在管道中执行 
     * @param data Map<String, Map<String, String>>格式的数据 
     * @return 操作状态的集合 
     */  
    public List<Object> batchHashMultipleSet(final Map<String, Map<String, String>> data);
	/**
	 * 
	 * @Title: batchHashGetAll
	 * @Description: 批量获取Map
	 * @param keys
	 * @return 
	 * @return List<Map<String,String>> 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月19日 下午3:13:53
	 
	 */
    public List<Map<String, String>> batchHashGetAll(final String... keys);
	/**
	 * 查看哈希表 key 中，给定域 field 是否存在。<br/>
	 * redis> HEXISTS dingjianhua dingjianhua<br/>
	 * (integer) 0<br/>
	 * redis> HSET dingjianhua dingjianhua nokia-1110<br/>
	 * (integer) 1<br/>
	 * redis> HEXISTS phone dingjianhua<br/>
	 * (integer) 1
	 * @param key
	 * @param field
	 * @return 
	 * Boolean 如果哈希表含有给定域，返回 1 。如果哈希表不含有给定域，或 key 不存在，返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:32:15
	 */
	public  Boolean hexists(String key, String field);

	/**
	 * 
	 * @Title: hexists
	 * @Description:查看哈希表 key 中，给定域 field 是否存在。
	 * @param key
	 * @param field
	 * @return 
	 * @return Boolean 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月19日 下午3:14:03
	 
	 */
	public  Boolean hexists(byte[] key, byte[] field);

	/**
	 * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。 <br/>
	 * redis> HGETALL abbr<br/>
	 * 1) "a"<br/>
	 * 2) "apple"<br/>
	 * 3) "b"<br/>
	 * 4) "banana"<br/>
	 * 5) "c"<br/>
	 * 6) "cat"<br/>
	 * 7) "d"<br/>
	 * 8) "dog"<br/>
	 * # 删除单个域<br/>
	 * redis> HDEL abbr a<br/>
	 * (integer) 1<br/>
	 * # 删除不存在的域<br/>
	 * redis> HDEL abbr not-exists-field<br/>
	 * (integer) 0<br/>
	 * # 删除多个域<br/>
	 * redis> HDEL abbr b c<br/>
	 * (integer) 2<br/>
	 * redis> HGETALL abbr<br/>
	 * 1) "d"<br/>
	 * 2) "dog"
	 * @param key
	 * @param field
	 * @return 
	 * Long  被成功移除的域的数量，不包括被忽略的域。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:33:37
	 */
	public  Long hdel(String key, String field);

	/**
	 *删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。 <br/>
	 * @param key
	 * @param field
	 * @return 
	 * Long 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:30:36
	 */
	public  Long hdel(byte[] key, byte[] field);

	/**
	 * 返回哈希表 key 中域的数量。<br/>
	 * redis> HSET db redis redis.com<br/>
	 * (integer) 1<br/>
	 * redis> HSET db mysql mysql.com<br/>
	 * (integer) 1<br/>
	 * redis> HLEN db<br/>
	 * (integer) 2<br/>
	 * redis> HSET db mongodb mongodb.org<br/>
	 * (integer) 1<br/>
	 * redis> HLEN db<br/>
	 * (integer) 3
	 * @param key
	 * @return 
	 * Long 哈希表中域的数量。当 key 不存在时，返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:35:07
	 */
	public  Long hlen(String key);

	/**
	 * 返回哈希表 key 中域的数量。<br/>
	 * @Title: hlen
	 * @param key
	 * @return 
	 * Long 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:29:27
	 */
	public  Long hlen(byte[] key);

	/**
	 * 返回哈希表 key 中的所有域。 
	 * # 哈希表非空<br/>
	 * redis> HMSET website google www.google.com yahoo www.yahoo.com<br/>
	 * OK<br/>
	 * redis> HKEYS website<br/>
	 * 1) "google"<br/>
	 * 2) "yahoo"<br/>
	 * # 空哈希表/key不存在<br/>
	 * redis> EXISTS fake_key<br/>
	 * (integer) 0<br/>
	 * redis> HKEYS fake_key<br/>
	 * (empty list or set)
	 * @param key
	 * @return 
	 * Set<String> 一个包含哈希表中所有域的表。当 key 不存在时，返回一个空表。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:37:39
	 */
	public  Set<String> hkeys(String key);

	/**
	 * 返回哈希表 key 中的所有域。 
	 * # 哈希表非空<br/>
	 * @param key
	 * @return 
	 * Set<byte[]> 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:24:25
	 */
	public  Set<byte[]> hkeys(byte[] key);

	/**
	 * 返回哈希表 key 中所有域的值。
	 * # 非空哈希表
	 * redis> HMSET website google www.google.com yahoo www.yahoo.com<br/>
	 * OK<br/>
	 * redis> HVALS website<br/>
	 * 1) "www.google.com"<br/>
	 * 2) "www.yahoo.com"<br/>
	 * # 空哈希表/不存在的key<br/>
	 * redis> EXISTS not_exists<br/>
	 * (integer) 0<br/>
	 * redis> HVALS not_exists<br/>
	 * (empty list or set)
	 * @param key
	 * @return 
	 * List<String> 一个包含哈希表中所有值的表。当 key 不存在时，返回一个空表。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:38:23
	 */
	public  List<String> hvals(String key);

	/**
	 * 返回哈希表 key 中所有域的值。
	 * # 非空哈希表
	 * @param key
	 * @return 
	 * Collection<byte[]> 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:28:44
	 */
	public  Collection<byte[]> hvals(byte[] key);

	/**
	 * 返回哈希表 key 中，所有的域和值。在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。<br/>
	 * redis> HSET people jack "Jack Sparrow"<br/>
	 * (integer) 1<br/>
	 * redis> HSET people gump "Forrest Gump"<br/>
	 * (integer) 1<br/>
	 * redis> HGETALL people<br/>
	 * 1) "jack"          # 域<br/>
	 * 2) "Jack Sparrow"  # 值<br/>
	 * 3) "gump"<br/>
	 * 4) "Forrest Gump"
	 * @param key
	 * @return 
	 * Map<String,String>  以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:39:23
	 */
	public  Map<String, String> hgetAll(String key);
	
    /** 
     * 返回哈希表 key 中，所有的域和值。在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。 
     * 同时设置这个 key 的生存时间 
     * @param key key 
     * @param expire 生命周期，单位为秒 
     * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。 
     */  
    public Map<String, String> hashGetAll(final String key, final int expire); 
    
    /** 
     * 返回哈希表 key 中，所有的域和值。在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
     * <br/>通道查询 
     * @param key key  
     * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。 
     */  
    public Map<String, String> hashGetAll(final String key); 
	/**
	 * 返回哈希表 key 中，所有的域和值。在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。<br/>
	 * @param key
	 * @return 
	 * Map<byte[],byte[]> 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:26:21
	 */
	public  Map<byte[], byte[]> hgetAll(byte[] key);

	/**
	 * HSET key field value
	 * 将哈希表 key 中的域 field 的值设为 value 。
	 * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
	 * 如果域 field 已经存在于哈希表中，旧值将被覆盖。<br/>
	 * redis> HSET website google "www.g.cn"       # 设置一个新域<br/>
	 * (integer) 1<br/>
	 * redis> HSET website google "www.google.com" # 覆盖一个旧域<br/>
	 * (integer) 0
	 * @param key
	 * @param field
	 * @param value
	 * @return 
	 * Long 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:40:29
	 */
	public  Long hset(String key, String field, String value);

	/**
	 * HGET key field
	 * 返回哈希表 key 中给定域 field 的值。<br/>
	 * # 域存在<br/>
	 * redis> HSET site redis redis.com<br/>
	 * (integer) 1<br/>
	 * redis> HGET site redis<br/>
	 * "redis.com"<br/>
	 * # 域不存在<br/>
	 * redis> HGET site mysql<br/>
	 * (nil)
	 * @param key
	 * @param field
	 * @return 
	 * String 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:42:06
	 */
	public  String hget(String key, String field);

	/**
	 * HSETNX key field value<br/>
	 * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。<br/>
	 * 若域 field 已经存在，该操作无效。<br/>
	 * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。<br/>
	 * redis> HSETNX nosql key-value-store redis<br/>
	 * (integer) 1<br/>
	 * redis> HSETNX nosql key-value-store redis       # 操作无效，域 key-value-store 已存在<br/>
	 * (integer) 0
	 * @param key
	 * @param field
	 * @param value
	 * @return 
	 * Long 设置成功，返回 1 。如果给定域已经存在且没有操作被执行，返回 0 。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:42:45
	 */
	public  Long hsetnx(String key, String field, String value);

	/**
	 * HMSET key field value [field value ...]<br/>
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。<br/>
	 * 此命令会覆盖哈希表中已存在的域。<br/>
	 * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。<br/>
	 * redis> HMSET website google www.google.com yahoo www.yahoo.com<br/>
	 * OK<br/>
	 * redis> HGET website google<br/>
	 * "www.google.com"<br/>
	 * redis> HGET website yahoo<br/>
	 * "www.yahoo.com"
	 * @param key
	 * @param hash
	 * @return 
	 * String 如果命令执行成功，返回 OK 。当 key 不是哈希表(hash)类型时，返回一个错误。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:44:44
	 */
	public  String hmset(String key, Map<String, String> hash);

	/**
	 * HMSET key field value [field value ...]<br/>
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。<br/>
	 * 此命令会覆盖哈希表中已存在的域。<br/>
	 * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。<br/>
	 * redis> HMSET website google www.google.com yahoo www.yahoo.com<br/>
	 * @param key
	 * @param hash
	 * @return 
	 * String 
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:34:32
	 */
	public  String hmset(byte[] key, Map<byte[], byte[]> hash);

	/**
	 * HMGET key field [field ...]
	 * 返回哈希表 key 中，一个或多个给定域的值。
	 * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
	 * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。<br/>
	 * Note:redis> HMSET pet dog "doudou" cat "nounou"    # 一次设置多个域<br/>
	 * OK<br/>
	 * redis> HMGET pet dog cat fake_pet             # 返回值的顺序和传入参数的顺序一样<br/>
	 * 1) "doudou"<br/>
	 * 2) "nounou"<br/>
	 * 3) (nil)                                      # 不存在的域返回nil值
	 * @param key
	 * @param fields
	 * @return 
	 * List<String> 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
	 * @author dingjianhua
	 * @date 2014-12-5 下午3:45:34
	 */
	public  List<String> hmget(String key, String... fields);

	/**
	 * HMGET key field [field ...]
	 * 返回哈希表 key 中，一个或多个给定域的值。
	 * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
	 * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。<br/>
	 * Note:redis> HMSET pet dog "doudou" cat "nounou"    # 一次设置多个域<br/>
	 * @param key
	 * @param fields
	 * @return 
	 * List<byte[]> 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:33:43
	 */
	public  List<byte[]> hmget(byte[] key, byte[]... fields);

	/**
	 * HINCRBY key field increment<br/>
	 * 为哈希表 key 中的域 field 的值加上增量 increment 。<br/>
	 * 增量也可以为负数，相当于对给定域进行减法操作。<br/>
	 * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。<br/>
	 * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。<br/>
	 * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。<br/>
	 * 本操作的值被限制在 64 位(bit)有符号数字表示之内。<br/>
	 * # increment 为正数<br/>
	 * redis> HEXISTS counter page_view    # 对空域进行设置<br/>
	 * (integer) 0<br/>
	 * redis> HINCRBY counter page_view 200<br/>
	 * (integer) 200<br/>
	 * redis> HGET counter page_view<br/>
	 * "200"<br/>
	 * # increment 为负数<br/>
	 * redis> HGET counter page_view<br/>
	 * "200"<br/>
	 * redis> HINCRBY counter page_view -50<br/>
	 * (integer) 150<br/>
	 * redis> HGET counter page_view<br/>
	 * "150"<br/>
	 * # 尝试对字符串值的域执行HINCRBY命令<br/>
	 * redis> HSET myhash string hello,world       # 设定一个字符串值<br/>
	 * (integer) 1<br/>
	 * redis> HGET myhash string<br/>
	 * "hello,world"<br/>
	 * redis> HINCRBY myhash string 1              # 命令执行失败，错误。<br/>
	 * (error) ERR hash value is not an integer<br/>
	 * redis> HGET myhash string                   # 原值不变<br/>
	 * "hello,world"
	 * @param key
	 * @param field
	 * @param value
	 * @return 
	 * Long 
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:07:47
	 */
	public  Long hincrBy(String key, String field, long value);

	/**
	 * HINCRBY key field increment<br/>
	 * 为哈希表 key 中的域 field 的值加上增量 increment 。<br/>
	 * 增量也可以为负数，相当于对给定域进行减法操作。<br/>
	 * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。<br/>
	 * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。<br/>
	 * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。<br/>
	 * 本操作的值被限制在 64 位(bit)有符号数字表示之内。<br/>
	 * @param key
	 * @param field
	 * @param value
	 * @return 
	 * Long 
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:32:42
	 */
	public  Long hincrBy(byte[] key, byte[] field, long value);

	/**
	 * HSET key field value<br/>
	 * 将哈希表 key 中的域 field 的值设为 value 。<br/>
	 * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。<br/>
	 * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
	 * @param key
	 * @param field
	 * @param value
	 * @return 
	 * Long 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:12:39
	 */
	public  Long hset(byte[] key, byte[] field, byte[] value);

	/**
	 * HGET key field
	 * 返回哈希表 key 中给定域 field 的值。<br/>
	 * # 域存在<br/>
	 * redis> HSET site redis redis.com<br/>
	 * (integer) 1<br/>
	 * redis> HGET site redis<br/>
	 * "redis.com"<br/>
	 * # 域不存在<br/>
	 * redis> HGET site mysql<br/>
	 * (nil)
	 * @param key
	 * @param field
	 * @return 
	 * byte[] 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-5 下午4:17:06
	 */
	public  byte[] hget(byte[] key, byte[] field);

	/**
	 * 
	 * HSETNX key field value<br/>
	 * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。<br/>
	 * 若域 field 已经存在，该操作无效。<br/>
	 * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。<br/>
	 * redis> HSETNX nosql key-value-store redis<br/>
	 * (integer) 1<br/>
	 * redis> HSETNX nosql key-value-store redis       # 操作无效，域 key-value-store 已存在<br/>
	 * (integer) 0
	 * @param key
	 * @param field
	 * @param value
	 * @return 
	 * Long 设置成功，返回 1 。如果给定域已经存在且没有操作被执行，返回 0 。
	 * @date 2014-12-5 下午4:18:38
	 */
	public  Long hsetnx(byte[] key, byte[] field, byte[] value);

}