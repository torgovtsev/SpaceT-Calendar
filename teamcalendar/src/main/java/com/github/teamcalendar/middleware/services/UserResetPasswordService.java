package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.UserResetPassword;

public interface UserResetPasswordService
{
    void addUserResetPassword(UserResetPassword user);

    void updateUserResetPassword(UserResetPassword user);

    void deleteUserResetPassword(UserResetPassword user);

    UserResetPassword getUserResetPasswordByUserId(Integer id);

    List<UserResetPassword> getAllUsersResetPassword();
}
