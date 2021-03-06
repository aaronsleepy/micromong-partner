package org.socurites.micromong.partner.interfaces.partner;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.socurites.micromong.partner.common.response.CommonResponse;
import org.socurites.micromong.partner.common.response.ErrorCode;
import org.socurites.micromong.partner.infrastructure.partner.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PartnerApiControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void registerPartnerSuccess() {
        final String requestUrl = String.format("http://localhost:%d/api/vi/partners", port);
        final String partnerName = "test partner";
        final String businessNo = "111-11-1111";
        final String email = "test@kmong.com";

        PartnerDto.RegisterRequest request = PartnerDto.RegisterRequest.builder()
                .partnerName(partnerName)
                .businessNo(businessNo)
                .email(email)
                .build();

        CommonResponse<PartnerDto.RegisterResponse> commonResponse = restTemplate.exchange(requestUrl,
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<CommonResponse<PartnerDto.RegisterResponse>>() {
                }).getBody();

        assertThat(commonResponse.getResult()).isEqualTo(CommonResponse.Result.SUCCESS);

        PartnerDto.RegisterResponse response = commonResponse.getData();
        assertThat(partnerName).isEqualTo(response.getPartnerName());
        assertThat(businessNo).isEqualTo(response.getBusinessNo());
        assertThat(email).isEqualTo(response.getEmail());
        assertThat(response.getPartnerToken()).isNotEmpty();

        // TODO: ????????? ????????? ?????? call ??????(????????? ??????). @Transactional??? WebEnviroment??? ???????????? ??????(????????? ??????????????? ?????????)
    }

    @Test
    public void registerPartnerFailure() {
        final String requestUrl = String.format("http://localhost:%d/api/vi/partners", port);
        final String partnerName = "test partner";
        final String businessNo = "111-11-1111";
        final String email = null;

        PartnerDto.RegisterRequest request = PartnerDto.RegisterRequest.builder()
                .partnerName(partnerName)
                .businessNo(businessNo)
                .email(email)
                .build();

        CommonResponse<PartnerDto.RegisterResponse> commonResponse = restTemplate.exchange(requestUrl,
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<CommonResponse<PartnerDto.RegisterResponse>>() {
                }).getBody();

        assertThat(commonResponse.getResult()).isEqualTo(CommonResponse.Result.FAIL);
        assertThat(commonResponse.getErrorCode()).isEqualTo(ErrorCode.COMMON_INVALID_PARAMETER.name());
    }
}
