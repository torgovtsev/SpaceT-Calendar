package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.CountryEntity;

public interface CountryDAO extends AbstractDao<Integer, CountryEntity>
{

    List<CountryEntity> getAllCountries();

    CountryEntity getCountryByName(String name);

}
