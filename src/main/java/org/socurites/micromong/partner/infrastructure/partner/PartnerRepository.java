package org.socurites.micromong.partner.infrastructure.partner;

import org.socurites.micromong.partner.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
