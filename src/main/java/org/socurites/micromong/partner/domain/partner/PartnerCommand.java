package org.socurites.micromong.partner.domain.partner;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PartnerCommand {
    private String partnerName;
    private String businessNo;
    private String email;

    // Interface > Facade
    @Builder
    public PartnerCommand(String partnerName, String businessNo, String email) {
        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
    }

    // Service > Store
    public Partner toEntity() {
        return Partner.builder()
                .partnerName(partnerName)
                .businessNo(businessNo)
                .email(email)
                .build();
    }
}
