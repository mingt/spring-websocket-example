
package com.neoframework.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by think on 2018-08-16.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BaseApiException {

    /**
     * Instantiates a new Unauthorized exception.
     */
    public UnauthorizedException() {
        super();
    }

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param message the message
     */
    public UnauthorizedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param cause the cause
     */
    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     */
    public UnauthorizedException(Integer bizStatus, String message) {
        super(bizStatus, message);
    }

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     * @param cause the cause
     */
    public UnauthorizedException(Integer bizStatus, String message, Throwable cause) {
        super(bizStatus, message, cause);
    }

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param bizStatus the biz status
     * @param cause the cause
     */
    public UnauthorizedException(Integer bizStatus, Throwable cause) {
        super(bizStatus, cause);
    }
}
