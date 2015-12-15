package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.UserResetPasswordEntity;

public interface UserResetPasswordDAO extends AbstractDao<Integer, UserResetPasswordEntity>
{

    UserResetPasswordEntity getUserResetPasswordByUserId(Integer id);

    List<UserResetPasswordEntity> getAllUsersResetPassword();
}
