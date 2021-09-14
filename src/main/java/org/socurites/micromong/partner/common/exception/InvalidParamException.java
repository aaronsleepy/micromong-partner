package org.socurites.micromong.partner.common.exception;

import org.socurites.micromong.partner.common.response.ErrorCode;

public class InvalidParamException extends BaseException {

    public InvalidParamException() {
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public InvalidParamException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidParamException(String errorMsg) {
        super(ErrorCode.COMMON_INVALID_PARAMETER, errorMsg);
    }

    public InvalidParamException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
