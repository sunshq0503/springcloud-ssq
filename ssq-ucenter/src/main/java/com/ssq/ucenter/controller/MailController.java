package com.ssq.ucenter.controller;

import com.ssq.ucenter.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


public class MailController {
    @Autowired
    MailService mailService;

    @GetMapping("/sendSimpleMail")
    public void sendSimpleMail() {
        mailService.sendSimpleMail("653310596@qq.com","测试发送简单邮件"," Hello, this is simple mail!");
    }

    @GetMapping("/sendHtmlMail")
    public void sendHtmlMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("653310596@qq.com","测试发送html邮件",content);
    }

    @GetMapping("/sendAttachmentsMail")
    public void sendAttachmentsMail() {
        String filePath="C:\\Resource\\1.pdf";
        mailService.sendAttachmentsMail("653310596@qq.com", "主题: 您有一封邮件待查看", "附上example文件,请查收! ", filePath);
    }

    @GetMapping("/sendInlineResourceMail")
    public void sendInlineResourceMail() {
        String resId = "1.jpg";
        String content="<html>\n" +
                "<center><body>\n" +
                "这是一封有图片的邮件哦! O(∩_∩)O <img src=\'cid:" + resId + "\' >\n" +
                "</body></center>\n" +
                "</html>";
        String imgPath = "C:\\Resource\\"+resId;

        mailService.sendInlineResourceMail("653310596@qq.com", "主题: 您有一封邮件待查看", content, imgPath, resId);
    }
}
