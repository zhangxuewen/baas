package com.wxbaas.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class AbstractHandler implements WxMpMessageHandler {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
