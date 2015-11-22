package com.github.teamcalendar.frontend.component;

import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.backend.services.MailService;
import com.github.teamcalendar.backend.services.MailServiceImpl;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UsersService;

@ManagedBean(name = "restorePasswordBean")
@Component(value = "restorePasswordBean")
@RequestScoped
public class RestorePasswordBean
{

    private final String STRING_PASSWORDRESET_MAILBODY      = "Hi.\n\nYour reset password code:\n\n%s\n\nProceed to http://localhost:8080/teamcalendar/restore2step.xhtml\n\nThanks for using Space-T Calendar.";
    private final String STRING_PASSWORDRESET_MAILSUBJECT   = "[Space-T Calendar] Reset password identifier";

    private final String STRING_USERMESSAGE_GENERAL_ERROR   = "Something went wrong";
    private final String STRING_USERMESSAGE_GENERAL_SUCCESS = "Success!";

    private final String STRING_USERMESSAGE_CODE_SENT       = "Code was sent to your email";

    @Autowired
    private UsersService userService;

    @Autowired
    MailService          mailService;

    private String       username;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void reset(ActionEvent event)
    {
        FacesMessage message = null;

        if (username.contains("@") && username.contains("."))
        {
            User user = userService.getUserByEmail(username);
            if (user != null && user.getIsVerified() && !user.getIsBlocked() && !user.getIsDeleted())
            {
                //TODO change "setSecretAnswer" to "setResetPasswordUuid"
                String resetPasswordUuid = UUID.randomUUID().toString();
                user.setSecretAnswer(resetPasswordUuid);
                userService.updateUser(user);

                ((MailServiceImpl)mailService).setMessage_body(String.format(STRING_PASSWORDRESET_MAILBODY, resetPasswordUuid));
                ((MailServiceImpl)mailService).setMessage_subject(STRING_PASSWORDRESET_MAILSUBJECT);
                ((MailServiceImpl)mailService).setMessage_to(user.getEmail());
                mailService.send();

                message = new FacesMessage(FacesMessage.SEVERITY_INFO, STRING_USERMESSAGE_GENERAL_SUCCESS, STRING_USERMESSAGE_CODE_SENT);
            }
            else
            {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_USERMESSAGE_GENERAL_ERROR, "");

            }
        }
        else
        {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_USERMESSAGE_GENERAL_ERROR, "");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
