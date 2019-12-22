package querydsl.tonels.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import querydsl.tonels.persistence.dao.PasswordResetTokenRepository;
import querydsl.tonels.persistence.dao.VerificationTokenRepository;

import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class TokensPurgeTask {

    @Autowired
    VerificationTokenRepository tokenRepository;

    @Autowired
    PasswordResetTokenRepository passwordTokenRepository;

    @Scheduled(cron = "${purge.cron.expression}")
    public void purgeExpired() {

        Date now = Date.from(Instant.now());

        passwordTokenRepository.deleteAllExpiredSince(now);
        tokenRepository.deleteAllExpiredSince(now);
    }
}
