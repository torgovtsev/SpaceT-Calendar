package com.github.teamcalendar.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface Dao
{
    
    Session getSession();
    
    Criteria getCriteria();     

}
