package junit.logUtil;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.dingjh.log.logUtil.LogManager;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class LogUtilTest extends AbstractJUnit4SpringContextTests  {
    @Test
    public void testLogUtil(){
        LogManager.log(LogManager.LEVEL_INFO,
                LogManager.LEVEL_INFO,this.getClass().getName(),"testLogUtil",
                "日志内容：成功成功，还可以 " );
        System.out.println("记录日志成功");
    }
}
