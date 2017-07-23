package com.dingjh.redis.core;

import java.util.List;
import java.util.Set;

public interface SetOperations {
	/**
	 * SADD key member [member ...]<br/>
	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。<br/>
	 * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。<br/>
	 * 当 key 不是集合类型时，返回一个错误。<br/>
	 * # 添加单个元素<br/>
	 * redis> SADD bbs "discuz.net"<br/>
	 * (integer) 1<br/>
	 * # 添加重复元素<br/>
	 * redis> SADD bbs "discuz.net"<br/>
	 * (integer) 0<br/>
	 * # 添加多个元素<br/>
	 * redis> SADD bbs "tianya.cn" "groups.google.com"<br/>
	 * (integer) 2<br/>
	 * redis> SMEMBERS bbs<br/>
	 * 1) "discuz.net"<br/>
	 * 2) "groups.google.com"<br/>
	 * 3) "tianya.cn"
	 * @param key
	 * @param member
	 * @return 
	 * Long 被添加到集合中的新元素的数量，不包括被忽略的元素
	 * @author dingjianhua
	 * @date 2014-12-5 下午6:06:17
	 */
	public Long sadd(final String key, final String member);

	public Long sadd(byte[] key, byte[] member);

	public Set<String> smembers(String key);

	public Boolean sismember(byte[] key, byte[] member);

	public Set<byte[]> smembers(byte[] key);

	public Long srem(String key, String member);

	public Long srem(byte[] key, byte[] member);

	public String spop(String key);

	public byte[] spop(byte[] key);

	public Long scard(String key);

	public Long scard(byte[] key);

	public String srandmember(String key);

	public List<String> srandmember(String key, int count);

	public byte[] srandmember(byte[] key);

}
