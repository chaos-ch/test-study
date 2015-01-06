import com.ch.test.ServiceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by he.chen on 14-10-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/service.xml" })
public class SpringTest {
    @Resource
    ServiceFactory serviceFactory;
    @Test
    public void test(){
        System.out.println(serviceFactory.getOtaService("kaiyuan").getPrice());
//       serviceFactory.setApplicationContext;
    }
}
