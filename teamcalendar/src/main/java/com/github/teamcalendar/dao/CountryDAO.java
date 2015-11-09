package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Country;

public interface CountryDAO
{

    void addCountry(Country country);

    List<Country> getAllCountries();

}