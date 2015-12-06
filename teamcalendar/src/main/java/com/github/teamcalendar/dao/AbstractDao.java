package com.github.teamcalendar.dao;

import java.io.Serializable;

public interface AbstractDao<PK extends Serializable, T> extends Dao
{

    T getById(PK id);

    void create(T t);

    void update(T t);

    void delete(T t);

}
