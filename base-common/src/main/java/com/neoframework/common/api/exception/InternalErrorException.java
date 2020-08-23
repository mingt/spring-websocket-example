
package com.neoframework.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by think on 2017-09-19.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends BaseApiException {

    /**
     * Instantiates a new Internal error exception.
     */
    public InternalErrorException() {
        super();
    }

    /**
     * Instantiates a new Internal error exception.
     *
     * @param message the message
     */
    public InternalErrorException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Internal error exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Internal error exception.
     *
     * @param cause the cause
     */
    public InternalErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Internal error exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     */
    public InternalErrorException(Integer bizStatus, String message) {
        super(bizStatus, message);
    }

    /**
     * Instantiates a new Internal error exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     * @param cause the cause
     */
    public InternalErrorException(Integer bizStatus, String message, Throwable cause) {
        super(bizStatus, message, cause);
    }

    /**
     * Instantiates a new Internal error exception.
     *
     * @param bizStatus the biz status
     * @param cause the cause
     */
    public InternalErrorException(Integer bizStatus, Throwable cause) {
        super(bizStatus, cause);
    }
}
