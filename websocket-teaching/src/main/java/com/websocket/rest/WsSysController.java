
package com.websocket.rest;

import com.websocket.config.StompProperties;
import com.websocket.config.StompPropertiesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ws 系统相关公共接口.
 */
@RestController
@RequestMapping("wssys")
public class WsSysController {

    private static final Logger logger = LoggerFactory.getLogger(WsSysController.class);

    @Autowired
    private StompProperties stompProperties;

    /**
     * 获取版本信息.
     *
     * @return
     */
    @GetMapping("ifExternalBroker")
    public StompPropertiesDto ifExternalBroker() {
        StompPropertiesDto result = new StompPropertiesDto();
        if (stompProperties != null) {
            result.setExternalBroker(stompProperties.getExternalBroker());
            result.setIfExternalBroker(stompProperties.ifExternalBroker());
        } else {
            result.setIfExternalBroker(false);
        }
        return result;
    }
}
