package org.socurites.micromong.partner.infrastructure.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.socurites.micromong.partner.common.exception.EntityNotFoundException;
import org.socurites.micromong.partner.domain.partner.Partner;
import org.socurites.micromong.partner.domain.partner.PartnerReader;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerReaderImpl implements PartnerReader {
    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(String partnerToken) {
        return partnerRepository.findByPartnerToken(partnerToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
