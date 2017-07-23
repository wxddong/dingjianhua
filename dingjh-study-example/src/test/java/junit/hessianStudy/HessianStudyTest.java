package junit.hessianStudy;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.dingjh.example.hessianStudy.IHessianStudyService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class HessianStudyTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private IHessianStudyService hessianStudyService;

    @Test
    public void testHessian() {
        String param = "fffffff";
        String result = hessianStudyService.hessianStudyTest(param);
        System.out.println(result);
    }

    @SuppressWarnings("resource")
    @Test
    public void testHessian2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        IHessianStudyService hello = (IHessianStudyService) context.getBean("hessianStudyService");
        System.out.println(hello.hessianStudyTest("rrrrrr"));
    }
}
