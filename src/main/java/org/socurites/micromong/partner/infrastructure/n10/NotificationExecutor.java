package org.socurites.micromong.partner.infrastructure.n10;

import lombok.extern.slf4j.Slf4j;
import org.socurites.micromong.partner.common.response.CommonResponse;
import org.socurites.micromong.partner.domain.n10n.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class NotificationExecutor implements NotificationService {
    private final RestTemplate restTemplate;
    private final String notificationServiceBaseUrl;

    public NotificationExecutor(RestTemplate restTemplate,
        @Value("${service.notification.base-url}") String notificationServiceBaseUrl) {
        this.restTemplate = restTemplate;
        this.notificationServiceBaseUrl = notificationServiceBaseUrl;
    }

    @Override
    @Async
    public void sendEmail(NotificationDto.SendRequest request) {
        final String requestUrl = String.format("%s/api/v1/notifications", notificationServiceBaseUrl);
        log.info(String.format("sending Email to %s via %s", request.getReceiver(), requestUrl));

        CommonResponse<Void> commonResponse = restTemplate.exchange(requestUrl,
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<CommonResponse<Void>>() {
                }).getBody();

        log.info(String.format("[%s]sent Email to %s", commonResponse.getResult(), request.getReceiver()));
    }

    @Override
    @Async
    public void sendKakao(NotificationDto.SendRequest request) {
        final String requestUrl = String.format("%s/api/v1/notifications", notificationServiceBaseUrl);
        log.info(String.format("sending Kakao to %s via %s", request.getReceiver(), requestUrl));

        CommonResponse<Void> commonResponse = restTemplate.exchange(requestUrl,
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<CommonResponse<Void>>() {
                }).getBody();

        log.info(String.format("[%s]sent Kakao to %s", commonResponse.getResult(), request.getReceiver()));
    }

    @Override
    public void sendSms(NotificationDto.SendRequest request) {
        log.info(String.format("sendSms to %s", request.getReceiver()));
    }
}
