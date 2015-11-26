package com.github.teamcalendar.backend.services;

public interface MailService
{
    public void send(String to, String subject, String body);
}
