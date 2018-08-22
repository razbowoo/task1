package com.ecreatic.test.services;



import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {


    private JavaMailSender mailSender;


    public void sendMail(String emailId)
    {
        MimeMessage message =  this.mailSender.createMimeMessage();
        MimeMessageHelper mimeHelper;
        try {
            mimeHelper = new MimeMessageHelper(message,true);
            mimeHelper.setTo(emailId);


            mimeHelper.setFrom("kirya.kuzmich@gmail.com");
            mimeHelper.setSubject("Password Reset");
            mimeHelper.setText("<html><body>hi,<br/><a href='http://localhost:8080/forgot-password/newPassword/"+emailId+"/'> Click here</a> to reset password</body></html>",true);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Error Sending email "+ e.getMessage());
        }

    }
}
