package org.socurites.micromong.partner.domain.partner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.UUID;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "partners")
public class Partner extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"),
        DISABLE("비활성화")
        ;
        private final String description;
    }

    @Builder
    public Partner(String partnerName, String businessNo, String email) {
        if (StringUtils.isEmpty(partnerName)) throw new InvalidParameterException("empty partnerName");
        if (StringUtils.isEmpty(businessNo)) throw new InvalidParameterException("empty businessNo");
        if (StringUtils.isEmpty(email)) throw new InvalidParameterException("empty email");

        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
        this.status = Status.ENABLE;

        this.partnerToken = UUID.randomUUID().toString().replace("-", "");
    }

    public void enable() { this.status = Status.ENABLE; }
    public void disable() { this.status = Status.DISABLE; }
}
