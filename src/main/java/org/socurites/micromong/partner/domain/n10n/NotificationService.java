package org.socurites.micromong.partner.domain.n10n;

public interface NotificationService {
    void sendEmail(String email, String title, String body);
    void sendKakao(String phoneNo, String body);
    void sendSms(String phoneNo, String body);
}
