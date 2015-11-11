package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.CountryDAO;
import com.github.teamcalendar.domain.CountryEntity;
import com.github.teamcalendar.domain.QuestionEntity;
import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("countryDAO")
public class CountryDAOImpl extends AbstractDao<Integer, CountryEntity>
		implements CountryDAO {

	public void addCountry(CountryEntity country) {
		persist(country);
	}

	@SuppressWarnings("unchecked")
	public List<CountryEntity> getAllCountries() {
		Criteria criteria = createEntityCriteria();
		List<CountryEntity> countries = (List<CountryEntity>) criteria.list();

		return countries;
	}

	public CountryEntity getCountryById(Integer id) {
		CountryEntity country = getByKey(id);
		return country;
	}

}
