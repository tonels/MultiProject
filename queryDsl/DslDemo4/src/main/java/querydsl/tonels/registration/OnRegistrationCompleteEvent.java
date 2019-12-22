package querydsl.tonels.registration;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import querydsl.tonels.persistence.model.User;

import java.util.Locale;

//@SuppressWarnings("serial")
@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale; // 国际化配置
    private final User user;

    public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

}
