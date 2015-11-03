package com.github.teamcalendar.dao;

import java.util.List;
import com.github.teamcalendar.domain.Country;

public interface CountryDAO {
	
	void addCountry(Country country);

	List<Country> allCountries();

	
}
