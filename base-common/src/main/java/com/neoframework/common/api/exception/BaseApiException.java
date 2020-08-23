
package com.neoframework.common.api.exception;

/**
 * Created by think on 2017-09-19.
 */
public class BaseApiException extends RuntimeException {

    /**
     * The Biz status.
     */
    protected Integer bizStatus;

    /**
     * Instantiates a new Base api exception.
     */
    public BaseApiException() {
        super();
    }

    /**
     * Instantiates a new Base api exception.
     *
     * @param message the message
     */
    public BaseApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Base api exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public BaseApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Base api exception.
     *
     * @param cause the cause
     */
    public BaseApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Base api exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     */
    public BaseApiException(Integer bizStatus, String message) {
        super(message);
        this.bizStatus = bizStatus;
    }

    /**
     * Instantiates a new Base api exception.
     *
     * @param bizStatus the biz status
     * @param message the message
     * @param cause the cause
     */
    public BaseApiException(Integer bizStatus, String message, Throwable cause) {
        super(message, cause);
        this.bizStatus = bizStatus;
    }

    /**
     * Instantiates a new Base api exception.
     *
     * @param bizStatus the biz status
     * @param cause the cause
     */
    public BaseApiException(Integer bizStatus, Throwable cause) {
        super(cause);
        this.bizStatus = bizStatus;
    }

    /**
     * Gets biz status.
     *
     * @return the biz status
     */
    public Integer getBizStatus() {
        return bizStatus;
    }

    /**
     * Sets biz status.
     *
     * @param bizStatus the biz status
     */
    public void setBizStatus(Integer bizStatus) {
        this.bizStatus = bizStatus;
    }
}
