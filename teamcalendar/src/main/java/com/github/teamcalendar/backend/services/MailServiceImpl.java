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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

@Service
public class MailServiceImpl implements MailService
{

    private static final Logger LOG         = LoggerFactory.getLogger(MailServiceImpl.class);

    private Properties          properties  = new Properties();
    private Properties          credentials = new Properties();

    public MailServiceImpl()
    {

    }

    @Override
    public void send(String to, String subject, String body)
    {
        try
        {
            properties.load(getClass().getClassLoader().getResourceAsStream("mailservice.properties"));
            credentials.load(getClass().getClassLoader().getResourceAsStream("mailservice.credentials.properties"));

            Session session = Session.getInstance(properties, null);

            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(credentials.getProperty("MESSAGE_FROM")));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(body);
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
            LOG.error(e.getMessage());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
    }
}
