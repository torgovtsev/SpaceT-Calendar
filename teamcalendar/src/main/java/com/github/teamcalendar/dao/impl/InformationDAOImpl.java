package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.InformationDAO;
import com.github.teamcalendar.domain.InformationEntity;

@Repository("informationDAO")
public class InformationDAOImpl extends AbstractDaoImpl<Integer, InformationEntity> implements InformationDAO
{
    @Override
    @SuppressWarnings("unchecked")
    public List<InformationEntity> getAllInformations()
    {
        Criteria criteria = getCriteria();
        List<InformationEntity> infs = (List<InformationEntity>)criteria.list();

        return infs;
    }
}
