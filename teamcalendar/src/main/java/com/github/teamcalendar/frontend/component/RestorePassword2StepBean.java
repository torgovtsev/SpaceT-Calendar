package com.github.teamcalendar.frontend.component;

import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.backend.services.MailService;
import com.github.teamcalendar.backend.services.MailServiceImpl;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UsersService;

@ManagedBean(name = "restorePassword2StepBean")
@Component(value = "restorePassword2StepBean")
@RequestScoped
public class RestorePassword2StepBean
{
    private final String STRING_USERMESSAGE_GENERAL_ERROR         = "Something went wrong";
    private final String STRING_USERMESSAGE_GENERAL_SUCCESS       = "Success!";

    private final String STRING_USERMESSAGE_INVALID_USERNAME_CODE = "Invalid username or code";
    private final String STRING_USERMESSAGE_PASSWORDSENT          = "New password sent to your email";

    private final String STRING_PASSWORDRESET_MAILSUBJECT         = "[Space-T Calendar] New password";
    private final String STRING_PASSWORDRESET_MAILBODY            = "Hi.\n\nThis is your new password: %s\n\nThanks for using Space-T Calendar.";

    @Autowired
    private UsersService userService;

    @Autowired
    private MailService  mailService;

    private String       username;
    private String       generatedCode;

    public String getGeneratedCode()
    {
        return generatedCode;
    }

    public void setGeneratedCode(String generatedCode)
    {
        this.generatedCode = generatedCode;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void generateNewPasswordAndSentByEmail(ActionEvent event)
    {
        FacesMessage message = null;

        if (username != null && generatedCode != null)
        {
            if (username.contains("@") && username.contains("."))
            {
                User user = userService.getUserByEmail(username);
                if (user != null && user.getIsVerified() && !user.getIsBlocked() && !user.getIsDeleted())
                {
                    if (user.getSecretAnswer().equals(generatedCode))
                    {
                        String newPassword = RandomStringUtils.random(10, true, true);
                        user.setPassword(newPassword);

                        //TODO change "getSecretAnswer" to "getResetPasswordUuid"
                        user.setSecretAnswer(UUID.randomUUID().toString());//overwrite old confirmation code

                        userService.updateUser(user);

                        ((MailServiceImpl)mailService).setMessage_body(String.format(STRING_PASSWORDRESET_MAILBODY, newPassword));
                        ((MailServiceImpl)mailService).setMessage_subject(STRING_PASSWORDRESET_MAILSUBJECT);
                        ((MailServiceImpl)mailService).setMessage_to(username);

                        mailService.send();

                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, STRING_USERMESSAGE_GENERAL_SUCCESS,
                                STRING_USERMESSAGE_PASSWORDSENT);
                    }
                    else
                    {
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_USERMESSAGE_GENERAL_ERROR,
                                STRING_USERMESSAGE_INVALID_USERNAME_CODE);
                    }
                }
                else
                {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_USERMESSAGE_GENERAL_ERROR, "");
                }
            }
        }
        else
        {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_USERMESSAGE_GENERAL_ERROR, "");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
