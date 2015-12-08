package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.CountryDAO;
import com.github.teamcalendar.domain.CountryEntity;
import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.services.CountryService;
import com.github.teamcalendar.middleware.utils.MapperService;

/**
 * @author Artem Ivanov
 *
 */
@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService
{

    private static final Logger LOG = LoggerFactory.getLogger(CountryEntity.class);

    @Autowired
    private CountryDAO          dao;

    /**
     * @param country
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean addCountry(Country country)
    {
        if (country == null)
        {
            return false;
        }

        try
        {
            CountryEntity countryEntity = convertCountryToEntity(country);
            dao.addCountry(countryEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to add country");
        }

        return false;
    }

    /**
     * @return <code>null</code> if there is no countries in table
     */
    public List<Country> getAllCountries()
    {
        List<Country> result = new ArrayList<Country>();

        List<CountryEntity> countriesEntity = dao.getAllCountries();

        if (CollectionUtils.isEmpty(countriesEntity))
        {
            LOG.error("NULL reference for country ");
            return result;
        }

        for (CountryEntity data : countriesEntity)
        {
            Country country = convertEntityToCountry(data);
            result.add(country);
        }
        return result;
    }

    /**
     * @return <code>null</code> if there is no entry for such id
     */
    public Country getCountryById(Integer id)
    {
        Country result = null;

        try
        {
            CountryEntity countryEntity = (CountryEntity)dao.getCountryById(id);
            if (countryEntity != null)
            {
                result = convertEntityToCountry(countryEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading country by id=%s", id), ex);
        }

        return result;
    }

    /**
     * @return <code>null</code> if there is no such country
     */
    public Country getCountryByName(String name)
    {
        Country result = null;

        try
        {
            CountryEntity countryEntity = (CountryEntity)dao.getCountryByName(name);

            if (countryEntity != null)
            {
                result = convertEntityToCountry(countryEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading country by name=%s", name), ex);
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
