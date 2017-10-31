package com.provectus.grub.service;


import com.provectus.grub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Random;

@Service
public class EmailNotification {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNotification(User user) throws MailException {
        // send email

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getUsername());
        mail.setFrom("pavvel@gmail.com");
        mail.setSubject("Grub e-mail confirmation");
        mail.setText("http://grub.com.ua:8080/activation?activationCode=" + user.getActivationCode());

        javaMailSender.send(mail);
    }

    public void sendPasswordReminder(User user) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getUsername());
        mail.setFrom("pavvel@gmail.com");
        mail.setSubject("Grub password reminder");
        mail.setText("Your password: " + user.getPassword());

        javaMailSender.send(mail);

    }

    public String generateCode(int length)
    {
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
        Random rnd = new Random();
        char[] code = new char[length];
        for (int i = 0; i < length; i++)
        {
            code[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(code);
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
