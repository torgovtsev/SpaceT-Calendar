package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.CountryEntity;

public interface CountryDAO extends AbstractDao<Integer, CountryEntity>
{

    void create(CountryEntity country);

    List<CountryEntity> getAllCountries();

    CountryEntity getById(Integer id);

    CountryEntity getCountryByName(String name);

}
