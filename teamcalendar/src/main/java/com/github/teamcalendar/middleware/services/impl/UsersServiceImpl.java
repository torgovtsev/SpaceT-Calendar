package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.UserDAO;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UsersService;
import com.github.teamcalendar.middleware.utils.MapperService;

/**
 * @author Artem Ivanov
 *
 */
@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService
{

    private static final Logger LOG = LoggerFactory.getLogger(UserEntity.class);

    @Autowired
    private UserDAO             dao;

    /**
     * @param User
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean addUser(User user)
    {
        if (user == null)
        {
            return false;
        }

        try
        {
            ShaPasswordEncoder encoder = new ShaPasswordEncoder(512);
            user.setPassword(encoder.encodePassword(user.getPassword(), null));
            UserEntity userEntity = convertUserToEntity(user);
            dao.create(userEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to add user");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param User
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean updateUser(User user)
    {
        if (user == null)
        {
            return false;
        }

        try
        {
            ShaPasswordEncoder encoder = new ShaPasswordEncoder(512);
            user.setPassword(encoder.encodePassword(user.getPassword(), null));

            UserEntity userEntity = convertUserToEntity(user);
            dao.update(userEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to update user");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param User
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean deleteUser(User user)
    {
        if (user == null)
        {
            return false;
        }
        try
        {
            UserEntity userEntity = convertUserToEntity(user);
            dao.delete(userEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to delete user");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return <code>null</code> if user does not exist
     */
    public User getUserByEmail(String email)
    {

        User result = null;
        try
        {
            UserEntity userEntity = (UserEntity)dao.getUserByEmail(email);

            if (userEntity != null)
            {
                result = convertEntityToUser(userEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by email=%s", email), ex);
        }

        return result;
    }

    /**
     * @return <code>null</code> if user does not exist
     */
    public User getUserByID(Integer id)
    {

        User result = null;
        try
        {
            UserEntity userEntity = (UserEntity)dao.getById(id);
            if (userEntity != null)
            {
                result = convertEntityToUser(userEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by id=%s", id), ex);
        }

        return result;
    }

    /**
     * @return <code>null</code> if there is no users in table
     */
    public List<User> getAllUsers()
    {

        final List<User> result = new ArrayList<User>();

        List<UserEntity> usersEntity = dao.getAllUsers();

        if (CollectionUtils.isEmpty(usersEntity))
        {
            LOG.error("NULL reference on users");
            return result;
        }

        for (UserEntity data : usersEntity)
        {
            User user = convertEntityToUser(data);
            result.add(user);
        }
        return result;
    }

    /**
     * @param email
     * @return true in case User with such email already exists
     */
    public boolean checkExistEmail(String email)
    {
        Long count;

        count = dao.getCountUserByEmail(email);

        if (count.equals(1l))
        {
            return true;
        }

        return false;
    }

    public boolean checkExistMobile(String mobile)
    {
        Long count;

        count = dao.getCountUserByMobile(mobile);

        if (count.equals(1l))
        {
            return true;
        }

        return false;
    }

    /**
     * @param User
     *            if a null object parameter is passed to method, nothing will happen
     * @return true User success validate
     */
    public boolean validateUser(User user)
    {
        if (user == null)
        {
            return false;
        }

        boolean valid = true;

        FacesContext context = FacesContext.getCurrentInstance();

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";

        final String MOBILE_PATTERN = "^[0-9]{11}$";

        final String PASSWORD_PATTERN = "^[a-z0-9_-]{6,18}$";

        Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
        Matcher matcherEmail;

        Pattern patternMobile = Pattern.compile(MOBILE_PATTERN);
        Matcher matcherMobile;

        Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcherPassword;

        matcherEmail = patternEmail.matcher(user.getEmail());
        matcherMobile = patternMobile.matcher(user.getMobile());
        matcherPassword = patternPassword.matcher(user.getPassword());

        if (user.getFirstName().length() < 1 && user.getFirstName().length() > 30)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name must have length between 1 and 30",
                    "Name must have length between 1 and 30"));
            valid = false;
        }

        if (user.getLastName().length() < 1 && user.getLastName().length() > 30)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lastname must have length between 1 and 30",
                    "Lastname must have length between 1 and 30"));
            valid = false;
        }

        if (user.getSecretAnswer().length() < 1)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter secret answer", "Enter secret answer"));
            valid = false;
        }

        if (!checkExistEmail(user.getEmail()))
        {
            if (!matcherEmail.matches())
            {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email can have @ and '.'", "Email can have @ and '.'"));
                valid = false;
            }
        }
        else
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email alredy exist", "Email alredy exist"));
            valid = false;
        }

        if (user.getMobile() != null)
        {
            if (!checkExistMobile(user.getMobile()))
            {
                if (!matcherMobile.matches())
                {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User mobile can have 11 digits",
                            "User mobile can have 11 digits"));
                    valid = false;

                }
            }
            else
            {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mobile alredy exist", "Mobile alredy exist"));
                valid = false;
            }
        }
        if (!matcherPassword.matches())
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password can have 6-18 symbols [A-z0-9-_]",
                    "Password can have 6-18 symbols [A-z0-9-_]"));
            valid = false;
        }

        return valid;

    }

    private User convertEntityToUser(UserEntity entity)
    {
        return MapperService.getInstance().map(entity, User.class);
    }

    private UserEntity convertUserToEntity(User user)
    {
        return MapperService.getInstance().map(user, UserEntity.class);
    }

	@Override
	public User getUserByName(String first, String last) {
		User result = null;
        try
        {
            UserEntity userEntity = (UserEntity)dao.getUserByName(first, last);
            if (userEntity != null)
            {
                result = convertEntityToUser(userEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by name=%s", first+" "+last), ex);
        }

        return result;
	}

}
