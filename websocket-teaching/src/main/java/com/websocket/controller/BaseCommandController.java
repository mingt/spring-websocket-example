
package com.websocket.controller;

import com.websocket.model.command.BaseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 包含命令的 ws 控制器基类.
 */
public class BaseCommandController<T extends BaseCommand> extends BaseWebSocketController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
