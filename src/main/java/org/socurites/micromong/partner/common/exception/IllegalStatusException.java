package org.socurites.micromong.partner.common.exception;


import org.socurites.micromong.partner.common.response.ErrorCode;

public class IllegalStatusException extends BaseException {

    public IllegalStatusException() {
        super(ErrorCode.COMMON_ILLEGAL_STATUS);
    }

    public IllegalStatusException(String message) {
        super(ErrorCode.COMMON_ILLEGAL_STATUS, message);
    }
}
