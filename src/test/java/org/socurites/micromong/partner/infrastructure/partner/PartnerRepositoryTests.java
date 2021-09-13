package org.socurites.micromong.partner.infrastructure.partner;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.socurites.micromong.partner.domain.partner.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PartnerRepositoryTests {
    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void findById() {
        final String partnerName = "test partner";
        final String businessNo = "111-11-1111";
        final String email = "test@kmong.com";

        Partner savedPartner = partnerRepository.save(Partner.builder()
                .partnerName(partnerName)
                .businessNo(businessNo)
                .email(email)
                .build());

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
