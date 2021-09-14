package org.socurites.micromong.partner.interfaces.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.socurites.micromong.partner.application.partner.PartnerFacade;
import org.socurites.micromong.partner.common.response.CommonResponse;
import org.socurites.micromong.partner.domain.partner.PartnerCommand;
import org.socurites.micromong.partner.domain.partner.PartnerInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/partners")
public class PartnerApiController {
    private final PartnerFacade partnerFacade;

    @PostMapping
    public CommonResponse registerPartner(
            @RequestBody @Valid PartnerDto.RegisterRequest request
    ) {
        PartnerCommand command = request.toCommand();
        PartnerInfo partnerInfo = partnerFacade.registerPartner(command);

//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new PartnerDto.RegisterResponse(partnerInfo));

        return CommonResponse.success(new PartnerDto.RegisterResponse(partnerInfo));
    }
}
