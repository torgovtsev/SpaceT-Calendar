package com.github.teamcalendar.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.teamcalendar.dao.AbstractDao;
import com.github.teamcalendar.dao.Dao;

public abstract class AbstractDaoImpl<PK extends Serializable, T> implements Dao,AbstractDao<PK, T>
{

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDaoImpl()
    {
        this.persistentClass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getById(PK id)
    {
        return (T)getSession().get(persistentClass, id);
    }

    public void create(T entity)
    {
        getSession().persist(entity);
    }

    public void update(T entity)
    {
        getSession().update(entity);
    }

    public void delete(T entity)
    {
        getSession().delete(entity);
    }

    public Criteria getCriteria()
    {
        return getSession().createCriteria(persistentClass);
    }

    public Query createEntityQuery(String hsql)
    {
        return getSession().createQuery(hsql);
    }

}
