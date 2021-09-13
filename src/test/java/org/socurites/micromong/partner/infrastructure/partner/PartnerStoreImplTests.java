package org.socurites.micromong.partner.infrastructure.partner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.socurites.micromong.partner.domain.partner.Partner;
import org.socurites.micromong.partner.domain.partner.PartnerStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PartnerStoreImplTests {
    @Autowired
    private PartnerStore partnerStore;

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void storeSuccess() {
        final String partnerName = "test partner";
        final String businessNo = "111-11-1111";
        final String email = "test@kmong.com";

        Partner initPartner = Partner.builder()
                .partnerName(partnerName)
                .businessNo(businessNo)
                .email(email)
                .build();

        Partner savedPartner = partnerStore.store(initPartner);

        Partner foundPartner = partnerRepository.findById(savedPartner.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("No partners for %d", savedPartner.getId())));

        assertThat(partnerName).isEqualTo(foundPartner.getPartnerName());
        assertThat(businessNo).isEqualTo(foundPartner.getBusinessNo());
        assertThat(email).isEqualTo(foundPartner.getEmail());
        assertThat(foundPartner.getPartnerToken()).isNotEmpty();

        partnerRepository.deleteById(savedPartner.getId());

        assertThat(partnerRepository.findById(savedPartner.getId()).isEmpty()).isTrue();
    }
}
