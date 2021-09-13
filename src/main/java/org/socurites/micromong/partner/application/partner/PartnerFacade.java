package org.socurites.micromong.partner.application.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.socurites.micromong.partner.domain.n10n.NotificationService;
import org.socurites.micromong.partner.domain.partner.PartnerCommand;
import org.socurites.micromong.partner.domain.partner.PartnerInfo;
import org.socurites.micromong.partner.domain.partner.PartnerService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerFacade {
    private final PartnerService partnerService;
    private final NotificationService notificationService;

    public PartnerInfo registerPartner(PartnerCommand command) {
        PartnerInfo partnerInfo = partnerService.registerPartner(command);
        // TODO: 파트너 등록결과를 이메일로 안내하기
        notificationService.sendEmail(partnerInfo.getEmail(), "title?", "body?");
        return partnerInfo;
    }
}