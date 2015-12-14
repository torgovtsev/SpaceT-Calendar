package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.UserEntity;

public interface UserDAO extends AbstractDao<Integer, UserEntity>
{

    void create(UserEntity user);

    void update(UserEntity user);

    void delete(UserEntity user);

    UserEntity getUserByEmail(String email);

    UserEntity getById(Integer id);

    List<UserEntity> getAllUsers();

    Long getCountUserByEmail(String email);

    Long getCountUserByMobile(String mobile);
}
