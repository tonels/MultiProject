package com.tonels.common;

import lombok.Data;

@Data
public class UserCreatedEvent {
    User user;

    public UserCreatedEvent(User user) {
        this.user = user;
    }
}
