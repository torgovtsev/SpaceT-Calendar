package com.github.teamcalendar.backend.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class MailServiceImpl implements MailService
{

    private String     message_to;
    private String     message_body;
    private String     message_subject;

    private Properties properties  = new Properties();
    private Properties credentials = new Properties();

    public MailServiceImpl(String to, String body, String subject)
    {
        this.message_to = to;
        this.message_body = body;
        this.message_subject = subject;
    }

    @Override
    public void send()
    {
        try
        {
            properties.load(getClass().getClassLoader().getResourceAsStream("mailservice.properties"));
            credentials.load(getClass().getClassLoader().getResourceAsStream("mailservice.credentials.properties"));

            Session session = Session.getInstance(properties, null);

            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(credentials.getProperty("MESSAGE_FROM")));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(message_to, false));
            msg.setSubject(message_subject);
            msg.setText(message_body);
            msg.setSentDate(new Date());

            SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

            t.connect(properties.getProperty("mail.smtps.host"), credentials.getProperty("SMTP_USERNAME"),
                    credentials.getProperty("SMTP_PASSWORD"));
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
