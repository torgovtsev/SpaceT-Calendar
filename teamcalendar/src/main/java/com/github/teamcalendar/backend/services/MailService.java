package com.github.teamcalendar.backend.services;

public interface MailService
{
    /**
     * Sends email message
     * 
     * @param to
     *            valid email addesss
     * @param subject
     *            title of message, plain text
     * @param body
     *            plain text
     */
    public void send(String to, String subject, String body);
}
