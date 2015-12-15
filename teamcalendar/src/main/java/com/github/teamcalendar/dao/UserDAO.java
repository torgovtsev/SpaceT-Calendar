package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.UserEntity;

public interface UserDAO extends AbstractDao<Integer, UserEntity>
{

    UserEntity getUserByEmail(String email);

    List<UserEntity> getAllUsers();

    Long getCountUserByEmail(String email);

    Long getCountUserByMobile(String mobile);
}
