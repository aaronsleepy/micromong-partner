package org.socurites.micromong.partner.interfaces.partner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.socurites.micromong.partner.domain.partner.Partner;
import org.socurites.micromong.partner.domain.partner.PartnerCommand;
import org.socurites.micromong.partner.domain.partner.PartnerInfo;

import javax.validation.constraints.NotEmpty;

public class PartnerDto {
    @Getter
    @Builder
    @ToString
    public static class RegisterRequest {
        @NotEmpty(message = "partnerName is required")
        private String partnerName;
        @NotEmpty(message = "businessNo is required")
        private String businessNo;
        @NotEmpty(message = "email is required")
        private String email;

        // DTO -> Command
        public PartnerCommand toCommand() {
            return PartnerCommand.builder()
                    .partnerName(partnerName)
                    .businessNo(businessNo)
                    .email(email)
                    .build();
        }
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class RegisterResponse {
        private String partnerToken;
        private String partnerName;
        private String businessNo;
        private String email;
        private Partner.Status status;

        // PartnerInfo -> DTO
        public RegisterResponse(PartnerInfo partnerInfo) {
            this.partnerToken = partnerInfo.getPartnerToken();
            this.partnerName = partnerInfo.getPartnerName();
            this.businessNo = partnerInfo.getBusinessNo();
            this.email = partnerInfo.getEmail();
            this.status = partnerInfo.getStatus();
        }
    }
}
