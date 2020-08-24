
package com.neoframework.common.auth.model;

import java.io.Serializable;

/**
 * 二维码登录记录 model .
 */
public class QrCodeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 前端生成二维码对应的 URL , 这个 URL 需要登录用户才能访问 */
    private String scanUrl;

    // /** 上面 URL 对应的 uuid */
    // private String uuid;

    /** 前端用来定时轮询是否扫描完成的 URL , 这个 URL “不”需要登录即可访问 */
    private String checkUrl;

    /** 过期间隔 */
    private Integer expiredMinute;

    public String getScanUrl() {
        return scanUrl;
    }

    public void setScanUrl(String scanUrl) {
        this.scanUrl = scanUrl;
    }

    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public Integer getExpiredMinute() {
        return expiredMinute;
    }

    public void setExpiredMinute(Integer expiredMinute) {
        this.expiredMinute = expiredMinute;
    }
}
