package com.ihave.service.impl;

import com.ihave.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, List<String> filePath) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            filePath.forEach(item->{
                FileSystemResource file = new FileSystemResource(new File(item));
                String filename = file.getFilename();
                assert filename != null;
                try {
                    mimeMessageHelper.addAttachment(filename, file);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
            javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendInLinResourceMail(String to, String subject, String content, Map<String,Object> params) throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);
        for(Map.Entry<String,Object> map: params.entrySet()){
            FileSystemResource fileSystemResource=new FileSystemResource(new File(map.getValue().toString()));
            mimeMessageHelper.addInline(map.getKey(),fileSystemResource);
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendHtmlTemplatesMail(String to, String subject, String templates, Map<String,Object> params) throws MessagingException {
        Context context = new Context();
        for(Map.Entry<String,Object> map: params.entrySet()){
            context.setVariable(map.getKey(),map.getValue());
        }
        String process = templateEngine.process(templates, context);

        sendHtmlEmail(to,subject,process);
    }
}
