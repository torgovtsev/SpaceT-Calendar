package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.CountryEntity;
import com.github.teamcalendar.middleware.dto.Country;

public interface CountryDAO {

	void addCountry(CountryEntity country);

	List<CountryEntity> getAllCountries();

	CountryEntity getCountryById(Integer id);

}
