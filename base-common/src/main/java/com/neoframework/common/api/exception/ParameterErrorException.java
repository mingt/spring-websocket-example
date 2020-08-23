
package com.neoframework.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by think on 2017-09-19.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParameterErrorException extends BaseApiException {

    /**
     * Instantiates a new Parameter error exception.
     */
    public ParameterErrorException() {
        super();
    }

    /**
     * Instantiates a new Parameter error exception.
     *
     * @param message the message
     */
    public ParameterErrorException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Parameter error exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ParameterErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Parameter error exception.
     *
     * @param cause the cause
     */
    public ParameterErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Parameter error exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     */
    public ParameterErrorException(Integer bizStatus, String message) {
        super(bizStatus, message);
    }

    /**
     * Instantiates a new Parameter error exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     * @param cause the cause
     */
    public ParameterErrorException(Integer bizStatus, String message, Throwable cause) {
        super(bizStatus, message, cause);
    }

    /**
     * Instantiates a new Parameter error exception.
     *
     * @param bizStatus the biz status
     * @param cause the cause
     */
    public ParameterErrorException(Integer bizStatus, Throwable cause) {
        super(bizStatus, cause);
    }
}
