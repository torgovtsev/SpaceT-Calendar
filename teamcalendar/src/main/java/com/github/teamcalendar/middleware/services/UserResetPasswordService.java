package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.UserResetPassword;
import com.github.teamcalendar.middleware.services.impl.UserResetPasswordServiceImpl;

/**
 * @author Ivan Kopylov
 * @see {@link UserResetPasswordServiceImpl}
 */
public interface UserResetPasswordService
{

    /**
     * @param user
     *            if a null object parameter is passed to method, nothing will happen
     */
    boolean addUserResetPassword(UserResetPassword user);

    /**
     * @param user
     *            if a null object parameter is passed to method, nothing will happen
     */
    boolean updateUserResetPassword(UserResetPassword user);

    /**
     * @param user
     *            if a null object parameter is passed to method, nothing will happen
     */
    boolean deleteUserResetPassword(UserResetPassword user);

    /**
     * @param id
     *            should be PK of UserEntity
     * @see UserEntity#getId()
     */
    UserResetPassword getUserResetPasswordByUserId(Integer id);

    List<UserResetPassword> getAllUsersResetPassword();
}
