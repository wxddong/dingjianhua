package junit.activeMQ;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.dingjh.activeMQProducter.service.IStudyMessageService;

@ContextConfiguration(locations = { "/applicationContext.xml"})
public class ActiveMQTest  extends AbstractJUnit4SpringContextTests {
    @Autowired
    private IStudyMessageService studyMessageService;
    
    @Test
    public void testActvieMQ() {
        studyMessageService.sendNotice();
        System.out.println("发送完成！");
    }

}
