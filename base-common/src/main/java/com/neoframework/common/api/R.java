
package com.neoframework.common.api;

import java.io.Serializable;

/**
 * 响应信息主体.
 *
 * @param <T> the type parameter
 * @author ahming
 */
// @ToString
// @NoArgsConstructor
// @AllArgsConstructor
// @Accessors(chain = true)
// @ApiModel(value = "响应信息主体")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    public static final Integer FAIL = 1;

    // @Getter
    // @Setter
    // @ApiModelProperty(value = "返回标记：成功标记=0，失败标记=1")
    private int code;

    // @Getter
    // @Setter
    // @ApiModelProperty(value = "返回信息")
    private String msg;

    // @Getter
    // @Setter
    // @ApiModelProperty(value = "数据")
    private T data;

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Ok r.
     *
     * @param <T> the type parameter
     * @return the r
     */
    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, null);
    }

    /**
     * Ok r.
     *
     * @param <T> the type parameter
     * @param data the data
     * @return the r
     */
    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    /**
     * Ok r.
     *
     * @param <T> the type parameter
     * @param data the data
     * @param msg the msg
     * @return the r
     */
    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    /**
     * Failed r.
     *
     * @param <T> the type parameter
     * @return the r
     */
    public static <T> R<T> failed() {
        return restResult(null, FAIL, null);
    }

    /**
     * Failed r.
     *
     * @param <T> the type parameter
     * @param msg the msg
     * @return the r
     */
    public static <T> R<T> failed(String msg) {
        return restResult(null, FAIL, msg);
    }

    /**
     * Failed r.
     *
     * @param <T> the type parameter
     * @param data the data
     * @return the r
     */
    public static <T> R<T> failed(T data) {
        return restResult(data, FAIL, null);
    }

    /**
     * Failed r.
     *
     * @param <T> the type parameter
     * @param data the data
     * @param msg the msg
     * @return the r
     */
    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
