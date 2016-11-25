package com.codecool.shop.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailMan {
    Session session = null;

    public MailMan() {
        if (session == null) {
            init();
        }
    }

    public void init() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("javababok.webshop@gmail.com", "Codecoo1");
            }
        });
        if (session != null) {
            System.out.println("Email fromAddress: [OK]");
        } else {
            System.out.println("Email fromAddress: [NOK]");
        }
    }
    public void sendWelcome(String customerAddress) {
        if (session == null) {
            System.exit(0);
        }
        try {
            String messageText = "Üdvözöllek a JavaBabok webshopjában!";
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress("no-reply", "No Reply"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            message.setReplyTo(InternetAddress.parse("no-reply"));


            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(customerAddress));
            message.setSubject("Sikeres regisztráció!:)");
            message.setText(messageText);

            Transport.send(message);

            System.out.println("Email sent: [OK]");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email sent:[NOT]");
        }
    }
}