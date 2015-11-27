package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.CountryEntity;

public interface CountryDAO
{

    void addCountry(CountryEntity country);

    List<CountryEntity> getAllCountries();

    CountryEntity getCountryById(Integer id);

    CountryEntity getCountryByName(String name);

}
