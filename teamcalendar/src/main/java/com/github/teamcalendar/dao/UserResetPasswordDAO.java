package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.UserResetPasswordEntity;

public interface UserResetPasswordDAO extends AbstractDao<Integer, UserResetPasswordEntity>
{
    void create(UserResetPasswordEntity user);

    void update(UserResetPasswordEntity user);

    void delete(UserResetPasswordEntity user);

    UserResetPasswordEntity getUserResetPasswordByUserId(Integer id);

    List<UserResetPasswordEntity> getAllUsersResetPassword();
}
