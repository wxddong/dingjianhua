package junit.redisStudy;

import org.junit.Test;

import com.dingjh.redis.connection.RedisDataSource;
import com.dingjh.redis.connection.RedisDataSourceImpl;
import com.dingjh.redis.template.RedisClientTemplate;

public class RedisTest {
    private RedisClientTemplate redisClientTemplate = new RedisClientTemplate();
    private RedisDataSource redisDataSource = new RedisDataSourceImpl();
    
    @Test
    public void testStudyRedis(){
        redisClientTemplate.opsForString().set("hello", "world");
        
        redisDataSource.getClient().sadd("hello1", "world2");
        System.out.println("完成");
    }

}
