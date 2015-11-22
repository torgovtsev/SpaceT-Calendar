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

import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

@Service
public class MailServiceImpl implements MailService
{

    private String     message_to;
    private String     message_body;
    private String     message_subject;

    private Properties properties  = new Properties();
    private Properties credentials = new Properties();

    public MailServiceImpl()
    {

    }

    public void setMessage_to(String message_to)
    {
        this.message_to = message_to;
    }

    public void setMessage_body(String message_body)
    {
        this.message_body = message_body;
    }

    public void setMessage_subject(String message_subject)
    {
        this.message_subject = message_subject;
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
