package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.InformationEntity;

public interface InformationDAO extends AbstractDao<Integer, InformationEntity>
{
    void create(InformationEntity information);

    void update(InformationEntity information);

    void delete(InformationEntity information);

    //get events by criteria
    List<InformationEntity> getAllInformations();
}
