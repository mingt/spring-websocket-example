
package com.neoframework.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by think on 2017-09-19.
 */
@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class NotImplementedException extends BaseApiException {

    /**
     * Instantiates a new Not implemented exception.
     */
    public NotImplementedException() {
        super();
    }

    /**
     * Instantiates a new Not implemented exception.
     *
     * @param message the message
     */
    public NotImplementedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Not implemented exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Not implemented exception.
     *
     * @param cause the cause
     */
    public NotImplementedException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Not implemented exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     */
    public NotImplementedException(Integer bizStatus, String message) {
        super(bizStatus, message);
    }

    /**
     * Instantiates a new Not implemented exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     * @param cause the cause
     */
    public NotImplementedException(Integer bizStatus, String message, Throwable cause) {
        super(bizStatus, message, cause);
    }

    /**
     * Instantiates a new Not implemented exception.
     *
     * @param bizStatus the biz status
     * @param cause the cause
     */
    public NotImplementedException(Integer bizStatus, Throwable cause) {
        super(bizStatus, cause);
    }
}
