package com.steven.demo01.mina;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;

/**
 * mina 长链接配置
 */
@Slf4j
//@Configuration
public class MinaConfig {
    @Value("${mina.port1}")
    private int port1;
    @Value("${mina.host}")
    private String host;

    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

    @Bean
    public IoHandler ioHandler() {
        return new ServerHandler();
    }

    @Bean
    public IoAcceptor ioAcceptor() throws Exception {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", loggingFilter());
        acceptor.getFilterChain().addLast("codec",   // 使用自定义编码解码工厂类
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("GBK"))));//设置编码过滤器

        KeepAliveMessageFactoryImpl kamfi = new KeepAliveMessageFactoryImpl();
        KeepAliveFilter kaf = new KeepAliveFilter(kamfi, IdleStatus.BOTH_IDLE);
        // idle 事件回调
        kaf.setForwardEvent(true);
        // 心跳检测间隔时间
        kaf.setRequestInterval(60);
        // 心跳检测超时时间
        kaf.setRequestTimeout(30);
        acceptor.getFilterChain().addLast("heart", kaf);

        acceptor.setHandler(ioHandler());
        acceptor.getSessionConfig().setReadBufferSize(1024);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
        SocketAddress addresses = new InetSocketAddress(host, port1);
        acceptor.bind(addresses);
        log.info("=====================> Mina服务器在端口：" + port1 + "," + "已经启动!");
        return acceptor;
    }
}
