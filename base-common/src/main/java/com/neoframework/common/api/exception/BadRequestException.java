
package com.neoframework.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by think on 2017-09-19.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseApiException {

    /**
     * Instantiates a new Bad request exception.
     */
    public BadRequestException() {
        super();
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message the message
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param cause the cause
     */
    public BadRequestException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     */
    public BadRequestException(Integer bizStatus, String message) {
        super(bizStatus, message);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     * @param cause the cause
     */
    public BadRequestException(Integer bizStatus, String message, Throwable cause) {
        super(bizStatus, message, cause);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param bizStatus the biz status
     * @param cause the cause
     */
    public BadRequestException(Integer bizStatus, Throwable cause) {
        super(bizStatus, cause);
    }
}
