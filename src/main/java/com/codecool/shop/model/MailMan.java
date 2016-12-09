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
            String messageText = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Collegium Hungaricum Berlin - 1956</title>\n" +
                    "<meta charset=\"utf-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n" +
                    "\n" +
                    "</head>\n" +
                    "    <body style=\"margin: 0 !important; padding: 0 !important;\">\n" +
                    "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                    "            <tr>\n" +
                    "                <td>\n" +
                    "                    <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse; border: 1px solid #cccccc; font-size: 15; font-family: Helvetica;\">\n" +
                    "                        <tr>\n" +
                    "                            <td align=\"center\">\n" +
                    "                                <img src=\"https://c3.staticflickr.com/1/131/31141177970_816abda6e8_b.jpg\" alt=\"Codecool Shop LOGO\" style=\"display: block\" width=\"100%\"/>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"font-size: 60px; text-align: center; color: #595959; padding: 0px 0px 40px 0px\">\n" +
                    "                                <strong>Codecool Shop<br></strong>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"font-size: 35px; padding: 0px 36px 40px 36px;\">\n" +
                    "                                Hello there\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"font-size: 18px; padding: 0px 36px 40px 36px; line-height: 2;\">\n" +
                    "                                Thank you for your registration! We are developing fast\n" +
                    "                                to give you the best experience, please come back to our\n" +
                    "                                site later, and check out the new features week by week!\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"padding: 0px 30px 15px 36px\">\n" +
                    "                                <strong>Members of JavaBeans</strong>\n" +
                    "                            </td>\n" +
                    "                        </tr>         \n" +
                    "                    </table>\n" +
                    "                </td>\n" +
                    "            </tr>        \n" +
                    "        </table>\n" +
                    "    </body>\n" +
                    "</html>";

            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress("no-reply", "No Reply"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            message.setReplyTo(InternetAddress.parse("no-reply"));


            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(customerAddress));
            message.setSubject("Sikeres regisztráció!:)");
            message.setContent(messageText, "text/html");

            Transport.send(message);

            System.out.println("Email sent: [OK]");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email sent:[NOT]");
        }
    }
}