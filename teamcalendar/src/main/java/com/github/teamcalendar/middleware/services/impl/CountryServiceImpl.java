package com.github.teamcalendar.middleware.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.CountryDAO;
import com.github.teamcalendar.middleware.dto.Country;

@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryDAO
{

    @Autowired
    private CountryDAO dao;

    public void addCountry(Country country)
    {
        dao.addCountry(country);
    }

    public List<Country> getAllCountries()
    {
        return dao.getAllCountries();
    }

}
