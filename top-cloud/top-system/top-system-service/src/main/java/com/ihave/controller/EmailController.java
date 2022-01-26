package com.ihave.controller;

import com.ihave.api.R;
import com.ihave.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "邮件控制器")
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    @ApiOperation(value = "发送邮件")
    public R<Object> sms(String to, String subject, String content) {
        emailService.sendEmail(to,subject,content);
        return R.status(true);
    }

}
