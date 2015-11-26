package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.UserResetPasswordEntity;

public interface UserResetPasswordDAO
{
    void addUserResetPassword(UserResetPasswordEntity user);

    void updateUserResetPassword(UserResetPasswordEntity user);

    void deleteUserResetPassword(UserResetPasswordEntity user);

    UserResetPasswordEntity getUserResetPasswordByUserId(Integer id);

    List<UserResetPasswordEntity> getAllUsersResetPassword();
}
