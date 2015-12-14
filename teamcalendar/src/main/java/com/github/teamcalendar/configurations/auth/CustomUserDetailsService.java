package com.github.teamcalendar.configurations.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UsersService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private UsersService usersService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        //TODO: remove it
        //        username = username.toLowerCase();
        User user = usersService.getUserByEmail(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getIsVerified(), true, true,
                true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user)
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Role userProfile : user.getRoleUser())
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getName().toUpperCase()));
        }
        return authorities;
    }

}
