package org.socurites.micromong.partner.application.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.socurites.micromong.partner.domain.n10n.NotificationService;
import org.socurites.micromong.partner.domain.partner.PartnerCommand;
import org.socurites.micromong.partner.domain.partner.PartnerInfo;
import org.socurites.micromong.partner.domain.partner.PartnerService;
import org.socurites.micromong.partner.infrastructure.n10.NotificationDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerFacade {
    private final PartnerService partnerService;
    private final NotificationService notificationService;

    public PartnerInfo registerPartner(PartnerCommand command) {
        PartnerInfo partnerInfo = partnerService.registerPartner(command);

        notificationService.sendEmail(buildRegisterSuccessEmail(partnerInfo.getEmail()));

        // FIXME: 파트너의 전화번호를 가져왔다고 가정
        notificationService.sendKakao(buildRegisterSuccessKakao("010-1111-1234"));

        return partnerInfo;
    }

    // TODO: 이메일 템플릿에 맞게 메시지 구성하기
    private NotificationDto.SendRequest buildRegisterSuccessEmail(String receiver) {
        return NotificationDto.SendRequest.builder()
                .type("EMAIL")
                .sender("kmong-official@kmong.com")
                .receiver(receiver)
                .body("Thank you for joining us")
                .title("Thank you title")
                .build();
    }

    // TODO: 카카오 템플릿에 맞게 메시지 구성하기
    private NotificationDto.SendRequest buildRegisterSuccessKakao(String receiver) {
        return NotificationDto.SendRequest.builder()
                .type("KAKAO")
                .sender("070-111-1111")
                .receiver(receiver)
                .body("Thank you for joining us")
                .build();
    }
}