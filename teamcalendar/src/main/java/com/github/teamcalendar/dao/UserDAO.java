package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.User;

public interface UserDAO
{

    void addUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(UserEntity user);

    UserEntity getUserByEmail(String email);

    UserEntity getUserByID(Integer id);

    List<UserEntity> getAllUsers();

}
