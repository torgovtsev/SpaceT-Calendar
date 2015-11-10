package com.github.teamcalendar.middleware.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class MapperService
{
    private static DozerBeanMapper instance;

    public static synchronized Mapper getInstance()
    {
        if (instance == null)
        {
            instance = new DozerBeanMapper();
            instance.addMapping(new MapperBuilder());
        }
        return instance;
    }
    
}
