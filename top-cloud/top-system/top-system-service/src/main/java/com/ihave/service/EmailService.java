package com.ihave.service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

public interface EmailService {

    /**
     * 发送普通邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendEmail(String to,String subject,String content);

    /**
     * 发送html类型的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容（代指html）
     * @throws MessagingException 异常
     */
    void sendHtmlEmail(String to,String subject,String content) throws MessagingException;

    /**
     * 发送带文件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 文件（多个文件的路径）
     * @throws MessagingException 异常
     */
    void sendAttachmentsMail(String to, String subject, String content, List<String> filePath) throws MessagingException;

    /**
     * 发送带图片的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param params 图片参数（ID，filepath）
     * @throws MessagingException 异常
     */
    void sendInLinResourceMail(String to,String subject,String content,Map<String,Object> params) throws MessagingException;

    /**
     * 发送模版的邮件
     * @param to 收件人
     * @param subject 主题
     * @param templates 内容
     * @param params 模版参数（参数名，参数值）
     * @throws MessagingException 异常
     */
    void sendHtmlTemplatesMail(String to, String subject,String templates, Map<String,Object> params) throws MessagingException;
}
