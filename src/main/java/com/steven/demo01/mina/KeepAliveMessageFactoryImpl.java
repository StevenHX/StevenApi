package com.steven.demo01.mina;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 被动型心跳机制，服务器在接受到客户端连接以后被动接受心跳请求，当在规定时间内没有收到客户端心跳请求时将客户端连接关闭
 */
@Slf4j
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    @Override
    public boolean isRequest(IoSession session, Object message) {
        log.info("请求心跳包信息: " + message);
        return message.equals("HEART_BEAT_REQUEST");
    }

    @Override
    public boolean isResponse(IoSession session, Object message) {
        return false;
    }

    @Override
    public Object getRequest(IoSession session) {
        return null;
    }

    @Override
    public Object getResponse(IoSession session, Object request) {
        log.info("响应预设信息: HEART_BEAT_RESPONSE");
        return "HEART_BEAT_RESPONSE";
    }

}