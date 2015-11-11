package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.dto.Group;

public interface CountryService {

	void addCountry(Country country);

	List<Country> getAllCountries();

	Country getCountryById(Integer id);

}
