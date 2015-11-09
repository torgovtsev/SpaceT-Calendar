package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.CountryDAO;
import com.github.teamcalendar.domain.CountryEntity;
import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("countryDAO")
public class CountryDAOImpl extends AbstractDao<Integer, CountryEntity> implements CountryDAO
{

    private static final Logger LOG = LoggerFactory.getLogger(CountryEntity.class);

    public void addCountry(Country country)
    {
        CountryEntity data = convertCountryToEntity(country);
        persist(data);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries()
    {
        final List<Country> result = new ArrayList<Country>();

        Criteria criteria = createEntityCriteria();
        List<CountryEntity> countries = (List<CountryEntity>)criteria.list();

        if (CollectionUtils.isEmpty(countries))
        {
            LOG.error("NULL reference ");
            return result;
        }

        for (CountryEntity data : countries)
        {
            Country country = convertEntityToCountry(data);
            result.add(country);
        }

        return result;
    }

    private Country convertEntityToCountry(CountryEntity entity)
    {
        return MapperService.getInstance().map(entity, Country.class);
    }

    private CountryEntity convertCountryToEntity(Country country)
    {
        return MapperService.getInstance().map(country, CountryEntity.class);
    }

}
