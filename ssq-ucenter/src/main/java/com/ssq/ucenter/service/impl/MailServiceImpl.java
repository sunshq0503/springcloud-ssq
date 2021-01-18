package com.ssq.ucenter.service.impl;

import com.ssq.ucenter.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送最简单的文本邮件
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        try{
            SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            mailSender.send(simpleMailMessage);
            log.info("邮件已发送成功!");
        } catch (Exception e) {
            log.error("发送邮件时发生异常!",e);
        }
    }

    /**
     * 发送html邮件
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //这里的true表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content);
            mailSender.send(message);
            log.info("邮件发送成功! ");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常!", e);
        }
    }

    /**
     * 发送带有附件的邮件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            mimeMessageHelper.addAttachment(fileName,fileSystemResource);

            mailSender.send(message);
            log.info("邮件已经发送成功! ");
        } catch (MessagingException  e) {
            log.error("发送邮件时发生异常!", e);
        }
    }

    /**
     * 发送邮件中带有图片的邮件
     */
    public void sendInlineResourceMail(String to, String subject, String content, String resPath, String resId){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper Helper = new MimeMessageHelper(message, true);
            Helper.setFrom(from);
            Helper.setTo(to);
            Helper.setSubject(subject);
            Helper.setText(content,true);

            FileSystemResource resource = new FileSystemResource(new File(resPath));
            Helper.addAttachment(resId,resource);

            mailSender.send(message);
            log.info("邮件已经发送成功! ");
        } catch (MessagingException  e) {
            log.error("发送邮件时发生异常!", e);
        }
    }
}