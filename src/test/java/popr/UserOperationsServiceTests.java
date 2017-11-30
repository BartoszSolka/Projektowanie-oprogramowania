package popr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Zone;
import popr.service.UserOperationsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserOperationsServiceTests {

    @Autowired
    private UserOperationsService userOperationsService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void canCreateServiceOrder() throws Exception {
        userOperationsService.createServiceOrder("test", "koszykowa 1","01-111", 1L, 1L);
    }
}
