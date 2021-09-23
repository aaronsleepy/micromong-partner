package org.socurites.micromong.partner.infrastructure.n10;

import lombok.Builder;
import lombok.Getter;

public class NotificationDto {
    @Builder
    @Getter
    public static class SendRequest {
        private String type;
        private String sender;
        private String receiver;
        private String body;
        private String title;
    }
}
