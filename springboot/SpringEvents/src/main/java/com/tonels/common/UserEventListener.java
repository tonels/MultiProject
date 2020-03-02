package com.tonels.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class UserEventListener {

    @Async
    @TransactionalEventListener
    public void processUserCreatedEvent(UserCreatedEvent event) {
        User user = event.getUser();
        log.info("用户事件处理..", user.toString());
    }

}
