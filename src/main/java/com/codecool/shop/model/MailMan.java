package com.codecool.shop.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailMan {

    Session session = null;
    private EmailType type;
    private String name;
    private double cost;
    private String time;
    private float totalPrice;

    public MailMan(EmailType type, String name) {
        System.out.println(session);
        if (session == null) {
            init();
            System.out.println("it is in the if loop");
            this.type = type;
            this.name = name;
            this.setEmailBody(name);
        }
    }

    public MailMan(EmailType type, String name, double cost, String time, float totalPrice ) {
        if (session == null) {
            init();
            this.type = type;
            this.name = name;
            this.cost = cost;
            this.time = time;
            this.totalPrice = totalPrice;
            this.setSummeryEmailBody(name, cost, time, totalPrice);
        }
    }

    public double calcTotal(float totalPrice, double cost) {
        double totalOrderPrice = totalPrice;
        return totalOrderPrice + cost;
    }

    private String setEmailBody(String name){

        String WELCOME_MESSAGE = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Registration successful</title>\n" +
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
                "                                Hello \n" + name +
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

        return WELCOME_MESSAGE;
    }

    private String setSummeryEmailBody(String name, double cost, String time, float totalPrice){

        String SUMMARY_MESSAGE = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Summary</title>\n" +
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
                "                                Hello " + name + "\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td style=\"font-size: 18px; padding: 0px 36px 40px 36px; line-height: 2;\">\n" +
                "                                Thank you for ordering from us! Our drone is on the way with your beans." +
                "                                Please check the delivery time, and make sure to receive the order!" +
                "                                Here are your order details:\n"+
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tr>\n" +
                "                        <tr>" +
                "                           <td style=\"font-size: 18px; padding: 0px 36px 40px 36px; line-height: 2;\">\n" +
                "                               <strong>Delivery time:</strong> " + time + "\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>" +
                "                           <td style=\"font-size: 18px; padding: 0px 36px 40px 36px; line-height: 2;\">\n" +
                "                                <strong>Delivery cost:</strong> " + cost + "\n" +
                "                            </td>\n" +
                "                        <tr>" +
                "                           <td style=\"font-size: 18px; padding: 0px 36px 40px 36px; line-height: 2;\">\n" +
                "                                <strong>Order cost:</strong> " + totalPrice + "$" + "\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>" +
                "                           <td style=\"font-size: 18px; padding: 0px 36px 40px 36px; line-height: 2;\">\n" +
                "                                <strong>Total cost:</strong> " + calcTotal(totalPrice, cost) + "$" + "\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td style=\"padding: 0px 30px 15px 36px\">\n" +
                "                                <strong>Members of JavaBeans</strong>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>        \n" +
                "        </table>\n" +
                "    </body>\n" +
                "</html>";

        return SUMMARY_MESSAGE;

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
            String messageText = setEmailBody(this.name);

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

    public void sendSummary(String customerAddress) {
        if (session == null) {
            System.exit(0);
        }
        try {
            String messageText = setSummeryEmailBody(this.name, this.cost, this.time, this.totalPrice);

            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress("no-reply", "No Reply"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            message.setReplyTo(InternetAddress.parse("no-reply"));


            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(customerAddress));
            message.setSubject("confirmation");
            message.setContent(messageText, "text/html");

            Transport.send(message);

            System.out.println("Email sent: [OK]");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email sent:[NOT]");
        }
    }

}