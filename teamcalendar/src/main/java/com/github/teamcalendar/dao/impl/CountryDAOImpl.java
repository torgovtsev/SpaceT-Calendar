package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.CountryDAO;
import com.github.teamcalendar.domain.CountryEntity;

@Repository("countryDAO")
public class CountryDAOImpl extends AbstractDaoImpl<Integer, CountryEntity> implements CountryDAO
{

    @SuppressWarnings("unchecked")
    public List<CountryEntity> getAllCountries()
    {
        Criteria criteria = getCriteria();
        List<CountryEntity> countries = (List<CountryEntity>)criteria.list();

        return countries;
    }

    @Override
    public CountryEntity getCountryByName(String name)
    {
        Criteria crit = getCriteria();
        crit.add(Restrictions.eq("name", name));
        CountryEntity country = (CountryEntity)crit.uniqueResult();

        return country;
    }

}
