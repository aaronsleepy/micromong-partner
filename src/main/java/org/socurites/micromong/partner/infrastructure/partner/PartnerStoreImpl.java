package org.socurites.micromong.partner.infrastructure.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.socurites.micromong.partner.common.exception.InvalidParamException;
import org.socurites.micromong.partner.domain.partner.Partner;
import org.socurites.micromong.partner.domain.partner.PartnerStore;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerStoreImpl implements PartnerStore {
    private final PartnerRepository partnerRepository;

    @Override
    public Partner store(Partner initPartner) {
        if (StringUtils.isEmpty(initPartner.getPartnerToken())) throw new InvalidParamException("Empty partnerToken");
        if (StringUtils.isEmpty(initPartner.getPartnerName())) throw new InvalidParamException("Empty partnerName");
        if (StringUtils.isEmpty(initPartner.getBusinessNo())) throw new InvalidParamException("Empty businessNo");
        if (StringUtils.isEmpty(initPartner.getEmail())) throw new InvalidParamException("Empty email");
        if (null == initPartner.getStatus()) throw new InvalidParamException("Empty status");

        return partnerRepository.save(initPartner);
    }
}
