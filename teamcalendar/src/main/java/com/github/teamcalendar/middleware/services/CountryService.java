package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.services.impl.CountryServiceImpl;

/**
 * @author Artem Ivanov
 * @see {@link CountryServiceImpl}
 */
public interface CountryService
{

    boolean addCountry(Country country);

    List<Country> getAllCountries();

    Country getCountryById(Integer id);

    Country getCountryByName(String name);

}
