package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.User;

public interface UserDAO extends AbstractDao<Integer, UserEntity>
{

    UserEntity getUserByEmail(String email);

    List<UserEntity> getAllUsers();

    Long getCountUserByEmail(String email);
    
    UserEntity getUserByName(String first, String last);

    Long getCountUserByMobile(String mobile);
}
