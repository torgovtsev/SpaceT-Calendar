package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.GroupDAO;
import com.github.teamcalendar.domain.CountryEntity;
import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.domain.UserGroupEntity;
import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("groupDAO")
public class GroupDAOImpl extends AbstractDao<Integer, GroupEntity> implements GroupDAO
{

    private static final Logger LOG = LoggerFactory.getLogger(GroupEntity.class);

    public void addGroup(Group group)
    {
        GroupEntity data = convertGroupToEntity(group);
        persist(data);
    }

    public void updateRole(Group group)
    {
        GroupEntity data = convertGroupToEntity(group);
        update(data);
    }

    public void deleteRole(Group group)
    {
        GroupEntity data = convertGroupToEntity(group);
        delete(data);
    }

    @SuppressWarnings("unchecked")
    public List<Group> getAllGroup()
    {

        final List<Group> result = new ArrayList<Group>();

        Criteria criteria = createEntityCriteria();
        List<GroupEntity> groups = (List<GroupEntity>)criteria.list();

        if (CollectionUtils.isEmpty(groups))
        {
            LOG.error("NULL reference on group");
            return result;
        }

        for (GroupEntity data : groups)
        {
            Group group = convertEntityToGroup(data);
            result.add(group);
        }

        return result;
    }

    public Group getGroupById(Integer id)
    {

        Group result = null;

        try
        {
            GroupEntity group = getByKey(id);
            if (group != null)
            {
                result = convertEntityToGroup(group);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading group by id=%s", id), ex);
        }

        return result;

    }

    public Group getGroupByName(String name)
    {

        Group result = null;

        try
        {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("name", name));
            GroupEntity group = (GroupEntity)crit.uniqueResult();

            if (group != null)
            {
                result = convertEntityToGroup(group);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading group by name=%s", name), ex);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public Set<Group> getGroupForUser(User user)
    {
        String email = user.getEmail();
        String hql = "select g from Group g join g.userGroup ug join ug.user u where u.email = :email";
        Query query = createEntityQuery(hql).setParameter("email", email);
        Set<GroupEntity> groups = (Set<GroupEntity>)query.list();

        Set<Group> result = new HashSet<Group>();

        for (GroupEntity gr : groups)
        {
            Group data = convertEntityToGroup(gr);
            result.add(data);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public void updateGroupForUser(User user, List<Group> userGroups)
    {

        //		UserEntity userEntity = MapperService.getInstance().map(user, UserEntity.class);
        //		
        //		String hql = "select ug from userGroupEntity ug where ug.user = :user";
        //		Query query = createEntityQuery(hql).setParameter("user", user);
        //		List<UserGroupEntity> oldGroups = (List<UserGroupEntity>) query.list();	
        //		
        //		for(UserGroupEntity group: oldGroups){
        //			delete(group);
        //		}
        //		
        //		Set<UserGroupEntity> resultUserGroup = new HashSet<UserGroupEntity>();
        //		
        //		for(Group g: userGroups){
        //			GroupEntity groupEntity = MapperService.getInstance().map(g, GroupEntity.class);
        //			
        //			UserGroupEntity userGroup = new UserGroupEntity(userEntity,groupEntity);
        //			persist(userGroup);
        //			resultUserGroup.add(userGroup);
        //		}
        //		userEntity.setUserGroupEntity(resultUserGroup);

    }

    private Group convertEntityToGroup(GroupEntity entity)
    {
        return MapperService.getInstance().map(entity, Group.class);
    }

    private GroupEntity convertGroupToEntity(Group group)
    {
        return MapperService.getInstance().map(group, GroupEntity.class);
    }

}
