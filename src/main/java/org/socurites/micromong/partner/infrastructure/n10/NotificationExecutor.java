package org.socurites.micromong.partner.infrastructure.n10;

import lombok.extern.slf4j.Slf4j;
import org.socurites.micromong.partner.domain.n10n.NotificationService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationExecutor implements NotificationService {
    @Override
    public void sendEmail(String email, String title, String body) {
        log.info(String.format("sendEmail to %s", email));
    }

    @Override
    public void sendKakao(String phoneNo, String body) {
        log.info(String.format("sendKakao to %s", phoneNo));
    }

    @Override
    public void sendSms(String phoneNo, String body) {
        log.info(String.format("sendSms to %s", phoneNo));
    }
}
