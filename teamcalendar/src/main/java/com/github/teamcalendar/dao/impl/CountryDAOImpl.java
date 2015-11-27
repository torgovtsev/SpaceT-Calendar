package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.CountryDAO;
import com.github.teamcalendar.domain.CountryEntity;

@Repository("countryDAO")
public class CountryDAOImpl extends AbstractDao<Integer, CountryEntity> implements CountryDAO
{

    public void addCountry(CountryEntity country)
    {
        persist(country);
    }

    @SuppressWarnings("unchecked")
    public List<CountryEntity> getAllCountries()
    {
        Criteria criteria = createEntityCriteria();
        List<CountryEntity> countries = (List<CountryEntity>)criteria.list();

        return countries;
    }

    public CountryEntity getCountryById(Integer id)
    {
        CountryEntity country = getByKey(id);
        return country;
    }

    @Override
    public CountryEntity getCountryByName(String name)
    {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        CountryEntity country = (CountryEntity)crit.uniqueResult();

        return country;
    }

}
