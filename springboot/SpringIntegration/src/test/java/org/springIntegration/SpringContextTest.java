package org.springIntegration;

import com.tonels.springIntegration.si.security.MessageConsumer;
import com.tonels.springIntegration.si.security.SecuredDirectChannel;
import com.tonels.springIntegration.si.security.SecurityConfig;
import com.tonels.springIntegration.si.security.SecurityPubSubChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SecurityConfig.class, SecuredDirectChannel.class, SecurityPubSubChannel.class,
		MessageConsumer.class })
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
