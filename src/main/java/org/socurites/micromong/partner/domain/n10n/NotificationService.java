package org.socurites.micromong.partner.domain.n10n;

import org.socurites.micromong.partner.infrastructure.n10.NotificationDto;

public interface NotificationService {
    void sendEmail(NotificationDto.SendRequest request);
    void sendKakao(NotificationDto.SendRequest request);
    void sendSms(NotificationDto.SendRequest request);
}
