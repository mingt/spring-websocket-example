
package com.neoframework.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by think on 2017-09-19.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseApiException {

    /**
     * Instantiates a new Resource not found exception.
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param cause the cause
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     */
    public ResourceNotFoundException(Integer bizStatus, String message) {
        super(bizStatus, message);
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     * @param cause the cause
     */
    public ResourceNotFoundException(Integer bizStatus, String message, Throwable cause) {
        super(bizStatus, message, cause);
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param bizStatus the biz status
     * @param cause the cause
     */
    public ResourceNotFoundException(Integer bizStatus, Throwable cause) {
        super(bizStatus, cause);
    }
}
