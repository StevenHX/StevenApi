package com.steven.demo01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class Demo01ApplicationTests {
    @Autowired
    private JavaMailSender javaMailSender;
    @Test
    void contextLoads() {
    }

    @Test
    void testSendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("datongganjin@163.com");
        msg.setBcc();
        msg.setTo("1812247149@qq.com");
        msg.setSubject("StevenApi投稿");
        msg.setText("您在"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"登录该系统");
        try {
            javaMailSender.send(msg);
        } catch (MailException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
