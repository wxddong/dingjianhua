package com.dingjh.redis.config;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.dingjh.config.SystemConfig;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * @ClassName: StudyShardedJedisPool
 * @Description: 连接池初始化
 * 
 * @author dingjianhua
 * @date 2017年4月19日 下午3:10:28
 
 */
public class StudyShardedJedisPool {
	private StudyShardedJedisPool(){
	}
	private static ShardedJedisPool pool;
	// 静态代码初始化池配置
	static {
		try {
			// 创建jedis池配置实例
			JedisPoolConfig config = initConfig();
			List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
			String host = SystemConfig.getConfig("redis_url");
			Set<String> hosts = init(host);
			for (String hs : hosts) {
				String[] values = hs.split(":");
				JedisShardInfo shard = new JedisShardInfo(values[0], Integer.valueOf(values[1]));
//				shard.setPassword("123456");
				if (values.length > 2) {
					shard.setPassword(values[2]);
				}
				shards.add(shard);
			}
			pool = new ShardedJedisPool(config, shards);
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * @Title: initConfig
	 * @Description: 初始化连接池参数
	 * @return 
	 * @return JedisPoolConfig 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月19日 下午3:10:39
	 
	 */
	private static JedisPoolConfig initConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置池配置项值
		config.setMaxTotal(Integer.valueOf(RedisConfig.getString("redis.pool.maxTotal")));
		config.setMaxIdle(Integer.valueOf(RedisConfig.getString("redis.pool.maxIdle")));
		config.setMinIdle(Integer.valueOf(RedisConfig.getString("redis.pool.minIdle")));
		config.setMaxWaitMillis(Long.valueOf(RedisConfig.getString("redis.pool.maxWaitMillis")));
		config.setTestOnBorrow(Boolean.valueOf(RedisConfig.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(RedisConfig.getString("redis.pool.testOnReturn")));
		config.setTestWhileIdle(Boolean.valueOf(RedisConfig.getString("redis.pool.testWhileIdle")));
		return config;
	}

	private static Set<String> init(String values) {
		if (values == null || "".equals(values.trim())) {
			throw new NullPointerException("redis host not found!");
		}
		Set<String> paramter = new HashSet<String>();
		String[] sentinelArray = values.split(",");
		for (String string : sentinelArray) {
			paramter.add(string);
		}
		return paramter;
	}

	/**
	 * 
	 * @Title: getShardedJedisPool
	 * @Description: 获取初始化完成的连接池
	 * @return 
	 * @return ShardedJedisPool 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月19日 下午3:10:47
	 
	 */
	public static ShardedJedisPool getShardedJedisPool() {
		return pool;
	}

}
