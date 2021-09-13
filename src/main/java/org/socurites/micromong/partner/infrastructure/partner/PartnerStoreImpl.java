package org.socurites.micromong.partner.infrastructure.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.socurites.micromong.partner.domain.partner.Partner;
import org.socurites.micromong.partner.domain.partner.PartnerStore;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerStoreImpl implements PartnerStore {
    private final PartnerRepository partnerRepository;

    @Override
    public Partner store(Partner initPartner) {
        if (StringUtils.isEmpty(initPartner.getPartnerToken())) throw new InvalidParameterException("Empty partnerToken");
        if (StringUtils.isEmpty(initPartner.getPartnerName())) throw new InvalidParameterException("Empty partnerName");
        if (StringUtils.isEmpty(initPartner.getBusinessNo())) throw new InvalidParameterException("Empty businessNo");
        if (StringUtils.isEmpty(initPartner.getEmail())) throw new InvalidParameterException("Empty email");
        if (null == initPartner.getStatus()) throw new InvalidParameterException("Empty status");

        return partnerRepository.save(initPartner);
    }
}
